package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.discord.DiscordChatMessageService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.discord.DiscordJoinPlayerMessageService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.discord.DiscordPbbansMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class PluginDiscordChatPbbscreenApplicationTests {

    private final DiscordChatMessageService discordMessageService;
    private final DiscordPbbansMessageService discordPbbansMessageService;
    private final DiscordJoinPlayerMessageService discordJoinPlayerMessageService;

//    @Autowired
    PluginDiscordChatPbbscreenApplicationTests(DiscordChatMessageService discordMessageService,
                                               DiscordPbbansMessageService discordPbbansMessageService,
                                               DiscordJoinPlayerMessageService discordJoinPlayerMessageService) {
        this.discordMessageService = discordMessageService;
        this.discordPbbansMessageService = discordPbbansMessageService;
        this.discordJoinPlayerMessageService = discordJoinPlayerMessageService;
    }

//    @Test
    void send_discord_mensagem_chat_log() {
        discordMessageService.execute();
    }

//    @Test
    void send_discord_mensagem_pbbans_screenshot() {
        discordPbbansMessageService.execute();
    }
//    @Test
    void send_discord_mensagem_join_player() {
        discordJoinPlayerMessageService.execute();
    }

}
