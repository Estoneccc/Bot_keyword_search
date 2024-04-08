package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.ChatWithBot;

@Repository
public interface ChatForBotRepository extends JpaRepository<ChatWithBot, Long> {
}
