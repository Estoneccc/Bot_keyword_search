package project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Узел
 */
public class MessageComposite implements MessageComponent {
    private final List<MessageComponent> messages = new ArrayList<>();

    @Override
    public void addMessage(String message) {
        messages.add(new MessageLeaf(message));
    }

    @Override
    public void removeMessage(String message) {
        messages.removeIf(msgComponent ->
                msgComponent.searchByKeyword(message).contains(message)
        );
    }

    @Override
    public List<String> searchByKeyword(String keyword) {
        List<String> result = new ArrayList<>();
        for (MessageComponent msgComponent : messages) {
            result.addAll(msgComponent.searchByKeyword(keyword));
        }
        return result;
    }
}