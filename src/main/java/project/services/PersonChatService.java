package project.services;

import project.domain.PersonChat;

import java.util.List;

public interface PersonChatService {
    void saveChatForPerson(PersonChat personChat);
    boolean existsPersonChatByUserIdAndChatId(Long userId, Long chatId);
    List<PersonChat> findPersonChatBy(Long userId);
    void deletePersonChat(PersonChat personChat);
}
