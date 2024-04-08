package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.PersonChat;

import java.util.List;

@Repository
public interface PersonChatRepository extends JpaRepository<PersonChat, Long> {
    List<PersonChat> findPersonChatByUserId(Long userId);
    boolean existsByUserIdAndChatId(Long userId, Long chatId);
}
