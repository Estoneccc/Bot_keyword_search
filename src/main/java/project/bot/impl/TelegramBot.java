package project.bot.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import project.bot.Bot;
import project.domain.ChatWithBot;
import project.domain.Person;
import project.domain.PersonChat;
import project.services.PersonChatService;
import project.services.impl.ChatWithBotServiceImpl;
import project.services.impl.PersonChatServiceImpl;
import project.services.impl.PersonServiceImpl;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot implements Bot {
    private final String botId = System.getenv("bot_Id");
    private final PersonServiceImpl userService;
    private final ChatWithBotServiceImpl chatForBotService;
    private final PersonChatServiceImpl personChatService;

    @Autowired
    public TelegramBot(PersonServiceImpl userService, ChatWithBotServiceImpl chatForBotService, PersonChatServiceImpl personChatService) {
        this.userService = userService;
        this.chatForBotService = chatForBotService;
        this.personChatService = personChatService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        if (update.hasMessage() && update.getMessage().hasText()) {

            chatForBotService.findAllChat().forEach(chatWithBot -> {
                if (!isUserIdInChat(String.valueOf(chatWithBot.getChatId()), Long.valueOf(botId)))
                    chatForBotService.deleteChat(chatWithBot);
            });

            Message messageFromUser = update.getMessage();
            Long chatId = messageFromUser.getChatId();


            if (messageFromUser.getChat().isUserChat()) {

                sendMessage.setChatId(chatId);

                if (messageFromUser.getText().equals("/start")) {
                    if (!userService.existsByPerson(chatId)) {
                        sendMessage.setText("Привет, я могу отправлять" +
                                " уведомления о важных сообщениях из групп и каналов.");
                        sendMessage(sendMessage);
                        userService.savePerson(new Person(chatId, messageFromUser.getFrom().getFirstName()));
                    }
                    sendMessage.setText("Откройте список чатов, в которых присутсвуете вы и бот.");
                    sendMessage.setReplyMarkup(getButton("Список чатов", "chat_list"));
                    sendMessage(sendMessage);
                }
            }

            if (messageFromUser.getChat().isSuperGroupChat()) {

                if (!chatForBotService.existsByChat(chatId))
                    chatForBotService.saveChat(new ChatWithBot(chatId, messageFromUser.getChat().getTitle()));

                userService.findAllPerson().forEach(person -> {
                    StringBuilder forwardedMessage = new StringBuilder();
                    forwardedMessage.append("Пришло важное сообщение:").append("\n");
                    forwardedMessage.append("Переслано от: ").append(messageFromUser.getFrom().getFirstName()).append("\n");
                    forwardedMessage.append("Название чата: ").append(messageFromUser.getChat().getTitle()).append("\n\n");
                    forwardedMessage.append(messageFromUser.getText());

                    sendMessage.setChatId(person.getChatId());
                    sendMessage.setText(forwardedMessage.toString());
                    sendMessage(sendMessage);
                });
            }
        }

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String callbackData = callbackQuery.getData();

            if ("chat_list".equals(callbackData)) {
                sendMessage.setChatId(callbackQuery.getFrom().getId());
                if (chatForBotService.findAllChat().isEmpty()) {
                    sendMessage.setText("Список пуст.");
                    sendMessage(sendMessage);
                } else {
                    sendMessage.setText("Выберите чат для получения уведомлений:");
                    chatForBotService.findAllChat().forEach(chatWithBot -> {
                        if (isUserIdInChat(String.valueOf(chatWithBot.getChatId()), callbackQuery.getFrom().getId())) {
                            sendMessage.setReplyMarkup(getButton(chatWithBot.getFirstName(), String.valueOf(chatWithBot.getChatId())));
                            sendMessage(sendMessage);
                        }
                    });
                }
            }

            else {
                if (!personChatService.existsPersonChatByUserIdAndChatId(callbackQuery.getFrom().getId(), Long.valueOf(callbackData))) {
                    sendMessage.setChatId(callbackQuery.getFrom().getId());
                    sendMessage.setText(String.format("Выбран чат: %s\nТеперь напишите ключевые слова для поиска сообщений", chatForBotService.findChatById(Long.valueOf(callbackData)).getFirstName()));
                    sendMessage(sendMessage);
//                    personChatService.saveChatForPerson(new PersonChat(callbackQuery.getFrom().getId(), Long.valueOf(callbackData)));
                }
            }
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
    public void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Не удалось отправить сообщение. "
                    + e.getMessage(), e);
        }
    }

    private InlineKeyboardMarkup getButton(String text, String callbackData) {
        // Создаем объект кнопки "Список чатов"
        InlineKeyboardButton listChatButton = new InlineKeyboardButton();
        listChatButton.setText(text);
        listChatButton.setCallbackData(callbackData);

        // Создаем клавиатуру и добавляем кнопку "Список чатов"
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(listChatButton);
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    /**
     * Проверить, является ли юзер или бот участником чата.
     */
    private boolean isUserIdInChat(String chatId, Long userId) {
        GetChatMember request = new GetChatMember(chatId, userId);
        try {
            ChatMember chatMember = execute(request);
            if (chatMember != null) {
                String status = chatMember.getStatus();
                return status.equals("member") || status.equals("administrator") || status.equals("creator") || status.equals("restricted");
            }
            return false;
        } catch (TelegramApiException e) {
            return false;
        }
    }

    /**
     * Обработка сообщения от юзера
     */
    private void handleMessage(String userName, String message) {

    }
}
