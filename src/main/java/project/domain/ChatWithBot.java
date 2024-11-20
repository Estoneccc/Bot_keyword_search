package project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chatWithBot")
public class ChatWithBot {

    @Id
    private Long chatId;
    private String firstName;

    private boolean active;

    public ChatWithBot(Long chatId, String firstName) {
        this.chatId = chatId;
        this.firstName = firstName;
    }

    public ChatWithBot() {

    }

    public Long getChatId() {
        return chatId;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
