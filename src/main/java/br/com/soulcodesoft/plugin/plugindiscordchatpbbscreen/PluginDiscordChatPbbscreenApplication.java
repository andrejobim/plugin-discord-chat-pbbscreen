package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PluginDiscordChatPbbscreenApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppConfig.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}
