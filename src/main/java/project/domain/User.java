package project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class User {

    @Id
    private Long chatId;

    private String name;

    public User(Long chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }
    public User(){
    }

    public Long getChatId() {
        return chatId;
    }
}
