package project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person {

    @Id
    private Long chatId;

    private String name;

    public Person(Long chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }
    public Person(){
    }

    public Long getChatId() {
        return chatId;
    }
}
