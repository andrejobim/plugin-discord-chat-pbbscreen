package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.pbbans;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.PbbansScreenDTO;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.PbbansScreenEntity;
import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.repository.PbbansScreenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PbbansScreenServiceImpl implements PbbansScreenService {

    private PbbansScreenRepository pbbansScreenRepository;

    public PbbansScreenServiceImpl(PbbansScreenRepository pbbansScreenRepository) {
        this.pbbansScreenRepository = pbbansScreenRepository;
    }

    @Override
    public List<PbbansScreenEntity> findAll() {
        return pbbansScreenRepository.findAllByEnableIsTrue();
    }

    @Override
    public List<PbbansScreenDTO> findAllDTO() {
        return pbbansScreenRepository.findAllByEnableIsTrue().stream()
                .map(pbbans -> new PbbansScreenDTO(pbbans))
                .collect(Collectors.toList());
    }

    @Override
    public void save(PbbansScreenEntity pbbansScreen) {
        pbbansScreenRepository.save(pbbansScreen);
    }

    @Override
    public PbbansScreenEntity findById(Long id) {
        return pbbansScreenRepository.findById(id).get();
    }
}
