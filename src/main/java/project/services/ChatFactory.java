package project.services;

import project.domain.ChatEntity;
import project.domain.Human;

/**
 * Фабрика
 */
public interface ChatFactory {
    ChatEntity createChat(String name, Long chatId);
}