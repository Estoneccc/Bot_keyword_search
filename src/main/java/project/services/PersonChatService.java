package project.services;

import project.domain.PersonChat;

import java.util.List;

public interface PersonChatService {
    /**
     * Сохраняет отслеживаемый чат для пользователя в базу
     */
    void saveChatForPerson(PersonChat personChat);

    /**
     * Возвращает список чатов пользователей по идентификатору отслеживаемого чата
     */
    List<PersonChat> findAllPersonChatByChatId(Long chatId);

    /**
     * Проверка на существование активного чата по идентификатору пользователя и отслеживаемого чата
     */
    boolean existsPersonChatByUserIdAndChatId(Long userId, Long chatId);

    /**
     * Возвращает список активных чатов пользователя
     */
    List<PersonChat> findAllPersonChatByUserId(Long userId);

    /**
     * Возвращает активный чат по идентификатору пользователя и отслеживаемого чата
     */
    PersonChat findPersonChatByUserIdAndChatId(Long userId, Long chatId);

    /**
     * Удаляет активный чат из базы
     */
    void deletePersonChatByChatId(Long chatId);

    /**
     * Изменяет ключевые слова для активного чата пользователя
     */
    void updateKeywordsForChat(Long userId, Long chatId, String keywords);

    /**
     * Возвращает ключевые слова активного чата пользователя
     */
    String findKeyWordsByUserIdAndChatId(Long userId, Long chatId);
}
