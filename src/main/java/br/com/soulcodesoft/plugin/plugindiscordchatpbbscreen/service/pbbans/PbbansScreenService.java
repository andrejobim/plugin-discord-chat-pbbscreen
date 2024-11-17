package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.pbbans;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.PbbansScreenDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.PbbansScreenEntity;

import java.util.List;

public interface PbbansScreenService {
    List<PbbansScreenEntity> findAll();

    List<PbbansScreenDTO> findAllDTO();
    void save(PbbansScreenEntity pbbansScreen);

    PbbansScreenEntity findById(Long id);
}
