package project.processors.strategy;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Конкретная стратегия
 */
public class CallbackQueryStrategy implements MessageProcessingStrategy {
    @Override
    public SendMessage process(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getFrom().getId().toString());
        message.setText("Вы выбрали: " + callbackData);
        return message;
    }
}