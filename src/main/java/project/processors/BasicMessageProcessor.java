package project.processors;

/**
 * Конкретный компонент
 */
public class BasicMessageProcessor implements MessageProcessor {

    @Override
    public void processMessage(String message) {

        System.out.println("Processing message: " + message);
    }
}
