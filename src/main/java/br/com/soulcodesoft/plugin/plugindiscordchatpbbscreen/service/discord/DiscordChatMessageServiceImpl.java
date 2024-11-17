package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.discord;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.DiscordPluginType;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ResponseChatLog;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.ChatLogEntity;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.chatlog.ChatLogService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons.DiscordAbstract;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DiscordChatMessageServiceImpl extends DiscordAbstract implements DiscordChatMessageService {

    private static final String SQL_CHAT_LOG =
            " select " +
                    " tc.ID as id,\n" +
                    " tp.ClanTag as clanTag, " +
                    " tc.logSoldierName as player , " +
                    " tc.logMessage as playerChat, " +
                    " tc.logDate as dateLog, " +
                    " tp.PBGUID as pbGuid, " +
                    " tp.EAGUID as eaGuid, " +
                    " tp.GlobalRank as patente, " +
                    " tp.CountryCode as country, " +
                    " tp.IP_Address as ipAddress " +
                    "from tbl_chatlog tc " +
                    "inner join tbl_playerdata tp ON " +
                    " tp.PlayerID = tc.logPlayerID " +
                    "where " +
                    " tc.logSubset = 'Global' and " +
                    " tc.ID > :lastId " +
                    " order by " +
                    " tc.logDate desc " +
                    "limit 10  ";

    private final ChatLogService chatLogService;

    public DiscordChatMessageServiceImpl(ChatLogService chatLogService) {
        this.chatLogService = chatLogService;
    }

    @Override
    public void execute() {
        List<ChatLogEntity> chatLogs = chatLogService.findAll();
        for (ChatLogEntity logEntity : chatLogs) {
            Integer lastId = sendMessageChatLogChannels(logEntity, SQL_CHAT_LOG);
            if (Objects.nonNull(lastId)){
                logEntity.setLastId(Long.valueOf(lastId));
                chatLogService.save(logEntity);
            }
        }
    }


    private Integer sendMessageChatLogChannels(ChatLogEntity chatLog, String sqlChatLog){
        String sql = StringUtils.replace(sqlChatLog,":lastId",
                Objects.isNull(chatLog.getLastId()) ? "0" : chatLog.getLastId().toString());
        List<ResponseChatLog> resultSet = getResultSet(chatLog.getIp(), String.valueOf(chatLog.getPort()),
                chatLog.getDatabase(), chatLog.getLogin(), chatLog.getPassword(), sql, DiscordPluginType.CHAT_LOG);
        if (!resultSet.isEmpty()){
            TextChannel textChannel = authenticationDiscord(chatLog.getDiscordToken(), chatLog.getDiscordIdChannel());
            if (Objects.nonNull(textChannel)) {
                sendChatLogsBatches(textChannel, resultSet);
                Optional<ResponseChatLog> lastRegister = resultSet.stream()
                        .max(Comparator.comparing(ResponseChatLog::getId));
                return lastRegister.get().getId();
            } else {
                System.out.println("Canal não encontrado!");
            }
        } else {
            System.out.println("Nenhuma mensgem nova encontrada!");
        }
        return null;
    }

    private void sendChatLogsBatches(TextChannel channel, List<ResponseChatLog> logMessages) {
        List<MessageEmbed> mensagens = new ArrayList<>();
        for (ResponseChatLog log : logMessages) {
            mensagens.add(createMessageEmbed(log));
        }
        channel.sendMessageEmbeds(mensagens).queue(success -> System.out.println("Mensagem enviada com sucesso!"),
                error -> System.err.println("Falha ao enviar mensagem: " + error.getMessage()));
    }

    private MessageEmbed createMessageEmbed(ResponseChatLog log) {

        java.util.List<MessageEmbed.Field> fields = new ArrayList<>();
        fields.add(new MessageEmbed.Field("Data", dateFormat.format(log.getDateLog()), false));
        fields.add(new MessageEmbed.Field("Player", log.getClanTag().isEmpty() ? log.getPlayer() : "["+log.getClanTag()+"] "+log.getPlayer(), false));
        fields.add(new MessageEmbed.Field("EA GUID", log.getEaGuid(), false));
        fields.add(new MessageEmbed.Field("PB GUID", StringUtils.isNotBlank(log.getPbGuid()) ? log.getPbGuid() : "NDA", false));
        fields.add(new MessageEmbed.Field("IP", log.getIpAddress(), false));
        fields.add(new MessageEmbed.Field("Country", Objects.nonNull(log.getCountry()) ? log.getCountry().toUpperCase() : "NA", false));
        fields.add(new MessageEmbed.Field("Rank", log.getPatente(), false));
        fields.add(new MessageEmbed.Field("Mensagem", String.format("``` %s ```", log.getPlayerChat()) , false));


        MessageEmbed.AuthorInfo authorInfo = new MessageEmbed.AuthorInfo("Author: André Jobim", "https://github.com/andrejobim","", "");
        MessageEmbed.Footer footer = new MessageEmbed.Footer("Visualização do Chat Log Online","","");
        MessageEmbed message = new MessageEmbed(
                null,"LOG CHAT",null,
                EmbedType.RICH,null,0x00FF00, null, null, authorInfo,null,footer,null, fields
        );
        return message;
    }

    @Override
    public String getNameService() {
        return "Discord Chat log";
    }
}
