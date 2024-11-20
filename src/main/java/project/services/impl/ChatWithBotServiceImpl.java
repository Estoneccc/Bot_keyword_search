package project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.domain.ChatWithBot;
import project.domain.iterator.ChatIterator;
import project.domain.iterator.ChatWithBotIterator;
import project.repositories.ChatWithBotRepository;
import project.services.ChatWithBotService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatWithBotServiceImpl implements ChatWithBotService {
    private final ChatWithBotRepository chatWithBotRepository;

    private final List<ChatWithBot> chatList = new ArrayList<>();

    @Autowired
    public ChatWithBotServiceImpl(ChatWithBotRepository chatWithBotRepository) {
        this.chatWithBotRepository = chatWithBotRepository;
    }

    public ChatIterator getActiveChatIterator() {
        return new ChatWithBotIterator(chatList);
    }

    @Override
    public void saveChat(ChatWithBot chatWithBot) {
        chatWithBotRepository.save(chatWithBot);
    }

    @Override
    public boolean existsByChat(Long chatId) {
        return chatWithBotRepository.existsById(chatId);
    }

    @Override
    public ChatWithBot findChatById(Long chatId) {
        Optional<ChatWithBot> chatWithBot = chatWithBotRepository.findById(chatId);
        return chatWithBot.orElse(null);
    }

    @Override
    public List<ChatWithBot> findAllChat() {
        return chatWithBotRepository.findAll();
    }

    @Override
    public void deleteChat(ChatWithBot chatWithBot) {
        chatWithBotRepository.delete(chatWithBot);
    }

    @Override
    public boolean isChatListEmpty() {
        return chatWithBotRepository.count() == 0;
    }
}
