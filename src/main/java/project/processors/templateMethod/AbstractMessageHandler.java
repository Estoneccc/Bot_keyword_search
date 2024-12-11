package project.processors.templateMethod;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

abstract class AbstractMessageHandler {

    public final void processMessage(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.getChat().isUserChat()) {
                handlePrivateMessage(message);
            } else if (message.getChat().isSuperGroupChat()) {
                handleSuperGroupMessage(message);
            }
        } else if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
        }
    }

    protected void handlePrivateMessage(Message message) {

    }

    protected void handleSuperGroupMessage(Message message) {

    }

    protected void handleCallback(CallbackQuery callbackQuery) {

    }
}
