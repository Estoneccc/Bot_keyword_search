package project.processors.strategy;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Конкретная стратегия
 */
public class TextMessageStrategy implements MessageProcessingStrategy {
    @Override
    public SendMessage process(Update update) {
        String userMessage = update.getMessage().getText();
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Вы сказали: " + userMessage);
        return message;
    }
}