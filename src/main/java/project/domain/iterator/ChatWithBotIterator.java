package project.domain.iterator;

import project.domain.ChatWithBot;

import java.util.List;

public class ChatWithBotIterator implements ChatIterator{
    private final List<ChatWithBot> chats;
    private int position = 0;

    public ChatWithBotIterator(List<ChatWithBot> chats) {
        this.chats = chats;
    }

    @Override
    public boolean hasNext() {
        while (position < chats.size()) {
            ChatWithBot chat = chats.get(position);
            if (chat.isActive()) {
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public ChatWithBot next() {
        return null;
    }
}
