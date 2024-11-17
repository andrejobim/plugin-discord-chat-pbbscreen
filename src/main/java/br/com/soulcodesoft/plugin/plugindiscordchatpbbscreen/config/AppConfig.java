package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.Executor;

@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        "br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.repository"
})
@EntityScan(basePackages = {
        "br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity"
})
@ComponentScan(basePackages = {
        "br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.controller",
        "br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service"
})
@Configuration
@EnableAsync
@EnableScheduling
public class AppConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
