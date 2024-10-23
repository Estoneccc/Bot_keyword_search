package project.processors;

/**
 * Конкретный декоратор
 */
public class LoggingMessageProcessor extends MessageProcessorDecorator {

    public LoggingMessageProcessor(MessageProcessor wrapped) {
        super(wrapped);
    }

    @Override
    public void processMessage(String message) {
        System.out.println("Logging message: " + message);
        super.processMessage(message);
    }
}
