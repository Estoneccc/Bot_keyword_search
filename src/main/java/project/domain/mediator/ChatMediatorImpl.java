package project.domain.mediator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.domain.ChatWithBot;
import project.domain.iterator.ChatIterator;
import project.domain.iterator.ChatWithBotIterator;
import project.services.ChatWithBotService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatMediatorImpl implements ChatMediator {
    private final ChatWithBotService chatService;
    private List<ChatWithBot> chatList = new ArrayList<>();

    @Autowired
    public ChatMediatorImpl(ChatWithBotService chatService) {
        this.chatService = chatService;
        updateChats();
    }

    @Override
    public void registerChat(ChatWithBot chat) {
        chatService.saveChat(chat);
        chatList.add(chat);
    }

    @Override
    public void removeChat(ChatWithBot chat) {
        chatService.deleteChat(chat);
        chatList.remove(chat);
    }

    @Override
    public ChatIterator getActiveChatIterator() {
        return new ChatWithBotIterator(chatList);
    }

    @Override
    public void updateChats() {
        chatList = chatService.findAllChat();
    }
}