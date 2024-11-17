package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.process;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.commons.DiscordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DynamicAsyncService {

    private static final Logger logger = LoggerFactory.getLogger(DynamicAsyncService.class);

    @Async("taskExecutor")
    public CompletableFuture<String> processTask(DiscordService discordService) {
        String taskName = discordService.getNameService();
        logger.info("Executing task: " + discordService.getNameService());
        System.out.println("Executing task: " + discordService.getNameService());
        discordService.execute();
        logger.info("Completed task: " + taskName);
        System.out.println("Completed task: " + taskName);
        return CompletableFuture.completedFuture(taskName + " Completed");
    }

}
