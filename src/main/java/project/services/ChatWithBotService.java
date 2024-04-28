package project.services;


import project.domain.ChatWithBot;

import java.util.List;

public interface ChatWithBotService {
    void saveChat(ChatWithBot chatWithBot);
    boolean existsByChat(Long chatId);
    ChatWithBot findChatById(Long chatId);
    List<ChatWithBot> findAllChat();
    void deleteChat(ChatWithBot chatWithBot);
    boolean isChatListEmpty();
}
