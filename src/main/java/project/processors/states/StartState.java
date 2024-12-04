package project.processors.states;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartState implements BotState {
    @Override
    public SendMessage handle(StateContext context) {
        SendMessage message = new SendMessage();
        message.setChatId(context.getUserId().toString());
        message.setText("Привет! Выберите список чатов или активных чатов.");
        return message;
    }
}