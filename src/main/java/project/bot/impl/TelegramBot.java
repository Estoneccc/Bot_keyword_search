package project.bot.impl;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import project.bot.Bot;
import project.domain.ChatWithBot;
import project.domain.Person;
import project.domain.PersonChat;
import project.processors.states.State;
import project.services.impl.ChatWithBotServiceImpl;
import project.services.impl.PersonChatServiceImpl;
import project.services.impl.PersonServiceImpl;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot implements Bot {
    private final String botId = System.getenv("bot_Id");
    private final PersonServiceImpl personService;
    private final ChatWithBotServiceImpl chatWithBotService;
    private final PersonChatServiceImpl personChatService;

    @Autowired
    public TelegramBot(PersonServiceImpl personService, ChatWithBotServiceImpl chatWithBotService, PersonChatServiceImpl personChatService) {
        this.personService = personService;
        this.chatWithBotService = chatWithBotService;
        this.personChatService = personChatService;
    }

    @Override
    public void onUpdateReceived(Update update) {

        SendMessage sendMessage = new SendMessage();

        if (update.hasMessage() && update.getMessage().hasText()) {

            chatWithBotService.findAllChat().forEach(chatWithBot -> {
                if (!isUserIdInChat(String.valueOf(chatWithBot.getChatId()), Long.valueOf(botId)))
                    chatWithBotService.deleteChat(chatWithBot);
            });

            Message messageFromUser = update.getMessage();
            Long chatId = messageFromUser.getChatId();


            if (messageFromUser.getChat().isUserChat()) {

                sendMessage.setChatId(chatId);

                if (messageFromUser.getText().equals("/start")) {
                    startCommand(sendMessage, chatId, messageFromUser.getFrom().getFirstName());
                } else {
                    if (personService.findStateByUserId(chatId).equals(State.CHAT_SELECTED)
                    || personService.findStateByUserId(chatId).equals(State.ACTIVE_CHAT_SELECTED)) {
                        personChatService.updateKeywordsForChat(
                                chatId,
                                personService.findByActiveChat(chatId),
                                messageFromUser.getText());
                        sendMessage.setText("Ключевые слова записаны.");
                        sendMessage(sendMessage);
                        personService.updatePersonState(messageFromUser.getChatId(), State.NONE);
                    }
                }
            }

            if (messageFromUser.getChat().isSuperGroupChat()) {

                if (!chatWithBotService.existsByChat(chatId))
                    chatWithBotService.saveChat(new ChatWithBot(chatId, messageFromUser.getChat().getTitle()));

                personChatService.findAllPersonChatByChatId(messageFromUser.getChatId()).forEach(personChat -> {
                    sendMessage.setChatId(personChat.getUserId());
                    Arrays.asList(personChat.getKeyWords().split("[\\s,]+")).forEach(keyWord -> {

//                        Properties props = new Properties();
//                        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
//                        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//
//                        Annotation annotation = new Annotation(messageFromUser.getText());
//                        pipeline.annotate(annotation);
//
//                        List<CoreLabel> tokens = annotation.get(CoreAnnotations.TokensAnnotation.class);


                        if (messageFromUser.getText().contains(keyWord)) {
                            sendMessage.setText(String.format("Пришло важное сообщение из чата:\n\"%s\"", messageFromUser.getChat().getTitle()));
                            sendMessage(sendMessage);

                            ForwardMessage forwardMessage = new ForwardMessage();
                            forwardMessage.setChatId(personChat.getUserId());
                            forwardMessage.setFromChatId(chatId);
                            forwardMessage.setMessageId(messageFromUser.getMessageId());

                            forwardMessage(forwardMessage);
                        }
                    });

                });
//                personService.findAllPerson().forEach(person -> {
//                    StringBuilder forwardedMessage = new StringBuilder();
//                    forwardedMessage.append("Пришло важное сообщение:").append("\n");
//                    forwardedMessage.append("Переслано от: ").append(messageFromUser.getFrom().getFirstName()).append("\n");
//                    forwardedMessage.append("Название чата: ").append(messageFromUser.getChat().getTitle()).append("\n\n");
//                    forwardedMessage.append(messageFromUser.getText());
//
//                    sendMessage.setChatId(person.getChatId());
//                    sendMessage.setText(forwardedMessage.toString());
//                    sendMessage(sendMessage);
//                });
            }
        }

        if (update.hasCallbackQuery()) {

            User user = update.getCallbackQuery().getFrom();
            String callbackData = update.getCallbackQuery().getData();
            DeleteMessage deleteMessage = new DeleteMessage(String.valueOf(user.getId()), update.getCallbackQuery().getMessage().getMessageId());

            if ("start".equals(callbackData)) {
                sendMessage.setChatId(user.getId());
                deleteMessage(deleteMessage);
                startCommand(sendMessage, user.getId(), user.getFirstName());
            } else if ("chat_list".equals(callbackData)) {
                personService.updatePersonState(user.getId(), State.SELECT_CHAT);
                sendMessage.setChatId(user.getId());

                List<ChatWithBot> listChatsWithBotAndPerson = new LinkedList<>();
                chatWithBotService.findAllChat().forEach(chatWithBot -> {
                    if (isUserIdInChat(String.valueOf(chatWithBot.getChatId()), user.getId()))
                        listChatsWithBotAndPerson.add(chatWithBot);
                });
                if (listChatsWithBotAndPerson.isEmpty()) {
                    sendMessage.setText("Список пуст.");
                    deleteMessage(deleteMessage);
                    sendMessage(sendMessage);
                } else {
                    sendMessage.setText("Выберите чат для получения уведомлений:");
                    List<List<InlineKeyboardButton>> keyboard = new LinkedList<>();
                    listChatsWithBotAndPerson.forEach(chatWithBot ->
                            keyboard.add(getRowKeyboardButton(chatWithBot.getFirstName(), String.valueOf(chatWithBot.getChatId()))));
                    keyboard.add(getRowKeyboardButton("< Назад", "start"));
                    sendMessage.setReplyMarkup(getKeyboardMarkup(keyboard));
                    deleteMessage(deleteMessage);
                    sendMessage(sendMessage);
                }
            } else if ("active_chat_list".equals(callbackData)) {
                personService.updatePersonState(user.getId(), State.SELECT_ACTIVE_CHAT);
                sendMessage.setChatId(user.getId());

                List<PersonChat> listActiveChats = personChatService.findAllPersonChatByUserId(user.getId());
                listActiveChats.removeIf(activeChat -> activeChat.getKeyWords() == null);

                if (listActiveChats.isEmpty()) {
                    List<List<InlineKeyboardButton>> keyboard = new LinkedList<>();
                    sendMessage.setText("Список отслеживаемых чатов пуст.");
                    keyboard.add(getRowKeyboardButton("< Назад", "start"));
                    sendMessage.setReplyMarkup(getKeyboardMarkup(keyboard));
                    deleteMessage(deleteMessage);
                    sendMessage(sendMessage);
                } else {
                    sendMessage.setText("Выберите активный чат");
                    List<List<InlineKeyboardButton>> keyboard = new LinkedList<>();
                    listActiveChats.forEach(activeChat ->
                            keyboard.add(getRowKeyboardButton(chatWithBotService.findChatById(activeChat.getChatId()).getFirstName(),
                                    String.valueOf(activeChat.getChatId()))));
                    keyboard.add(getRowKeyboardButton("< Назад", "start"));
                    sendMessage.setReplyMarkup(getKeyboardMarkup(keyboard));
                    deleteMessage(deleteMessage);
                    sendMessage(sendMessage);
                }
            } else if ("change_keywords".equals(callbackData)) {
                changeKeywordsCommand(sendMessage, user, personService.findByActiveChat(user.getId()), deleteMessage);
            } else if ("delete_chat".equals(callbackData)) {
                sendMessage.setChatId(user.getId());
                personChatService.deletePersonChatByChatId(personService.findByActiveChat(user.getId()));
                deleteMessage(deleteMessage);
                sendMessage.setText("Чат больше не отслеживается.");
                sendMessage(sendMessage);
            }
            else {
                if (personService.findStateByUserId(user.getId()).equals(State.SELECT_CHAT)) {

                    personService.updatePersonState(user.getId(), State.CHAT_SELECTED);
                    personService.updatePersonActiveChat(user.getId(), Long.valueOf(callbackData));

                    if (!personChatService.existsPersonChatByUserIdAndChatId(user.getId(), Long.valueOf(callbackData)))
                        personChatService.saveChatForPerson(new PersonChat(user.getId(), Long.valueOf(callbackData)));

                    if (personChatService.findKeyWordsByUserIdAndChatId(user.getId(), Long.valueOf(callbackData)) == null) {
                        sendMessage.setChatId(user.getId());
                        sendMessage.setText(String.format("Выбран чат: %s\nТеперь напишите ключевые слова для поиска сообщений",
                                chatWithBotService.findChatById(Long.valueOf(callbackData)).getFirstName()));
                        deleteMessage(deleteMessage);
                        sendMessage(sendMessage);
                    } else {
                        changeKeywordsCommand(sendMessage, user, personService.findByActiveChat(user.getId()), deleteMessage);
                    }
                }
                if (personService.findStateByUserId(user.getId()).equals(State.SELECT_ACTIVE_CHAT)) {
                    personService.updatePersonState(user.getId(), State.ACTIVE_CHAT_SELECTED);
                    personService.updatePersonActiveChat(user.getId(), Long.valueOf(callbackData));
                    sendMessage.setChatId(user.getId());
                    sendMessage.setText(String.format("Выбран чат: %s\nКлючевые слова: %s",
                            chatWithBotService.findChatById(Long.valueOf(callbackData)).getFirstName(),
                            personChatService.findKeyWordsByUserIdAndChatId(user.getId(), Long.valueOf(callbackData))));
                    List<List<InlineKeyboardButton>> keyboard = new LinkedList<>();
                    keyboard.add(getRowKeyboardButton("Изменить ключевые слова", "change_keywords"));
                    keyboard.add((getRowKeyboardButton("Перестать отслеживать чат", "delete_chat")));
                    keyboard.add(getRowKeyboardButton("< Назад", "active_chat_list"));
                    sendMessage.setReplyMarkup(getKeyboardMarkup(keyboard));
                    deleteMessage(deleteMessage);
                    sendMessage(sendMessage);
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

    @Override
    public void forwardMessage(ForwardMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Не удалось переслать сообщение. "
                    + e.getMessage(), e);
        }
    }

    @Override
    public void deleteMessage(DeleteMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Не удалось удалить сообщение. "
                    + e.getMessage(), e);
        }
    }

    void startCommand(SendMessage sendMessage, Long chatId, String userFirstName) {
        if (!personService.existsByPerson(chatId)) {
            sendMessage.setText("Привет, я могу отправлять" +
                    " уведомления о важных сообщениях из групп и каналов.");
            sendMessage(sendMessage);
            personService.savePerson(new Person(chatId, userFirstName));
        }
        personService.updatePersonState(chatId, State.START);
        sendMessage.setText("Выберите список всех чатов или только тех, для которых уже заданы ключевые слова.");
        List<List<InlineKeyboardButton>> keyboard = new LinkedList<>();
        keyboard.add(getRowKeyboardButton("Список всех чатов", "chat_list"));
        keyboard.add(getRowKeyboardButton("Список активных чатов", "active_chat_list"));
        sendMessage.setReplyMarkup(getKeyboardMarkup(keyboard));
        sendMessage(sendMessage);
    }

    void changeKeywordsCommand(SendMessage sendMessage, User user, Long activeChat, DeleteMessage deleteMessage) {
        sendMessage.setChatId(user.getId());
        List<List<InlineKeyboardButton>> keyboard = new LinkedList<>();
        keyboard.add(getRowKeyboardButton("< Назад", "chat_list"));
        sendMessage.setText(String.format("Ключевые слова для чата \"%s\" уже заданы:\n%s\nЕсли желаете изменить их, то введите новые.",
                chatWithBotService.findChatById(activeChat).getFirstName(),
                personChatService.findKeyWordsByUserIdAndChatId(user.getId(), activeChat)));
        sendMessage.setReplyMarkup(getKeyboardMarkup(keyboard));
        deleteMessage(deleteMessage);
        sendMessage(sendMessage);
    }

    private List<InlineKeyboardButton> getRowKeyboardButton(String text, String callbackData) {
        // Создаем объект кнопки
        InlineKeyboardButton buttonChatName = new InlineKeyboardButton();
        List<InlineKeyboardButton> row = new LinkedList<>();
        buttonChatName.setText(text);
        buttonChatName.setCallbackData(callbackData);
        row.add(buttonChatName);

        return row;
    }

    private InlineKeyboardMarkup getKeyboardMarkup(List<List<InlineKeyboardButton>> keyboard) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
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
