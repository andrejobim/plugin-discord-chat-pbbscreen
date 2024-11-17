package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.process;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.discord.DiscordChatMessageService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.discord.DiscordJoinPlayerMessageService;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.discord.DiscordPbbansMessageService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DynamicDiscordAsync {
    private final DynamicAsyncService dynamicAsyncService;
    private final DiscordChatMessageService discordChatMessageService;
    private final DiscordPbbansMessageService discordPbbansMessageService;
    private final DiscordJoinPlayerMessageService discordJoinPlayerMessageService;

    public DynamicDiscordAsync(DynamicAsyncService dynamicAsyncService,
                               DiscordChatMessageService discordChatMessageService,
                               DiscordPbbansMessageService discordPbbansMessageService,
                               DiscordJoinPlayerMessageService discordJoinPlayerMessageService) {
        this.dynamicAsyncService = dynamicAsyncService;
        this.discordChatMessageService = discordChatMessageService;
        this.discordPbbansMessageService = discordPbbansMessageService;
        this.discordJoinPlayerMessageService = discordJoinPlayerMessageService;
    }

//    @Scheduled(initialDelay = 5000, fixedRate = Long.MAX_VALUE)
//    public void initialExecution() {
//        executePbbansTasks();
//        executeChatTasks();
//        executeJoinChatTasks();
//    }
//
//    @Scheduled(cron = "0 */5 * * * *")
//    public void executePbbansTasks() {
//        CompletableFuture<String> discordPbbans = dynamicAsyncService
//                .processTask(discordPbbansMessageService);
//        CompletableFuture.allOf(discordPbbans).join();
//    }
//    @Scheduled(cron = "0 */3 * * * *")
//    public void executeChatTasks() {
//        CompletableFuture<String> discordChat = dynamicAsyncService
//                .processTask(discordChatMessageService);
//        CompletableFuture.allOf(discordChat).join();
//    }
//
//    @Scheduled(cron = "0 */1 * * * *")
//    public void executeJoinChatTasks() {
//        CompletableFuture<String> discordChat = dynamicAsyncService
//                .processTask(discordJoinPlayerMessageService);
//        CompletableFuture.allOf(discordChat).join();
//    }
}
