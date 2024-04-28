package project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.domain.PersonChat;
import project.repositories.PersonChatRepository;
import project.services.PersonChatService;

import java.util.List;
import java.util.Optional;

@Service
public class PersonChatServiceImpl implements PersonChatService {
    private final PersonChatRepository personChatRepository;

    @Autowired
    public PersonChatServiceImpl(PersonChatRepository personChatRepository) {
        this.personChatRepository = personChatRepository;
    }

    @Override
    public void saveChatForPerson(PersonChat personChat) {
        personChatRepository.save(personChat);
    }

    @Override
    public List<PersonChat> findAllPersonChatByChatId(Long chatId) {
        return personChatRepository.findAllPersonChatByChatId(chatId);
    }

    @Override
    public boolean existsPersonChatByUserIdAndChatId(Long userId, Long chatId) {
        return personChatRepository.existsByUserIdAndChatId(userId, chatId);
    }

    @Override
    public List<PersonChat> findAllPersonChatByUserId(Long userId) {
        return personChatRepository.findAllPersonChatByUserId(userId);
    }

    @Override
    public PersonChat findPersonChatByUserIdAndChatId(Long userId, Long chatId) {
        return personChatRepository.findPersonChatByUserIdAndChatId(userId, chatId);
    }

    @Override
    public void deletePersonChat(PersonChat personChat) {
        personChatRepository.delete(personChat);
    }

    @Override
    public void updateKeywordsForChat(Long userId, Long chatId, String keywords) {
        PersonChat personChat = personChatRepository.findPersonChatByUserIdAndChatId(userId, chatId);
        personChat.setKeyWords(keywords);
        personChatRepository.save(personChat);
    }

    @Override
    public String findKeyWordsByUserIdAndChatId(Long userId, Long chatId) {
        return personChatRepository.findKeyWordsByUserIdAndChatId(userId, chatId);
    }
}
