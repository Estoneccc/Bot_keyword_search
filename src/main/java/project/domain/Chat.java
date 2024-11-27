package project.domain;

import project.domain.observer.Observer;
import project.domain.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Паттерн Builder
 * Конкретный продукт
 */
public class Chat implements ChatEntity, Subject {

    private final List<Observer> observers = new ArrayList<>();
    private final String name;
    private final Long chatId;

    private Chat(String name, Long chatId) {
        this.name = name;
        this.chatId = chatId;
    }

    public static ChatBuilder having() {
        return new ChatBuilder();
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getChatId() {
        return chatId;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(String.format("Новое сообщение в чате '%s': %s", name, message));
        }
    }

    public static class ChatBuilder {

        private String name;
        private Long chatId;

        public ChatBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ChatBuilder chatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Chat done() {
            return new Chat(name, chatId);
        }
    }
}
