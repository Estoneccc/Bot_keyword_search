package project.domain.iterator;

import project.domain.ChatWithBot;

public interface ChatIterator {
    boolean hasNext();
    ChatWithBot next();
}
