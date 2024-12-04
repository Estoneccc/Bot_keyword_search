package project.processors.strategy;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Интерфейс стратегии
 */
public interface MessageProcessingStrategy {
    SendMessage process(Update update);
}