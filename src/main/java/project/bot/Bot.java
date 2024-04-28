package project.bot;

import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

public interface Bot {
    /**
     * Бот отправляет обработанное сообщение в чат
     */
    void sendMessage(SendMessage message);

    /**
     * Бот переотправляет сообщение из одного чата в другой
     */
    void forwardMessage(ForwardMessage message);

    /**
     * Бот удаляет свое сообщение
     */
    void deleteMessage(DeleteMessage message);
}
