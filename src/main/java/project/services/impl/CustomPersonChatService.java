package project.services.impl;

import project.domain.PersonChat;
import project.repositories.PersonChatRepository;
import project.services.PersonChatService;

import java.util.List;

/**
 * Утечненная абстракция
 */
public class CustomPersonChatService extends PersonChatServiceImpl {

    public CustomPersonChatService(PersonChatRepository personChatRepository) {
        super(personChatRepository);
    }

    public void customMethod(Long userId) {
        List<PersonChat> personChats = findAllPersonChatByUserId(userId);
        personChats.forEach(pc -> System.out.println("Custom handling for PersonChat with ID: " + pc.getChatId()));
    }
}
