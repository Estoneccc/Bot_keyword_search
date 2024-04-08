package project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chatForBot")
public class ChatWithBot {

    @Id
    private Long chatId;
    private String firstName;

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
}
