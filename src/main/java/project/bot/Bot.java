package project.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Bot {
    /**
     * Бот отправляет обработанное сообщение в чат
     */
    void sendMessage(SendMessage message);
}
