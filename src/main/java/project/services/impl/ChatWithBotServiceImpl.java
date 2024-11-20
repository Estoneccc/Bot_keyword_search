package project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.domain.ChatWithBot;
import project.domain.iterator.ChatIterator;
import project.domain.iterator.ChatWithBotIterator;
import project.domain.mediator.ChatMediator;
import project.repositories.ChatWithBotRepository;
import project.services.ChatWithBotService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatWithBotServiceImpl implements ChatWithBotService {
    private final ChatWithBotRepository chatWithBotRepository;
    private final ChatMediator chatMediator;

    @Autowired
    public ChatWithBotServiceImpl(ChatWithBotRepository chatWithBotRepository, ChatMediator chatMediator) {
        this.chatWithBotRepository = chatWithBotRepository;
        this.chatMediator = chatMediator;
    }

    @Override
    public void saveChat(ChatWithBot chatWithBot) {
        chatWithBotRepository.save(chatWithBot);
        chatMediator.updateChats(); // Обновляем чаты через посредника
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
        chatMediator.updateChats(); // Удаляем чат и обновляем чаты через посредника
    }

    @Override
    public boolean isChatListEmpty() {
        return chatWithBotRepository.count() == 0;
    }
}