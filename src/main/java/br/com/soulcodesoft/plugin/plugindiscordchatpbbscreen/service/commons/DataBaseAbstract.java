package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.DiscordPluginType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DataBaseAbstract extends FtpAbstract {

    protected abstract List getResponseChatLogResultSetDataBase(ResultSet rs);
    protected abstract List getResponseJoinPlayerResultSetDataBase(ResultSet rs);

    public List getResultSet(String ipAddress, String port, String dataBasename, String user, String password, String sqlRequest, DiscordPluginType discordPluginType){

        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false",ipAddress, port, dataBasename);

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List response = new ArrayList();

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida com sucesso!");

            stmt = conn.createStatement();

            rs = stmt.executeQuery(sqlRequest);

            return getResponseResult(rs, discordPluginType);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return response;
    }

    private List getResponseResult(ResultSet rs, DiscordPluginType discordPluginType) {
        if (DiscordPluginType.CHAT_LOG.equals(discordPluginType)){
            return getResponseChatLogResultSetDataBase(rs);
        }
        if (DiscordPluginType.JOIN_PLAYER.equals(discordPluginType)){
            return getResponseJoinPlayerResultSetDataBase(rs);
        }
        return null;
    }
}
