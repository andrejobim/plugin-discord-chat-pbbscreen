package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.controller;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ChatLogDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.JoinPlayerDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.PbbansScreenDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class UpdateController {

    // Método POST para receber dados do CHAT BOT
    @RequestMapping(value = "/chat-bot", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<String> receiveChatBotData(@RequestBody ChatLogDTO payload) {
        // Aqui você pode processar os dados recebidos
        // Exemplo: salvar no banco de dados ou fazer outra lógica de negócio
        System.out.println("Dados do CHAT BOT recebidos: " + payload);
        return new ResponseEntity<>("Dados do CHAT BOT recebidos com sucesso!", HttpStatus.OK);
    }

    // Método POST para receber dados do JOIN PLAYER
    @PostMapping("/join-player")
    public ResponseEntity<String> receiveJoinPlayerData(@RequestBody JoinPlayerDTO payload) {
        System.out.println("Dados do JOIN PLAYER recebidos: " + payload);
        return new ResponseEntity<>("Dados do JOIN PLAYER recebidos com sucesso!", HttpStatus.OK);
    }

    // Método POST para receber dados do PBBANS SCREEN
    @PostMapping("/pbbans-screen")
    public ResponseEntity<String> receivePbbansScreenData(@RequestBody PbbansScreenDTO payload) {
        System.out.println("Dados do PBBANS SCREEN recebidos: " + payload);
        return new ResponseEntity<>("Dados do PBBANS SCREEN recebidos com sucesso!", HttpStatus.OK);
    }
}
