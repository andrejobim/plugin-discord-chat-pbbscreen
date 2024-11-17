package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons.DataBaseResultAbstract;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.security.auth.login.LoginException;
import java.text.SimpleDateFormat;

public abstract class DiscordAbstract extends DataBaseResultAbstract {

    public final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TextChannel authenticationDiscord(String discordToken, String discordIdChannel){
        try {
            JDA jda = JDABuilder.createDefault(discordToken).build();
            jda.awaitReady();
            TextChannel channel = jda.getTextChannelById(discordIdChannel);
            return channel;
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
