package project.domain.iterator;

import project.domain.ChatWithBot;

import java.util.List;

public interface ChatIterator {
    boolean hasNext();
    ChatWithBot next();
}