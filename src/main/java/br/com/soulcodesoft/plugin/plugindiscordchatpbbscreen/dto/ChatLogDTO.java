package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.ChatLogEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatLogDTO {

    private Long id;
    @JsonProperty("database_name")
    private String databaseName;
    @JsonProperty("discord_id_channel")
    private String discordIdChannel;
    @JsonProperty("discord_token")
    private String discordToken;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("login")
    private String login;
    @JsonProperty("pass")
    private String pass;
    @JsonProperty("port")
    private String port;
    @JsonProperty("ip_port_server")
    private String ipPortServer;
    @JsonProperty("name_bot")
    private String nameBot;
    @JsonProperty("urlBot")
    private String urlBot;
    @JsonProperty("enable")
    private Boolean enable;

    public ChatLogDTO() {
    }

    public ChatLogDTO(ChatLogEntity chatLog) {
        setId(chatLog.getIdChatLog());
        setDatabaseName(chatLog.getDatabase());
        setDiscordIdChannel(chatLog.getDiscordIdChannel());
        setDiscordToken(chatLog.getDiscordToken());
        setIp(chatLog.getIp());
        setLogin(chatLog.getLogin());
        setPort(chatLog.getPort().toString());
        setPass(chatLog.getPassword());
        setIpPortServer(chatLog.getIpPortServer());
        setNameBot(chatLog.getNameBot());
        setUrlBot(chatLog.getUrlBot());
        setEnable(chatLog.getEnable());
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDiscordIdChannel() {
        return discordIdChannel;
    }

    public void setDiscordIdChannel(String discordIdChannel) {
        this.discordIdChannel = discordIdChannel;
    }

    public String getDiscordToken() {
        return discordToken;
    }

    public void setDiscordToken(String discordToken) {
        this.discordToken = discordToken;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIpPortServer() {
        return ipPortServer;
    }

    public void setIpPortServer(String ipPortServer) {
        this.ipPortServer = ipPortServer;
    }

    public String getNameBot() {
        return nameBot;
    }

    public void setNameBot(String nameBot) {
        this.nameBot = nameBot;
    }

    public String getUrlBot() {
        return urlBot;
    }

    public void setUrlBot(String urlBot) {
        this.urlBot = urlBot;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
