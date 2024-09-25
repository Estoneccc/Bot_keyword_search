package project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import project.processors.states.State;


/**
 * Паттерн Prototype
  */
@Entity
@Table(name = "person")
public class Person {

    @Id
    private Long chatId;

    private String name;

    private State state;

    private Long activeChat;

    public Person() {
    }

    public Person(Long chatId, String name) {
        this.chatId = chatId;
        this.name = name;
        this.state = State.START;
    }

    private Person(Person prototype) {
        this.state = prototype.getState();
        this.name = prototype.getName();
        this.chatId = prototype.getChatId();
        this.activeChat = prototype.getActiveChat();
    }

    public Person newPerson() {
        return new Person(this);
    }

    public String getName() {
        return name;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public Long getActiveChat() {
        return activeChat;
    }

    public void setActiveChat(Long activeChat) {
        this.activeChat = activeChat;
    }
}
