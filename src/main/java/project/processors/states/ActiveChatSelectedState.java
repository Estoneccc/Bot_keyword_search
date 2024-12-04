package project.processors.states;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ActiveChatSelectedState implements BotState{
    @Override
    public SendMessage handle(StateContext context) {
        SendMessage message = new SendMessage();
        message.setChatId(context.getUserId().toString());
        message.setText("Вы выбрали чат: %s\nКлючевые слова: %s");
        return message;
    }
}
