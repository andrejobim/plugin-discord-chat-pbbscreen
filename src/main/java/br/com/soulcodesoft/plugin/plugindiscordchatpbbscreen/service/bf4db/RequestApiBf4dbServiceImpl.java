package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.bf4db;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.Bf4dbPlayerResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Component
public class RequestApiBf4dbServiceImpl implements RequestApiBf4dbService {

    @Override
    public Bf4dbPlayerResponse getPlayerData(String userCode, String apiToken) {
        String apiUrl = String.format("https://bf4db.com/api/player/%s?api_token=%s",userCode, apiToken );
        try {
            URL url =  new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(connection.getInputStream(), Bf4dbPlayerResponse.class);
            } else {
                throw new RuntimeException("Falha na requisição: " + connection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
