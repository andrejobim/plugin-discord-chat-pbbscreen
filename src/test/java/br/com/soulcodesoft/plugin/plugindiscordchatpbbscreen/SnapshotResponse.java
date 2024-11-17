package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen;

import java.util.Map;

public class SnapshotResponse {

    private long lastUpdated;
    private Snapshot snapshot;

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    public static class Snapshot {
        private String status;
        private long gameId;
        private String gameMode;
        private int mapVariant;
        private String currentMap;
        private int maxPlayers;
        private int waitingPlayers;
        private int roundTime;
        private int defaultRoundTimeMultiplier;
        private Map<String, Conquest> conquest;
        private Map<String, TeamInfo> teamInfo;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getGameId() {
            return gameId;
        }

        public void setGameId(long gameId) {
            this.gameId = gameId;
        }

        public String getGameMode() {
            return gameMode;
        }

        public void setGameMode(String gameMode) {
            this.gameMode = gameMode;
        }

        public int getMapVariant() {
            return mapVariant;
        }

        public void setMapVariant(int mapVariant) {
            this.mapVariant = mapVariant;
        }

        public String getCurrentMap() {
            return currentMap;
        }

        public void setCurrentMap(String currentMap) {
            this.currentMap = currentMap;
        }

        public int getMaxPlayers() {
            return maxPlayers;
        }

        public void setMaxPlayers(int maxPlayers) {
            this.maxPlayers = maxPlayers;
        }

        public int getWaitingPlayers() {
            return waitingPlayers;
        }

        public void setWaitingPlayers(int waitingPlayers) {
            this.waitingPlayers = waitingPlayers;
        }

        public int getRoundTime() {
            return roundTime;
        }

        public void setRoundTime(int roundTime) {
            this.roundTime = roundTime;
        }

        public int getDefaultRoundTimeMultiplier() {
            return defaultRoundTimeMultiplier;
        }

        public void setDefaultRoundTimeMultiplier(int defaultRoundTimeMultiplier) {
            this.defaultRoundTimeMultiplier = defaultRoundTimeMultiplier;
        }

        public Map<String, Conquest> getConquest() {
            return conquest;
        }

        public void setConquest(Map<String, Conquest> conquest) {
            this.conquest = conquest;
        }

        public Map<String, TeamInfo> getTeamInfo() {
            return teamInfo;
        }

        public void setTeamInfo(Map<String, TeamInfo> teamInfo) {
            this.teamInfo = teamInfo;
        }

        public static class Conquest {
            private int tickets;
            private int ticketsMax;

            public int getTickets() {
                return tickets;
            }

            public void setTickets(int tickets) {
                this.tickets = tickets;
            }

            public int getTicketsMax() {
                return ticketsMax;
            }

            public void setTicketsMax(int ticketsMax) {
                this.ticketsMax = ticketsMax;
            }
        }

        public static class TeamInfo {
            private int faction;
            private Map<String, Player> players;

            public int getFaction() {
                return faction;
            }

            public void setFaction(int faction) {
                this.faction = faction;
            }

            public Map<String, Player> getPlayers() {
                return players;
            }

            public void setPlayers(Map<String, Player> players) {
                this.players = players;
            }

            public static class Player {
                private String name;
                private String tag;
                private int rank;
                private int score;
                private int kills;
                private int deaths;
                private int squad;
                private int role;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public int getRank() {
                    return rank;
                }

                public void setRank(int rank) {
                    this.rank = rank;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public int getKills() {
                    return kills;
                }

                public void setKills(int kills) {
                    this.kills = kills;
                }

                public int getDeaths() {
                    return deaths;
                }

                public void setDeaths(int deaths) {
                    this.deaths = deaths;
                }

                public int getSquad() {
                    return squad;
                }

                public void setSquad(int squad) {
                    this.squad = squad;
                }

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }
            }
        }
    }
}
