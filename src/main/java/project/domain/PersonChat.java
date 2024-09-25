package project.domain;

import jakarta.persistence.*;

import java.util.List;

/**
 * Паттерн Static factory method
 */
@Entity
@Table(name = "personChat")
public class PersonChat {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long chatId;

    private String keyWords;

    PersonChat(Long userId, Long chatId) {
        this.userId = userId;
        this.chatId = chatId;
    }

    PersonChat() {

    }

    public static PersonChat getPersonChat(Long userId, Long chatId) {
        return new PersonChat(userId, chatId);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getKeyWords() {
        return keyWords;
    }
}
