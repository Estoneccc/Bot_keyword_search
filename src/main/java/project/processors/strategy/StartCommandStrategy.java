package project.processors.strategy;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Конкретная стратегия
 */
public class StartCommandStrategy implements MessageProcessingStrategy{
    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Добро пожаловать! Я ваш бот. Выберите действие.");
        return message;
    }
}
