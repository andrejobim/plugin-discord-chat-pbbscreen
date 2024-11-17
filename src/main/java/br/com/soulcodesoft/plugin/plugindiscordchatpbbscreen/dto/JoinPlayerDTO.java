package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.JoinPlayerServerEntity;

public class JoinPlayerDTO {

    private Long id;
    private String apiTokenBf4db;
    private String databaseName;
    private String discordIdChannel;
    private String discordToken;
    private String ip;
    private String ipPortServer;
    private String login;
    private String pass;
    private Integer port;
    private String nameBot;
    private String urlBot;
    private Boolean enable;

    public JoinPlayerDTO() {
    }

    public JoinPlayerDTO(JoinPlayerServerEntity joinP) {
        setId(joinP.getIdJoinPlayerServer());
        setApiTokenBf4db(joinP.getApiTokenBf4db());
        setDatabaseName(joinP.getDatabase());
        setDiscordIdChannel(joinP.getDiscordIdChannel());
        setDiscordToken(joinP.getDiscordToken());
        setIp(joinP.getIp());
        setIpPortServer(joinP.getIpPortServer());
        setLogin(joinP.getLogin());
        setPort(joinP.getPort());
        setPass(joinP.getPassword());
        setNameBot(joinP.getNameBot());
        setUrlBot(joinP.getUrlBot());
        setEnable(joinP.getEnable());
    }

    public String getApiTokenBf4db() {
        return apiTokenBf4db;
    }

    public void setApiTokenBf4db(String apiTokenBf4db) {
        this.apiTokenBf4db = apiTokenBf4db;
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

    public String getIpPortServer() {
        return ipPortServer;
    }

    public void setIpPortServer(String ipPortServer) {
        this.ipPortServer = ipPortServer;
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

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
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
