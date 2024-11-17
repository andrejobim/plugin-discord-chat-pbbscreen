package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ProtocolPbbansType;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "pbbans_screen_entity", schema = "public")
public class PbbansScreenEntity {

    private Long idPabbansScreen;
    private String nameBot;
    private String urlBot;
    private String discordToken;
    private String discordIdChannel;
    private Integer port;
    private String ip;
    private String login;
    private String password;
    private String pathRemote;
    private String urlBase;
    private String ipPortServer;
    private LocalDateTime lastDate;
    private Boolean enable;
    private ProtocolPbbansType protocol;


    @Id
    @SequenceGenerator(allocationSize=1, name="pabbans_screen_seq", sequenceName="pabbans_screen_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pabbans_screen_seq")
    @Column(name = "id_pabbans_screen", unique = true, nullable = false)
    public Long getIdPabbansScreen() {
        return idPabbansScreen;
    }

    public void setIdPabbansScreen(Long idPabbansScreen) {
        this.idPabbansScreen = idPabbansScreen;
    }

    @Comment("Discord token: https://discord.com/developers/applications ")
    @Column(name = "discord_token")
    public String getDiscordToken() {
        return discordToken;
    }

    public void setDiscordToken(String discordToken) {
        this.discordToken = discordToken;
    }


    @Comment("Port database postgres, default 5432")
    @Column(name = "port")
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "protocol")
    public ProtocolPbbansType getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolPbbansType protocol) {
        this.protocol = protocol;
    }

    @Comment("IP database postgres")
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    @Comment("Login database postgres")
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Comment("Password database postgres")
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Comment("Path Remove SFTP")
    @Column(name = "path_remote")
    public String getPathRemote() {
        return pathRemote;
    }

    public void setPathRemote(String pathRemote) {
        this.pathRemote = pathRemote;
    }

    @Comment("id channel discord")
    @Column(name = "discord_id_channel", columnDefinition = "TEXT")
    public String getDiscordIdChannel() {
        return discordIdChannel;
    }

    public void setDiscordIdChannel(String discordIdChannel) {
        this.discordIdChannel = discordIdChannel;
    }
    @Comment("url base fragnet discord")
    @Column(name = "url_base", columnDefinition = "TEXT")
    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    @Comment("IP:PORTA Servidor")
    @Column(name = "ip_port_server", columnDefinition = "TEXT")
    public String getIpPortServer() {
        return ipPortServer;
    }

    public void setIpPortServer(String ipPortServer) {
        this.ipPortServer = ipPortServer;
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
