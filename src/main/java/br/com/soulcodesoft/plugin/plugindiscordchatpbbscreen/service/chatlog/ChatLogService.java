package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.chatlog;


import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.ChatLogDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.ChatLogEntity;

import java.util.List;

public interface ChatLogService {

    List<ChatLogEntity> findAll();
    void save(ChatLogEntity logEntity);

    List<ChatLogDTO> findAllDTO();

    ChatLogEntity findById(Long id);

}
