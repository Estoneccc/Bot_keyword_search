package project.services.impl;

import project.domain.Chat;
import project.domain.ChatEntity;
import project.services.ChatFactory;

/**
 * Конкретная фабрика
 */
public class DefaultChatFactory implements ChatFactory {
    @Override
    public ChatEntity createChat(String name, Long chatId) {
        return Chat.having().name(name).chatId(chatId).done();
    }
}
