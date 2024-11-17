package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.chatlog;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ChatLogDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.ChatLogEntity;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.repository.ChatLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatLogServiceImpl implements ChatLogService {

    private final ChatLogRepository chatLogRepository;

    public ChatLogServiceImpl(ChatLogRepository chatLogRepository) {
        this.chatLogRepository = chatLogRepository;
    }

    @Override
    public List<ChatLogEntity> findAll(){
        return chatLogRepository.findAllByEnableIsTrue();
    }

    @Override
    public List<ChatLogDTO> findAllDTO() {
        return chatLogRepository.findAllByEnableIsTrue().stream()
                .map(chatLog -> new ChatLogDTO(chatLog))
                .collect(Collectors.toList());
    }

    @Override
    public ChatLogEntity findById(Long id) {
        return chatLogRepository.findById(id).get();
    }

    @Override
    public void save(ChatLogEntity logEntity) {
        chatLogRepository.save(logEntity);
    }

}
