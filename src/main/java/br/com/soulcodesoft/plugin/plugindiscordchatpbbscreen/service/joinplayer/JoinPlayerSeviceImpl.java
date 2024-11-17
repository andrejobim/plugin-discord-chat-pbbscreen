package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.joinplayer;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.JoinPlayerDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.JoinPlayerServerEntity;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.repository.JoinPlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JoinPlayerSeviceImpl implements JoinPlayerSevice {

    private final JoinPlayerRepository joinPlayerRepository;

    public JoinPlayerSeviceImpl(JoinPlayerRepository joinPlayerRepository) {
        this.joinPlayerRepository = joinPlayerRepository;
    }

    @Override
    public List<JoinPlayerServerEntity> findAll(){
        return joinPlayerRepository.findAllByEnableIsTrue();
    }

    @Override
    public List<JoinPlayerDTO> findAllDTO() {
        return joinPlayerRepository.findAllByEnableIsTrue().stream()
                .map(joinP -> new JoinPlayerDTO(joinP))
                .collect(Collectors.toList());
    }

    @Override
    public void save(JoinPlayerServerEntity joinPlayerServer) {
        joinPlayerRepository.save(joinPlayerServer);
    }

    @Override
    public JoinPlayerServerEntity findById(Long id) {
        return joinPlayerRepository.findById(id).get();
    }
}
