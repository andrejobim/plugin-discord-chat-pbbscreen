package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.PbbansScreenEntity;

public class PbbansScreenDTO {

    private Long id;
    private String discordToken;
    private String ip;
    private String login;
    private String password;
    private String pathRemote;
    private Integer port;
    private String discordIdChannel;
    private String urlBase;
    private String ipPortServer;
    private String lastDate;
    private String nameBot;
    private String urlBot;
    private Boolean enable;
    private ProtocolPbbansType protocol;

    public PbbansScreenDTO() {
    }

    public PbbansScreenDTO(PbbansScreenEntity pbbans) {
        setId(pbbans.getIdPabbansScreen());
        setIp(pbbans.getIp());
        setLogin(pbbans.getLogin());
        setPassword(pbbans.getPassword());
        setPathRemote(pbbans.getPathRemote());
        setPort(pbbans.getPort());
        setDiscordIdChannel(pbbans.getDiscordIdChannel());
        setUrlBase(pbbans.getUrlBase());
        setIpPortServer(pbbans.getIpPortServer());
        setPort(pbbans.getPort());
        setNameBot(pbbans.getNameBot());
        setUrlBot(pbbans.getUrlBot());
        setEnable(pbbans.getEnable());
        setProtocol(pbbans.getProtocol());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPathRemote() {
        return pathRemote;
    }

    public void setPathRemote(String pathRemote) {
        this.pathRemote = pathRemote;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDiscordIdChannel() {
        return discordIdChannel;
    }

    public void setDiscordIdChannel(String discordIdChannel) {
        this.discordIdChannel = discordIdChannel;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public String getIpPortServer() {
        return ipPortServer;
    }

    public void setIpPortServer(String ipPortServer) {
        this.ipPortServer = ipPortServer;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
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

    public ProtocolPbbansType getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolPbbansType protocol) {
        this.protocol = protocol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
