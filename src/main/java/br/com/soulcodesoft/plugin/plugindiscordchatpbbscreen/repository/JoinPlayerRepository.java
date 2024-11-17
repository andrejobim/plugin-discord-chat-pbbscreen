package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.repository;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.JoinPlayerServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinPlayerRepository extends JpaRepository<JoinPlayerServerEntity, Long> {
    List<JoinPlayerServerEntity> findAllByEnableIsTrue();

}
