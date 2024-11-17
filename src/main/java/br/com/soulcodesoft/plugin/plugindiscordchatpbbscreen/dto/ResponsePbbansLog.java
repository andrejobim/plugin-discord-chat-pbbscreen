package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto;

import java.time.LocalDateTime;

public class ResponsePbbansLog {

    private String nameFile;
    private String player;
    private String guid;
    private LocalDateTime data;

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime  data) {
        this.data = data;
    }

    public String getImage() {
        return this.nameFile.replace(".htm",".png");
    }

}
