package project.processors.visitor;

public class GroupMessage implements Visitable {
    private final Long chatId;
    private final String text;

    public GroupMessage(Long chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    @Override
    public void accept(MessageVisitor visitor) {
        visitor.visitGroupMessage(this);
    }
}
