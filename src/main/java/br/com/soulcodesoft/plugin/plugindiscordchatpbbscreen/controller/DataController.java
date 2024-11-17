package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.controller;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ChatLogDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.JoinPlayerDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.PbbansScreenDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataController {

    @GetMapping("/form/chatlog")
    public String formChatLog(Model model) {
        model.addAttribute("chatlog", new ChatLogDTO());
        return "add-chat-log-bot";
    }

    @GetMapping("/form/joinplayer")
    public String formJoinPlayer(Model model) {
        model.addAttribute("joinplayer", new JoinPlayerDTO());
        return "add-join-player-bot";
    }
    @GetMapping("/form/pbbans")
    public String formPbbans(Model model) {
        model.addAttribute("pbbans", new PbbansScreenDTO());
        return "add-pbbans-bot";
    }


}
