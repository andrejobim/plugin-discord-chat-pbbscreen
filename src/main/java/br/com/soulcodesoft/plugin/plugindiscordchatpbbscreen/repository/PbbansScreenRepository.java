package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.repository;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.PbbansScreenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PbbansScreenRepository extends JpaRepository<PbbansScreenEntity, Long> {

    List<PbbansScreenEntity> findAllByEnableIsTrue();
}
