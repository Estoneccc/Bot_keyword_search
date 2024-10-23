package project.processors;

/**
 * Декоратор
 */
public abstract class MessageProcessorDecorator implements MessageProcessor {
    protected final MessageProcessor wrapped;

    public MessageProcessorDecorator(MessageProcessor wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void processMessage(String message) {
        wrapped.processMessage(message);
    }
}
