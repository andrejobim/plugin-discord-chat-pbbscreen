package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ResponsePbbansLog;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.PbbansScreenEntity;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.stream.Collectors;

public class SftpAbstract extends  FtpOrSftFormatAbstact  {

    public List<ResponsePbbansLog> getResultSftp(PbbansScreenEntity pbbansScreenEntity) {
        String sftpHost = pbbansScreenEntity.getIp();
        String sftpUser = pbbansScreenEntity.getLogin();
        String sftpPassword = pbbansScreenEntity.getPassword();
        int sftpPort = pbbansScreenEntity.getPort();
        String remoteDir = pbbansScreenEntity.getPathRemote();

        Session session = null;
        ChannelSftp channelSftp = null;
        List<ResponsePbbansLog> response = new ArrayList<>();
        try {

            JSch jsch = new JSch();
            session = jsch.getSession(sftpUser, sftpHost, sftpPort);
            session.setPassword(sftpPassword);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            Vector<ChannelSftp.LsEntry> fileList = channelSftp.ls(remoteDir);
            List<ChannelSftp.LsEntry> files = fileList.stream()
                    .filter(ftpFile -> ftpFile.getFilename().equals("pbsvss.htm"))
                    .collect(Collectors.toList());

            if (!files.isEmpty()) {
                ChannelSftp.LsEntry fileEntry = files.get(0);

                try {
                    InputStream inputStream = channelSftp.get(remoteDir+fileEntry.getFilename());

                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.add(getResultFormat(line));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        return response;
    }
}
