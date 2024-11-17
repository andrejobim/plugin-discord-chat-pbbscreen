package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.ChatLogEntity;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.JoinPlayerServerEntity;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.PbbansScreenEntity;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.chatlog.ChatLogService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.joinplayer.JoinPlayerSevice;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.pbbans.PbbansScreenService;
import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthenticationDiscordServiceImpl implements AuthenticationDiscordService {

    private final  Map<Long, net.dv8tion.jda.api.JDA> CHAT_LOG_CONNECTIONS = new HashMap<>();
    private final  Map<Long, net.dv8tion.jda.api.JDA> PBBANS_SCREEN_CONNECTIONS = new HashMap<>();
    private final  Map<Long, net.dv8tion.jda.api.JDA> JOIN_PLAYER_CONNECTIONS = new HashMap<>();

    private final ChatLogService chatLogService;
    private final PbbansScreenService pbbansScreenService;
    private final JoinPlayerSevice joinPlayerSevice;

    public AuthenticationDiscordServiceImpl(ChatLogService chatLogService,
                                            PbbansScreenService pbbansScreenService,
                                            JoinPlayerSevice joinPlayerSevice) {
        this.chatLogService = chatLogService;
        this.pbbansScreenService = pbbansScreenService;
        this.joinPlayerSevice = joinPlayerSevice;
    }


    public Map<Long, JDA> getChatLogConnections() {
        return CHAT_LOG_CONNECTIONS;
    }

    public Map<Long, JDA> getDiscordJoinPlayerMessageService() {
        return JOIN_PLAYER_CONNECTIONS;
    }

    public Map<Long, JDA> getPbbansScreenConnections() {
        return PBBANS_SCREEN_CONNECTIONS;
    }

    @PostConstruct
    private void poulateConnection(){

        List<ChatLogEntity> chatLogEntityList = chatLogService.findAll();
        for (ChatLogEntity chatLogEntity : chatLogEntityList) {
            populateConnections(chatLogEntity.getDiscordToken(), CHAT_LOG_CONNECTIONS, chatLogEntity.getIdChatLog());
        }

        List<PbbansScreenEntity> pbbansScreenEntityList = pbbansScreenService.findAll();
        for (PbbansScreenEntity pbbansScreenEntity : pbbansScreenEntityList) {
            populateConnections(pbbansScreenEntity.getDiscordToken(), PBBANS_SCREEN_CONNECTIONS, pbbansScreenEntity.getIdPabbansScreen());
        }

        List<JoinPlayerServerEntity> joinPlayerServerEntityList = joinPlayerSevice.findAll();
        for (JoinPlayerServerEntity joinPlayerServer : joinPlayerServerEntityList) {
            populateConnections(joinPlayerServer.getDiscordToken(), JOIN_PLAYER_CONNECTIONS, joinPlayerServer.getIdJoinPlayerServer());
        }
    }

    private void populateConnections(String discordTokem, Map<Long, net.dv8tion.jda.api.JDA> connections, Long id){
        try {
            JDA jda = JDABuilder.createDefault(discordTokem)
                    .build();
            jda.awaitReady();
            connections.put(id, jda);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }
}
