package project.domain;

import java.util.List;

/**
 * Лист
 */
public class MessageLeaf implements MessageComponent {
    private final String message;

    public MessageLeaf(String message) {
        this.message = message;
    }

    @Override
    public void addMessage(String message) {
        throw new UnsupportedOperationException("Cannot add message to a single message");
    }

    @Override
    public void removeMessage(String message) {
        throw new UnsupportedOperationException("Cannot remove message from a single message");
    }

    @Override
    public List<String> searchByKeyword(String keyword) {
        if (message.contains(keyword)) {
            return List.of(message);
        } else {
            return List.of();
        }
    }
}

