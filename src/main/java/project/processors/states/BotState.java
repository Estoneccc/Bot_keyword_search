package project.processors.states;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotState {
    SendMessage handle(StateContext context);
}