package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons;

import net.dv8tion.jda.api.JDA;

import java.util.Map;

public interface AuthenticationDiscordService {

    Map<Long, JDA> getChatLogConnections();

    Map<Long, JDA> getDiscordJoinPlayerMessageService();

    Map<Long, JDA> getPbbansScreenConnections();
}
