package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.controller;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ChangePasswordForm;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.User;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.chatlog.ChatLogService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons.UserService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.joinplayer.JoinPlayerSevice;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.pbbans.PbbansScreenService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    private final ChatLogService chatLogService;
    private final PbbansScreenService pbbansScreenService;
    private final JoinPlayerSevice joinPlayerSevice;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, ChatLogService chatLogService, PbbansScreenService pbbansScreenService, JoinPlayerSevice joinPlayerSevice, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.chatLogService = chatLogService;
        this.pbbansScreenService = pbbansScreenService;
        this.joinPlayerSevice = joinPlayerSevice;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }



    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "add-new-user";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        user.setRole("ROLE_USER");
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("changePasswordForm", new ChangePasswordForm());
        return "change-password";
    }

    @GetMapping("/list")
    public String showTables(Model model) {
        model.addAttribute("chatBots", chatLogService.findAllDTO());
        model.addAttribute("pbbansScreens", pbbansScreenService.findAllDTO());
        model.addAttribute("joinPlayers", joinPlayerSevice.findAllDTO());
        return "list-discord-bots";
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute("changePasswordForm") ChangePasswordForm form,
                                 BindingResult result,
                                 Authentication authentication) {
        if (result.hasErrors()) {
            return "change-password";
        }

        User user = userService.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verifica a senha antiga
        if (!passwordEncoder.matches(form.getOldPassword(), user.getPassword())) {
            result.rejectValue("oldPassword", "error.changePasswordForm", "Senha antiga incorreta.");
            return "change-password";
        }

        // Verifica se as novas senhas correspondem
        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            result.rejectValue("newPassword", "error.changePasswordForm", "As novas senhas não correspondem.");
            return "change-password";
        }

        // Atualiza a senha
        user.setPassword(passwordEncoder.encode(form.getNewPassword()));
        userService.save(user);

        return "redirect:/"; // Redireciona para a página inicial após a alteração
    }
}
