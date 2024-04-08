package project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "personChat")
public class PersonChat {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long chatId;

    public PersonChat(Long userId, Long chatId) {
        this.userId = userId;
        this.chatId = chatId;
    }

    public PersonChat() {

    }

    public Long getUserId() {
        return userId;
    }

    public Long getChatId() {
        return chatId;
    }
}
