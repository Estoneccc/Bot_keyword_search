package project.processors.strategy;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Контекст
 */
public class MessageProcessor {
    private final MessageProcessingStrategy strategy;

    public MessageProcessor(MessageProcessingStrategy strategy) {
        this.strategy = strategy;
    }

    public SendMessage execute(Update update) {
        return strategy.process(update);
    }

    public static MessageProcessor selectStrategy(Update update) {
        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            return new MessageProcessor(new StartCommandStrategy());
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            return new MessageProcessor(new TextMessageStrategy());
        } else if (update.hasCallbackQuery()) {
            return new MessageProcessor(new CallbackQueryStrategy());
        }
        throw new IllegalArgumentException("Неизвестный тип сообщения");
    }
}
