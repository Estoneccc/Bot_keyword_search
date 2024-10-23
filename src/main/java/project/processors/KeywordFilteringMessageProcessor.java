package project.processors;

/**
 * Конкретный декоратор
 */
public class KeywordFilteringMessageProcessor extends MessageProcessorDecorator {
    private final String keyword;

    public KeywordFilteringMessageProcessor(MessageProcessor wrapped, String keyword) {
        super(wrapped);
        this.keyword = keyword;
    }

    @Override
    public void processMessage(String message) {
        if (message.contains(keyword)) {
            System.out.println("Message contains keyword '" + keyword + "', processing...");
            super.processMessage(message);
        } else {
            System.out.println("Message does not contain keyword '" + keyword + "', skipping...");
        }
    }
}
