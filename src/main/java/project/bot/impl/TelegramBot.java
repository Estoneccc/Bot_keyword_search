package project.bot.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import project.bot.Bot;
import project.domain.User;
import project.services.impl.UserServiceImpl;

@Component
public class TelegramBot extends TelegramLongPollingBot implements Bot {

    private final UserServiceImpl userService;

    @Autowired
    public TelegramBot(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            Message message = update.getMessage();
            Long chatId = message.getChatId();

//            sendMessage(chatId, message.getText());

            if (message.getChat().isUserChat()) {
                sendMessage(chatId, message.getText());
                if (!userService.existsById(chatId))
                    userService.save(new User(chatId, message.getFrom().getFirstName()));
            }

            if (message.getChat().isSuperGroupChat()) {
                userService.findAll().forEach(user -> sendMessage(user.getChatId(), message.getText()));
            }
//            if (userService.existsById(chatId))
//                sendMessage(chatId, "Da");
//            else sendMessage(chatId, "Net");
//            sendMessage(chatId, String.valueOf(chatId));
        }
    }

    @Override
    public String getBotUsername() {
        return System.getenv("bot_userName");
    }

    @Override
    public String getBotToken() {
        return System.getenv("bot_token");
    }

    @Override
    public void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Не удалось отправить сообщение. "
                    + e.getMessage(), e);
        }
    }
}
