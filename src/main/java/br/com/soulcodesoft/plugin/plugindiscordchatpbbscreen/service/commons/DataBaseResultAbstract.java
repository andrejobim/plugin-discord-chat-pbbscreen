package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.JoinPlayerLog;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ResponseChatLog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DataBaseResultAbstract extends DataBaseAbstract {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected List getResponseChatLogResultSetDataBase(ResultSet rs) {
        List<ResponseChatLog> result = new ArrayList<>();
        ResponseChatLog responseChatLog;
        try {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String clanTag = rs.getString("clanTag");
                String player = rs.getString("player");
                String playerChat = rs.getString("playerChat");
                Timestamp dateLog = rs.getTimestamp("dateLog");
                String pbGuid = rs.getString("pbGuid");
                String eaGuid = rs.getString("eaGuid");
                String patente = rs.getString("patente");
                String country = rs.getString("country");
                String ip = rs.getString("ipAddress");
                responseChatLog = new ResponseChatLog(id, clanTag, player, playerChat, dateLog);
                responseChatLog.setPbGuid(pbGuid);
                responseChatLog.setEaGuid(eaGuid);
                responseChatLog.setPatente(patente);
                responseChatLog.setCountry(country);
                responseChatLog.setIpAddress(ip);
                result.add(responseChatLog);
            }
            result.sort(Comparator.comparing(ResponseChatLog::getDateLog));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    protected List getResponseJoinPlayerResultSetDataBase(ResultSet rs) {
        List<JoinPlayerLog> result = new ArrayList<>();
        JoinPlayerLog joinPlayerLog;
        try {
            while (rs.next()) {
                String serverName = rs.getString("serverName");
                String mode = rs.getString("mode");
                String mapa = rs.getString("mapa");
                String totalSlot = rs.getString("totalSlot");
                String totalLogged = rs.getString("totalLogged");
                String patente = rs.getString("patente");
                String tag = rs.getString("tag");
                String soldier = rs.getString("soldier");
                String pbGuid = rs.getString("pbGuid");
                String eaGuid = rs.getString("eaGuid");
                String serverIp = rs.getString("serverIp");
                String ping = rs.getString("ping");
                String datePlayerJoined = rs.getString("datePlayerJoined");
                String teamID = rs.getString("teamID");

                joinPlayerLog = new JoinPlayerLog();
                joinPlayerLog.setServerName(serverName);
                joinPlayerLog.setMode(mode);
                joinPlayerLog.setMapa(mapa);
                joinPlayerLog.setTotalSlot(totalSlot);
                joinPlayerLog.setTotalLogged(totalLogged);
                joinPlayerLog.setPatente(patente);
                joinPlayerLog.setTag(tag);
                joinPlayerLog.setSoldier(soldier);
                joinPlayerLog.setPbGuid(pbGuid);
                joinPlayerLog.setEaGuid(eaGuid);
                joinPlayerLog.setServerIpPort(serverIp);
                joinPlayerLog.setPing(ping);
                joinPlayerLog.setDatePlayerJoined(LocalDateTime.parse(datePlayerJoined, formatter));
                joinPlayerLog.setTeamID(teamID);
                result.add(joinPlayerLog);
            }
            result.sort(Comparator.comparing(JoinPlayerLog::getDatePlayerJoined));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
