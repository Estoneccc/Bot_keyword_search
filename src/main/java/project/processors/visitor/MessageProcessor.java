package project.processors.visitor;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import project.bot.impl.TelegramBot;

public class MessageProcessor implements MessageVisitor {

    private final TelegramBot bot;

    public MessageProcessor(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void visitPrivateMessage(PrivateMessage privateMessage) {
        SendMessage message = new SendMessage();
        message.setChatId(privateMessage.getChatId().toString());
        message.setText("Привет! Вы написали: " + privateMessage.getText());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitGroupMessage(GroupMessage groupMessage) {
        SendMessage message = new SendMessage();
        message.setChatId(groupMessage.getChatId().toString());
        message.setText("Сообщение для группы: " + groupMessage.getText());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitCallbackQuery(CallbackQuery callbackQuery) {
        System.out.println("Обработка CallbackQuery: " + callbackQuery.getData());
    }
}