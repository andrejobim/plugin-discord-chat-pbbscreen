package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.joinplayer;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.JoinPlayerDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.JoinPlayerServerEntity;

import java.util.List;

public interface JoinPlayerSevice {

    List<JoinPlayerServerEntity> findAll();
    List<JoinPlayerDTO> findAllDTO();
    void save(JoinPlayerServerEntity joinPlayerServer);
    JoinPlayerServerEntity findById(Long id);
}
