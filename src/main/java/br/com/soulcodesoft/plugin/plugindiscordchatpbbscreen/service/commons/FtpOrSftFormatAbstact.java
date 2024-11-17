package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ResponsePbbansLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class FtpOrSftFormatAbstact {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ResponsePbbansLog getResultFormat(String html) {

        ResponsePbbansLog responsePbbansLog = new ResponsePbbansLog();

        String hrefPattern = "href=(\\S+)";
        String nomePattern = "\"([^\"]+)\"";
        String guidPattern = "GUID=([a-fA-F0-9]{32})";
        String dataPattern = "\\[(\\d{4}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2}:\\d{2})\\]";

        Matcher hrefMatcher = Pattern.compile(hrefPattern).matcher(html);
        if (hrefMatcher.find()) {
            responsePbbansLog.setNameFile(hrefMatcher.group(1));
        }

        Matcher nomeMatcher = Pattern.compile(nomePattern).matcher(html);
        if (nomeMatcher.find()) {
            responsePbbansLog.setPlayer(nomeMatcher.group(1));
        }

        Matcher guidMatcher = Pattern.compile(guidPattern).matcher(html);
        if (guidMatcher.find()) {
            responsePbbansLog.setGuid(guidMatcher.group(1));
        }

        Matcher dataMatcher = Pattern.compile(dataPattern).matcher(html);
        if (dataMatcher.find()) {
            String dataDefault = dataMatcher.group(1);
            dataDefault = dataDefault.replace(".","-");
            responsePbbansLog.setData(LocalDateTime.parse(dataDefault, formatter));
        }
        return responsePbbansLog;
    }
}
