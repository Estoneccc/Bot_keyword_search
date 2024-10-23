package project.domain;

import java.util.List;

/**
 * Компонент
 */
public interface MessageComponent {
    void addMessage(String message);
    void removeMessage(String message);
    List<String> searchByKeyword(String keyword);
}