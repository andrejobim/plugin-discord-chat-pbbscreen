package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bf4dbPlayerResponse {

    @JsonProperty("data")
    private PlayerData data;

    public PlayerData getData() {
        return data;
    }

    public void setData(PlayerData data) {
        this.data = data;
    }

    public static class PlayerData {
        @JsonProperty("player_id")
        private long playerId;

        @JsonProperty("name")
        private String name;

        @JsonProperty("is_banned")
        private int isBanned;

        @JsonProperty("ban_reason")
        private String banReason;

        @JsonProperty("ea_guid")
        private String eaGuid;

        @JsonProperty("pb_guid")
        private String pbGuid;

        @JsonProperty("cheat_score")
        private int cheatScore;

        @JsonProperty("created_at")
        private String createdAt;

        @JsonProperty("updated_at")
        private String updatedAt;

        // Getters e Setters
        public long getPlayerId() {
            return playerId;
        }

        public void setPlayerId(long playerId) {
            this.playerId = playerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIsBanned() {
            return isBanned;
        }

        public void setIsBanned(int isBanned) {
            this.isBanned = isBanned;
        }

        public String getBanReason() {
            return banReason;
        }

        public void setBanReason(String banReason) {
            this.banReason = banReason;
        }

        public String getEaGuid() {
            return eaGuid;
        }

        public void setEaGuid(String eaGuid) {
            this.eaGuid = eaGuid;
        }

        public String getPbGuid() {
            return pbGuid;
        }

        public void setPbGuid(String pbGuid) {
            this.pbGuid = pbGuid;
        }

        public int getCheatScore() {
            return cheatScore;
        }

        public void setCheatScore(int cheatScore) {
            this.cheatScore = cheatScore;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
