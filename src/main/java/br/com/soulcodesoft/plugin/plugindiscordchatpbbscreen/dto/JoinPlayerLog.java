package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JoinPlayerLog {

    private String serverName;
    private String mode;
    private String mapa;
    private String totalSlot;
    private String totalLogged;
    private String patente;
    private String tag;
    private String soldier;
    private String pbGuid;
    private String eaGuid;
    private String serverIpPort;
    private String ipAddress;
    private String ping;
    private LocalDateTime datePlayerJoined;
    private String teamID;

    public JoinPlayerLog() {
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public String getTotalSlot() {
        return totalSlot;
    }

    public void setTotalSlot(String totalSlot) {
        this.totalSlot = totalSlot;
    }

    public String getTotalLogged() {
        return totalLogged;
    }

    public void setTotalLogged(String totalLogged) {
        this.totalLogged = totalLogged;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSoldier() {
        return soldier;
    }

    public void setSoldier(String soldier) {
        this.soldier = soldier;
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

    public String getServerIpPort() {
        return serverIpPort;
    }

    public void setServerIpPort(String serverIpPort) {
        this.serverIpPort = serverIpPort;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public LocalDateTime getDatePlayerJoined() {
        return datePlayerJoined;
    }

    public void setDatePlayerJoined(LocalDateTime datePlayerJoined) {
        this.datePlayerJoined = datePlayerJoined;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }
}
