package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.discord;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ProtocolPbbansType;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ResponsePbbansLog;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.PbbansScreenEntity;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons.AuthenticationDiscordService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons.DataBaseResultAbstract;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.pbbans.PbbansScreenService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DiscordPbbansMessageServiceImpl extends DataBaseResultAbstract implements DiscordPbbansMessageService{

    private final PbbansScreenService pbbansScreenService;
    private final AuthenticationDiscordService authenticationDiscordService;

    public DiscordPbbansMessageServiceImpl(PbbansScreenService pbbansScreenService,
                                           AuthenticationDiscordService authenticationDiscordService) {
        this.pbbansScreenService = pbbansScreenService;
        this.authenticationDiscordService = authenticationDiscordService;
    }

    @Override
    public void execute() {
        List<PbbansScreenEntity> pbbansScreens = pbbansScreenService.findAll();
        for (PbbansScreenEntity pbbansScreen : pbbansScreens) {
            LocalDateTime lastDateFile = sendMessagePbbansScrens(pbbansScreen);
            if (Objects.nonNull(lastDateFile)){
                pbbansScreen.setLastDate(lastDateFile);
                pbbansScreenService.save(pbbansScreen);
            }
        }
    }

    public LocalDateTime sendMessagePbbansScrens(PbbansScreenEntity pbbansScreenEntity){
        Map<Long, JDA> chatLogConnections = authenticationDiscordService.getPbbansScreenConnections();
        List<ResponsePbbansLog> resultSet = getResultByProtocol(pbbansScreenEntity);
        if (!resultSet.isEmpty()){
            JDA jda = chatLogConnections.get(pbbansScreenEntity.getIdPabbansScreen());
            TextChannel textChannel = jda.getTextChannelById(pbbansScreenEntity.getDiscordIdChannel());
            if (Objects.nonNull(textChannel)) {
                return sendPbbansScreenShotBatches(textChannel, resultSet, pbbansScreenEntity);
            } else {
                System.out.println("Canal não encontrado!");
            }
        } else {
            System.out.println("Nenhuma mensagem nova encontrada!");
        }
        return pbbansScreenEntity.getLastDate();
    }

    private List<ResponsePbbansLog> getResultByProtocol(PbbansScreenEntity pbbansScreenEntity) {
        if (ProtocolPbbansType.FTP.equals(pbbansScreenEntity.getProtocol())){
            return getResultSetFtp(pbbansScreenEntity);
        }
        return getResultSftp(pbbansScreenEntity);
    }

    private LocalDateTime sendPbbansScreenShotBatches(TextChannel textChannel, List<ResponsePbbansLog> resultSet, PbbansScreenEntity pbbansScreenEntity) {

        Optional<ResponsePbbansLog> responseCrescente = resultSet.stream()
                .max(Comparator.comparing(ResponsePbbansLog::getData));

        if (responseCrescente.isPresent()){
            ResponsePbbansLog responsePbbansLog = responseCrescente.get();

            LocalDateTime ultimoRegistroGerado;
            if (Objects.isNull(pbbansScreenEntity.getLastDate())){
                ultimoRegistroGerado = responsePbbansLog.getData().minusMinutes(5);
            } else {
                ultimoRegistroGerado = pbbansScreenEntity.getLastDate();
            }

            List<ResponsePbbansLog> filesFromLastHour = resultSet.stream()
                    .filter(file -> file.getData().isAfter(ultimoRegistroGerado))
                    .collect(Collectors.toList());

            for (ResponsePbbansLog pbbansLog : filesFromLastHour) {
                sendPbbansImage(pbbansLog,textChannel, pbbansScreenEntity.getUrlBase());
            }
            return responseCrescente.get().getData();
        }
        return pbbansScreenEntity.getLastDate();
    }

    private void sendPbbansImage(ResponsePbbansLog responsePbbansLog, TextChannel textChannel, String urlBase) {

        String imagePathOrUrl = urlBase+responsePbbansLog.getImage();

        java.util.List<MessageEmbed.Field> fields = new ArrayList<>();
        fields.add(new MessageEmbed.Field("GUID", responsePbbansLog.getGuid(), false));
        fields.add(new MessageEmbed.Field("Player", responsePbbansLog.getPlayer(), false));
        fields.add(new MessageEmbed.Field("Data", responsePbbansLog.getData().toString(), false));
        fields.add(new MessageEmbed.Field("URL", imagePathOrUrl, false));

        MessageEmbed.AuthorInfo authorInfo = new MessageEmbed.AuthorInfo("Author: André Jobim", "https://github.com/andrejobim/plugin-discord-chat-pbbscreen","", "");
        MessageEmbed.Footer footer = new MessageEmbed.Footer("Visualização do Chat Log Online","","");

        MessageEmbed embed = new MessageEmbed(
                imagePathOrUrl,
                "PunkBuster Screenshot",  // Título do embed
                "Visualização das imagens do PunkBuster", EmbedType.RICH,null,0x00FF00, null, null, authorInfo,null,footer,null, fields
        );

        try {
            InputStream imageStream = new URL(imagePathOrUrl).openStream();

            File tempImageFile = File.createTempFile("image", ".png");
            Files.copy(imageStream, tempImageFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            MessageAction messageAction = textChannel.sendMessageEmbeds(embed);
            messageAction.addFile(tempImageFile).queue();

            tempImageFile.deleteOnExit();
        } catch (IOException e) {
            System.out.println("Erro ao baixar a imagem: " + e.getMessage());
        }
    }

    @Override
    public String getNameService() {
        return "Discord Pbbans Message";
    }
}
