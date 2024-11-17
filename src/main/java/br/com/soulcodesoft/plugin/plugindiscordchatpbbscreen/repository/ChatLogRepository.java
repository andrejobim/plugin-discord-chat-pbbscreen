package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.repository;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity.ChatLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatLogRepository extends JpaRepository<ChatLogEntity, Long> {

    List<ChatLogEntity> findAllByEnableIsTrue();
}
