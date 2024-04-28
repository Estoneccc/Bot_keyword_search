package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.domain.Person;
import project.processors.states.State;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p.activeChat FROM Person p WHERE p.chatId = :chatId")
    Long findActiveChatByUserId(@Param("chatId") Long chatId);
    @Query("SELECT p.state FROM Person p WHERE p.chatId = :chatId")
    State findStateByUserId(@Param("chatId") Long chatId);
}
