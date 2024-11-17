package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.bf4db;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RequestUrlBf4dbInfoServiceImpl implements RequestUrlBf4dbInfoService {

    private String userCode;

    @Override
    public String getUserUrls(String nickName, String ipServer){
        String userCode = getUserBF4DB(nickName);
        if (Objects.nonNull(userCode)){
            setUserCode(userCode);
            return String.format(">>> BF4DB: https://bf4db.com/player/%s", userCode) + "\n"
                    + String.format(" BATTLELOG: https://battlelog.battlefield.com/bf4/soldier/%s/stats/%s/pc/", nickName, userCode) + "\n"
                    + String.format(" 247FAIRPLAY: https://www.247fairplay.com/CheatDetector/%s", nickName) + "\n"
                    + String.format(" BATTLEFIELD TRACKER: https://battlefieldtracker.com/bf4/profile/origin/%s/overview", nickName)+ "\n"
                    + String.format(" BF4CHEATREPORT: http://bf4cheatreport.com/?pid=%s&uid=&cnt=200&startdate=%s", userCode, getFormatDateNowYYYYMMDDHHMM()) + "\n"
                    + String.format(" IP-API.com: https://ip-api.com/#%s`", ipServer);
        }
        return StringUtils.EMPTY;
    }

    private String getUserBF4DB(String nickName) {
        try {
            String url = String.format( "https://bf4db.com/player/search?query=%s",nickName);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String htmlContent = response.toString();
            return extractHref(htmlContent);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao buscar as informações do player");
        }
        return null;
    }

    private String extractHref(String html) {
        String regex = "<td class=\"player-td-name\">\\s*<a href=\"([^\"]+)\">\\s*([\\w]+)\\s*</a>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String href = matcher.group(1);
            return href.replace("/player/","");
        }
        return null;
    }

    private String getFormatDateNowYYYYMMDDHHMM(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return now.format(formatter);
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
