package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReadFeedRssSteam {

    public static void main(String[] args) {
        String url = ""; // Substitua pela URL desejada
        ObjectMapper objectMapper = new ObjectMapper();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder jsonBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line);
                }
                SnapshotResponse snapshotResponse = objectMapper.readValue(jsonBuilder.toString(), SnapshotResponse.class);
                String message = formatSnapshotMessage(snapshotResponse);
                System.out.println("Status: " + snapshotResponse.getSnapshot().getStatus());
                sendMessageToDiscord(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void sendMessageToDiscord(String message) {
        String discordWebhookUrl = ""; // Substitua pela URL do seu webhook

        // Escapa caracteres especiais que podem causar erro
        String escapedMessage = escapeJson(message);

        String json = "{\"content\": \"" + escapedMessage + "\"}";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(discordWebhookUrl);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(json));

            CloseableHttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 204) { // 204 significa sucesso e que não há conteúdo a retornar
                System.out.println("Erro ao enviar mensagem: " + statusCode);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                System.out.println("Resposta do Discord: " + responseBuilder.toString());
            } else {
                System.out.println("Mensagem enviada com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String escapeJson(String message) {
        return message.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }


    public static String formatSnapshotMessage(SnapshotResponse snapshotResponse) {
        StringBuilder messageBuilder = new StringBuilder();

        // Adiciona o status
        messageBuilder.append("**Status:** ").append(snapshotResponse.getSnapshot().getStatus()).append("\n");

        // Adiciona informações do jogo
        messageBuilder.append("**Game ID:** ").append(snapshotResponse.getSnapshot().getGameId()).append("\n");
        messageBuilder.append("**Game Mode:** ").append(snapshotResponse.getSnapshot().getGameMode()).append("\n");
        messageBuilder.append("**Current Map:** ").append(snapshotResponse.getSnapshot().getCurrentMap()).append("\n");
        messageBuilder.append("**Max Players:** ").append(snapshotResponse.getSnapshot().getMaxPlayers()).append("\n");
        messageBuilder.append("**Waiting Players:** ").append(snapshotResponse.getSnapshot().getWaitingPlayers()).append("\n");

        // Adiciona informações da conquista
        messageBuilder.append("**Conquest Tickets:**\n");
        snapshotResponse.getSnapshot().getConquest().forEach((key, value) -> {
            messageBuilder.append(" - **Team ").append(key).append(":** ");
            messageBuilder.append(value.getTickets()).append("/").append(value.getTicketsMax()).append("\n");
        });

        // Adiciona informações dos times
        messageBuilder.append("**Team Information:**\n");
        snapshotResponse.getSnapshot().getTeamInfo().forEach((teamId, teamInfo) -> {
            messageBuilder.append("**Team ").append(teamId).append(" (Faction: ").append(teamInfo.getFaction()).append("):**\n");
            teamInfo.getPlayers().forEach((playerId, player) -> {
                messageBuilder.append("  - **").append(player.getName()).append(" (").append(player.getTag()).append(")** - Rank: ")
                        .append(player.getRank()).append(", Score: ").append(player.getScore())
                        .append(", Kills: ").append(player.getKills()).append(", Deaths: ").append(player.getDeaths()).append("\n");
            });
        });

        return messageBuilder.toString();
    }
}
