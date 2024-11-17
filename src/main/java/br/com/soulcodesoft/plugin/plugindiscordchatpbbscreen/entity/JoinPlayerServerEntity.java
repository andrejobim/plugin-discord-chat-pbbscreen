package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "join_player_server_entity", schema = "public")
public class JoinPlayerServerEntity {

    private Long idJoinPlayerServer;
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
    private String apiTokenBf4db;
    private LocalDateTime lastDate;
    private Boolean enable;


    @Id
    @SequenceGenerator(allocationSize=1, name="join_player_server_seq", sequenceName="join_player_server_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "join_player_server_seq")
    @Column(name = "join_player_server_log", unique = true, nullable = false)
    public Long getIdJoinPlayerServer() {
        return idJoinPlayerServer;
    }

    public void setIdJoinPlayerServer(Long idJoinPlayerServer) {
        this.idJoinPlayerServer = idJoinPlayerServer;
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


    @Comment("IP:PORTA Servidor")
    @Column(name = "ip_port_server", columnDefinition = "TEXT")
    public String getIpPortServer() {
        return ipPortServer;
    }

    public void setIpPortServer(String ipPortServer) {
        this.ipPortServer = ipPortServer;
    }


    @Comment("Api Token BF4DB - Patreon")
    @Column(name = "api_token_bf4db", columnDefinition = "TEXT")
    public String getApiTokenBf4db() {
        return apiTokenBf4db;
    }

    public void setApiTokenBf4db(String apiTokenBf4db) {
        this.apiTokenBf4db = apiTokenBf4db;
    }

    @Comment("date of the last file imported")
    @Column(name = "last_date", columnDefinition = "TEXT")
    public LocalDateTime getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDateTime lastDate) {
        this.lastDate = lastDate;
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
