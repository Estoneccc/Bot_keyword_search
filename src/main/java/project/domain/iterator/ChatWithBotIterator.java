package project.domain.iterator;

import project.domain.ChatWithBot;

import java.util.List;

public class ChatWithBotIterator implements ChatIterator {
    private final List<ChatWithBot> chats;
    private int position = 0;

    // Конструктор для передачи списка чатов
    public ChatWithBotIterator(List<ChatWithBot> chats) {
        this.chats = chats;
    }

    @Override
    public boolean hasNext() {
        return position < chats.size();
    }

    @Override
    public ChatWithBot next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more chats available.");
        }
        return chats.get(position++);
    }
}
