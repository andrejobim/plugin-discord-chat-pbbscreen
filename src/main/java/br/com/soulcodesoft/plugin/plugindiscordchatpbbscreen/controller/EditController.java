package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.controller;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ChatLogDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.JoinPlayerDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.PbbansScreenDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.chatlog.ChatLogService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.joinplayer.JoinPlayerSevice;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.pbbans.PbbansScreenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EditController {

    private final ChatLogService chatLogService;
    private final PbbansScreenService pbbansScreenService;
    private final JoinPlayerSevice joinPlayerSevice;

    public EditController(ChatLogService chatLogService,
                          PbbansScreenService pbbansScreenService,
                          JoinPlayerSevice joinPlayerSevice) {
        this.chatLogService = chatLogService;
        this.pbbansScreenService = pbbansScreenService;
        this.joinPlayerSevice = joinPlayerSevice;
    }

    @GetMapping("/edit/chat-log/{id}")
    public String editChatLog(@PathVariable Long id, Model model) {
      model.addAttribute("chatlog",
              new ChatLogDTO(chatLogService.findById(id)));
      return "add-chat-log-bot";
    }

    @GetMapping("/edit/join-player/{id}")
    public String editJoinPlayer(@PathVariable Long id, Model model) {
        model.addAttribute("joinPlayer", new JoinPlayerDTO(joinPlayerSevice.findById(id)));
        return "add-join-player-bot";
    }

    @GetMapping("/edit/pbbans-screen/{id}")
    public String editPbbansScreen(@PathVariable Long id, Model model) {
        model.addAttribute("pbbansScreen", new PbbansScreenDTO(pbbansScreenService.findById(id)));
        return "add-pbbans-bot";
    }
}
