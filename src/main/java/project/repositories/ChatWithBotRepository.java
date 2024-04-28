package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.ChatWithBot;

@Repository
public interface ChatWithBotRepository extends JpaRepository<ChatWithBot, Long> {
}
