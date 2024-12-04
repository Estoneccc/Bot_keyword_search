package project.processors.states;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ChatSelectedState implements BotState{
    @Override
    public SendMessage handle(StateContext context) {
        SendMessage message = new SendMessage();
        message.setChatId(context.getUserId().toString());
        message.setText("Вы выбрали чат: %s. Теперь отправьте ключевые слова для его отслеживания.");
        return message;
    }
}
