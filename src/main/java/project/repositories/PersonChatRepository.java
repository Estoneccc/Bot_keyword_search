package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.domain.PersonChat;

import java.util.List;

@Repository
public interface PersonChatRepository extends JpaRepository<PersonChat, Long> {
    @Query("SELECT pc FROM PersonChat pc WHERE pc.userId = :userId")
    List<PersonChat> findAllPersonChatByUserId(@Param("userId") Long userId);
    PersonChat findPersonChatByUserIdAndChatId(Long userId, Long chatId);
    boolean existsByUserIdAndChatId(Long userId, Long chatId);
    @Query("SELECT pc.keyWords FROM PersonChat pc WHERE pc.userId = :userId and pc.chatId = :chatId")
    String findKeyWordsByUserIdAndChatId(@Param("userId") Long userId, @Param("chatId") Long chatId);
    @Query("SELECT pc FROM PersonChat pc WHERE pc.chatId = :chatId")
    List<PersonChat> findAllPersonChatByChatId(@Param("chatId") Long chatId);
    void deletePersonChatByChatId(Long chatId);
}
