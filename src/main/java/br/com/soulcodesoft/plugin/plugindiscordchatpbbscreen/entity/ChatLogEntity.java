package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "chat_log_entity", schema = "public")
public class ChatLogEntity {

    private Long idChatLog;
    private String nameBot;
    private String urlBot;
    private String discordToken;
    private String discordIdChannel;
    private Integer port = 3306;
    private String ip;
    private String login;
    private String password;
    private String database;
    private String ipPortServer;
    private Long lastId = 0L;
    private Boolean enable;

    @Id
    @SequenceGenerator(allocationSize=1, name="chat_log_seq", sequenceName="chat_log_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_log_seq")
    @Column(name = "id_chat_log", unique = true, nullable = false)
    public Long getIdChatLog() {
        return idChatLog;
    }

    public void setIdChatLog(Long idChatLog) {
        this.idChatLog = idChatLog;
    }

    @Comment("id channel discord")
    @Column(name = "discord_id_channel", columnDefinition = "TEXT")
    public String getDiscordIdChannel() {
        return discordIdChannel;
    }

    public void setDiscordIdChannel(String discordIdChannel) {
        this.discordIdChannel = discordIdChannel;
    }

    @Comment("Discord token: https://discord.com/developers/applications ")
    @Column(name = "discord_token", columnDefinition = "TEXT")
    public String getDiscordToken() {
        return discordToken;
    }

    public void setDiscordToken(String discordToken) {
        this.discordToken = discordToken;
    }

    @Comment("Port database mysql, default 3306")
    @Column(name = "port")
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Comment("IP database mysql")
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    @Comment("Login database mysql")
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Comment("Password database mysql")
    @Column(name = "pass", columnDefinition = "TEXT")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Comment("nome database mysql")
    @Column(name = "database_name", columnDefinition = "TEXT")
    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }


    @Comment("Last id processed")
    @Column(name = "last_id")
    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

    @Comment("IP:PORTA Servidor")
    @Column(name = "ip_port_server", columnDefinition = "TEXT")
    public String getIpPortServer() {
        return ipPortServer;
    }

    public void setIpPortServer(String ipPortServer) {
        this.ipPortServer = ipPortServer;
    }

    @Column(name = "name_bot")
    public String getNameBot() {
        return nameBot;
    }

    public void setNameBot(String nameBot) {
        this.nameBot = nameBot;
    }

    @Comment("Url Generate in https://discord.com/developers/applications")
    @Column(name = "url_bot", columnDefinition = "TEXT")
    public String getUrlBot() {
        return urlBot;
    }

    public void setUrlBot(String urlBot) {
        this.urlBot = urlBot;
    }

    @Column(name = "enable")
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
