package project.domain.mediator;

import project.domain.ChatWithBot;
import project.domain.iterator.ChatIterator;
import project.services.ChatWithBotService;

public interface ChatMediator {
    void registerChat(ChatWithBot chat);
    void removeChat(ChatWithBot chat);
    ChatIterator getActiveChatIterator();
    void updateChats();
}