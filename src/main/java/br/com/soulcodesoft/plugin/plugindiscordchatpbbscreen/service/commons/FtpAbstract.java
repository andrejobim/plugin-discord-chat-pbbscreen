package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ResponsePbbansLog;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.PbbansScreenEntity;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FtpAbstract extends SftpAbstract {

    public List<ResponsePbbansLog> getResultSetFtp(PbbansScreenEntity pbbansScreenEntity) {

        String ftpHost = pbbansScreenEntity.getIp();
        String ftpUser = pbbansScreenEntity.getLogin();
        String ftpPassword = pbbansScreenEntity.getPassword();
        int ftpPort = pbbansScreenEntity.getPort();
        String remoteDir = pbbansScreenEntity.getPathRemote();

        FTPClient ftpClient = new FTPClient();
        List<ResponsePbbansLog> response = new ArrayList<>();
        try {
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUser, ftpPassword);

            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            ftpClient.changeWorkingDirectory(remoteDir);

            List<FTPFile> fileList = Arrays.stream(ftpClient.listFiles())
                    .filter(ftpFile -> ftpFile.getName().equals("pbsvss.htm"))
                    .collect(Collectors.toList());

            if (!fileList.isEmpty()){

                FTPFile ftpFile = fileList.get(0);

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(ftpClient.retrieveFileStream(ftpFile.getName())))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.add(getResultFormat(line));
                    }
                } finally {
                    ftpClient.completePendingCommand();
                }
                return response;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }



}
