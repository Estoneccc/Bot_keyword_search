package project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.domain.ChatWithBot;
import project.repositories.ChatForBotRepository;
import project.services.ChatWithBotService;

import java.util.List;
import java.util.Optional;

@Service
public class ChatWithBotServiceImpl implements ChatWithBotService {
    private final ChatForBotRepository chatForBotRepository;

    @Autowired
    public ChatWithBotServiceImpl(ChatForBotRepository chatForBotRepository) {
        this.chatForBotRepository = chatForBotRepository;
    }

    @Override
    public void saveChat(ChatWithBot chatWithBot) {
        chatForBotRepository.save(chatWithBot);
    }

    @Override
    public boolean existsByChat(Long chatId) {
        return chatForBotRepository.existsById(chatId);
    }

    @Override
    public ChatWithBot findChatById(Long chatId) {
        Optional<ChatWithBot> chatWithBot = chatForBotRepository.findById(chatId);
        return chatWithBot.orElse(null);
    }

    @Override
    public List<ChatWithBot> findAllChat() {
        return chatForBotRepository.findAll();
    }

    @Override
    public void deleteChat(ChatWithBot chatWithBot) {
        chatForBotRepository.delete(chatWithBot);
    }
}
