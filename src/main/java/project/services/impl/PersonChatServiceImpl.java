package project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.domain.PersonChat;
import project.repositories.PersonChatRepository;
import project.services.PersonChatService;

import java.util.List;

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
    public boolean existsPersonChatByUserIdAndChatId(Long userId, Long chatId) {
        return personChatRepository.existsByUserIdAndChatId(userId, chatId);
    }

    @Override
    public List<PersonChat> findPersonChatBy(Long userId) {
        return personChatRepository.findPersonChatByUserId(userId);
    }

    @Override
    public void deletePersonChat(PersonChat personChat) {
        personChatRepository.delete(personChat);
    }
}
