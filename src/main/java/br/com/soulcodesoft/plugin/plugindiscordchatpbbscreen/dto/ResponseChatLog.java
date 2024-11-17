package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto;

import java.sql.Timestamp;

public class ResponseChatLog {

    private Integer id;
    private String clanTag;
    private String player;
    private String playerChat;
    private Timestamp dateLog;
    private String pbGuid;
    private String eaGuid;
    private String patente;
    private String country;
    private String ipAddress;
    public ResponseChatLog(Integer id, String clanTag, String player, String playerChat, Timestamp dateLog) {
        this.id = id;
        this.clanTag = clanTag;
        this.player = player;
        this.playerChat = playerChat;
        this.dateLog = dateLog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClanTag() {
        return clanTag;
    }

    public void setClanTag(String clanTag) {
        this.clanTag = clanTag;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPlayerChat() {
        return playerChat;
    }

    public void setPlayerChat(String playerChat) {
        this.playerChat = playerChat;
    }

    public Timestamp getDateLog() {
        return dateLog;
    }

    public void setDateLog(Timestamp dateLog) {
        this.dateLog = dateLog;
    }

    public String getPbGuid() {
        return pbGuid;
    }

    public void setPbGuid(String pbGuid) {
        this.pbGuid = pbGuid;
    }

    public String getEaGuid() {
        return eaGuid;
    }

    public void setEaGuid(String eaGuid) {
        this.eaGuid = eaGuid;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
