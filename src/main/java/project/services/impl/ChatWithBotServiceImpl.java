package project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.domain.ChatWithBot;
import project.repositories.ChatWithBotRepository;
import project.services.ChatWithBotService;

import java.util.List;
import java.util.Optional;

@Service
public class ChatWithBotServiceImpl implements ChatWithBotService {
    private final ChatWithBotRepository chatWithBotRepository;

    @Autowired
    public ChatWithBotServiceImpl(ChatWithBotRepository chatWithBotRepository) {
        this.chatWithBotRepository = chatWithBotRepository;
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
