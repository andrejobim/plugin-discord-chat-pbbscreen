package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.discord;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.Bf4dbPlayerResponse;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.DiscordPluginType;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.JoinPlayerLog;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.JoinPlayerServerEntity;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.bf4db.RequestApiBf4dbService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.bf4db.RequestUrlBf4dbInfoService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons.DiscordAbstract;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.joinplayer.JoinPlayerSevice;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DiscordJoinPlayerMessageServiceImpl extends DiscordAbstract implements DiscordJoinPlayerMessageService {

    private final static String SQL_JOIN_PLAYER =
            " SELECT  " +
            " ts.ServerName as serverName, " +
            " ts.Gamemode as  mode, " +
            " ts.mapName as mapa, " +
            " ts.maxSlots as totalSlot, " +
            " ts.usedSlots as totalLogged, " +
            " tc.GlobalRank as patente, " +
            " tc.ClanTag as tag, " +
            " tc.Soldiername as soldier, " +
            " tc.PB_GUID as pbGuid, " +
            "    tc.EA_GUID as eaGuid, " +
            " ts.IP_Address as serverIp, " +
            " tc.Ping as ping, " +
            " tc.PlayerJoined as datePlayerJoined, " +
            " tc.TeamID as teamID " +
            " FROM tbl_currentplayers tc  " +
            " inner join tbl_server ts on ts.ServerID = tc.ServerID  " +
            " WHERE ts.ConnectionState = 'on' " +
            " order by tc.PlayerJoined desc" +
            " limit 32 ";

    private final JoinPlayerSevice joinPlayerSevice;
    private final RequestApiBf4dbService requestApiBf4dbService;
    private final RequestUrlBf4dbInfoService requestUrlBf4dbInfoService;

    public DiscordJoinPlayerMessageServiceImpl(JoinPlayerSevice joinPlayerSevice,
                                               RequestApiBf4dbService requestApiBf4dbService,
                                               RequestUrlBf4dbInfoService requestUrlBf4dbInfoService) {
        this.joinPlayerSevice = joinPlayerSevice;
        this.requestApiBf4dbService = requestApiBf4dbService;
        this.requestUrlBf4dbInfoService = requestUrlBf4dbInfoService;
    }

    @Override
    public void execute() {
        List<JoinPlayerServerEntity> joinPlayers = joinPlayerSevice.findAll();
        for (JoinPlayerServerEntity joinPlayer : joinPlayers) {
            LocalDateTime lastDateFile = sendMessage(joinPlayer);
            if (Objects.nonNull(lastDateFile)){
                joinPlayer.setLastDate(lastDateFile);
                joinPlayerSevice.save(joinPlayer);
            }
        }
    }

    private LocalDateTime sendMessage(JoinPlayerServerEntity joinPlayerServerEntity) {

        List<JoinPlayerLog> resultSet = getResultSet(joinPlayerServerEntity.getIp(), String.valueOf(joinPlayerServerEntity.getPort()),
                joinPlayerServerEntity.getDatabase(), joinPlayerServerEntity.getLogin(), joinPlayerServerEntity.getPassword(), SQL_JOIN_PLAYER, DiscordPluginType.JOIN_PLAYER);

        Optional<JoinPlayerLog> responseCrescente = resultSet.stream()
                .max(Comparator.comparing(JoinPlayerLog::getDatePlayerJoined));

        if (responseCrescente.isPresent()){

            TextChannel textChannel = authenticationDiscord
                    (joinPlayerServerEntity.getDiscordToken(), joinPlayerServerEntity.getDiscordIdChannel());
            if (Objects.nonNull(textChannel)) {

                JoinPlayerLog joinPlayerLog = responseCrescente.get();

                LocalDateTime ultimoRegistroGerado;
                if (Objects.isNull(joinPlayerServerEntity.getLastDate())){
                    ultimoRegistroGerado = joinPlayerLog.getDatePlayerJoined().minusMinutes(5);
                } else {
                    ultimoRegistroGerado = joinPlayerServerEntity.getLastDate();
                }

                List<JoinPlayerLog> joinFromLastMinutes = resultSet.stream()
                        .filter(file -> file.getDatePlayerJoined().isAfter(ultimoRegistroGerado))
                        .collect(Collectors.toList());

                sendJoinPlayerLogsBatches(textChannel, joinFromLastMinutes, joinPlayerServerEntity);

                return responseCrescente.get().getDatePlayerJoined();
            }

        }
        return joinPlayerServerEntity.getLastDate();
    }

    private void sendJoinPlayerLogsBatches(TextChannel channel, List<JoinPlayerLog> logMessages, JoinPlayerServerEntity joinPlayerServerEntity) {
        List<MessageEmbed> mensagens = new ArrayList<>();
        for (JoinPlayerLog log : logMessages) {
            MessageEmbed messageEmbed = createMessageEmbed(log, joinPlayerServerEntity);
            mensagens.add(messageEmbed);
        }

        if (!mensagens.isEmpty()){

            List<List<MessageEmbed>> sublistas = quebrarEmGrupos(mensagens, 6000);

            sublistas.forEach(sublista -> {
                channel.sendMessageEmbeds(sublista).queue(success -> System.out.println("Mensagem enviada com sucesso!"),
                        error -> System.err.println("Falha ao enviar mensagem: " + error.getMessage()));
            });

        } else {
            System.out.println("Nenhuma mensagem nova encontrada!");
        }
    }

    private static List<List<MessageEmbed>> quebrarEmGrupos(List<MessageEmbed> embeds, int limiteTotal) {
        List<List<MessageEmbed>> grupos = new ArrayList<>();
        List<MessageEmbed> grupoAtual = new ArrayList<>();
        int tamanhoAtual = 0;

        for (MessageEmbed embed : embeds) {
            int tamanhoEmbed = calcularTamanhoEmbed(embed);

            if (grupoAtual.size() < 10 && (tamanhoAtual + tamanhoEmbed) <= limiteTotal) {
                grupoAtual.add(embed);
                tamanhoAtual += tamanhoEmbed;
            } else {
                grupos.add(grupoAtual);
                grupoAtual = new ArrayList<>();
                grupoAtual.add(embed);
                tamanhoAtual = tamanhoEmbed;
            }
        }

        if (!grupoAtual.isEmpty()) {
            grupos.add(grupoAtual);
        }

        return grupos;
    }

    private MessageEmbed createMessageEmbed(JoinPlayerLog joinPlayerServer, JoinPlayerServerEntity joinPlayerServerEntity) {

        String userUrls = requestUrlBf4dbInfoService.getUserUrls(joinPlayerServer.getSoldier(), joinPlayerServerEntity.getIp());

        java.util.List<MessageEmbed.Field> fields = new ArrayList<>();
        fields.add(new MessageEmbed.Field("Date", joinPlayerServer.getDatePlayerJoined().toString(), false));
        fields.add(new MessageEmbed.Field("Server", joinPlayerServer.getServerName(), false));
        fields.add(new MessageEmbed.Field("Map", String.format("%s (%s)", joinPlayerServer.getMapa(), joinPlayerServer.getMode()), false));
        fields.add(new MessageEmbed.Field("Players", String.format("%s/%s",joinPlayerServer.getTotalSlot(), joinPlayerServer.getTotalLogged()), false));
        fields.add(new MessageEmbed.Field("GlobalRank", joinPlayerServer.getPatente(), false));
        fields.add(new MessageEmbed.Field("PlayerName", joinPlayerServer.getTag().isEmpty() ? joinPlayerServer.getSoldier() : "["+joinPlayerServer.getTag()+"] "+joinPlayerServer.getSoldier(), false));
        fields.add(new MessageEmbed.Field("EA GUID", joinPlayerServer.getEaGuid(), false));
        fields.add(new MessageEmbed.Field("PB GUID", joinPlayerServer.getPbGuid(), false));

        if (StringUtils.isNotBlank(joinPlayerServerEntity.getApiTokenBf4db())){
            Bf4dbPlayerResponse playerDataResponse = requestApiBf4dbService
                    .getPlayerData(requestUrlBf4dbInfoService.getUserCode(), joinPlayerServerEntity.getApiTokenBf4db());
            Bf4dbPlayerResponse.PlayerData playerData = playerDataResponse.getData();
            String status = playerData.getIsBanned() == -1 ? "CLEAN" : "BANNED";
            fields.add(new MessageEmbed.Field("BF4DB Status", status, false));
            fields.add(new MessageEmbed.Field("BF4DB Reason", playerData.getBanReason(), false));
            fields.add(new MessageEmbed.Field("BF4DB LastUpdate", playerData.getUpdatedAt(), false));
        }

        fields.add(new MessageEmbed.Field("IP", joinPlayerServer.getServerIpPort(), false));
        fields.add(new MessageEmbed.Field("Ping", joinPlayerServer.getPing(), false));
        fields.add(new MessageEmbed.Field("Team", getDescriptionByTeamId(joinPlayerServer.getTeamID()), false));
        fields.add(new MessageEmbed.Field("URLS", userUrls , false));

        MessageEmbed.AuthorInfo authorInfo = new MessageEmbed.AuthorInfo("Author: André Jobim", "https://github.com/andrejobim","", "");
        MessageEmbed.Footer footer = new MessageEmbed.Footer("Acompanhamento de jogador entrando no servidor","","");
        MessageEmbed message = new MessageEmbed(
                null,"New Join Player",null,
                EmbedType.RICH,null,0x00FF00, null, null, authorInfo,null,footer,null, fields
        );
        return message;
    }

    private static int calcularTamanhoEmbed(MessageEmbed embed) {
        int total = 0;

        // Adiciona o tamanho do título
        total += embed.getTitle() != null ? embed.getTitle().length() : 0;

        // Adiciona o tamanho da descrição
        total += embed.getDescription() != null ? embed.getDescription().length() : 0;

        // Adiciona o tamanho dos campos
        for (MessageEmbed.Field field : embed.getFields()) {
            total += field.getName().length() + field.getValue().length();
        }

        // Adiciona o tamanho do rodapé
        total += embed.getFooter() != null ? embed.getFooter().getText().length() : 0;

        return total;
    }

    private String getDescriptionByTeamId(String teamID) {
        switch (teamID) {
            case "0":
                return "EUA";
            case "1":
                return "RU - RUSSIA";
            case "2":
                return "CN - CHINA";
            default:
                return "Desconhecido";
        }
    }

    @Override
    public String getNameService() {
        return "Join New Player Server";
    }

}
