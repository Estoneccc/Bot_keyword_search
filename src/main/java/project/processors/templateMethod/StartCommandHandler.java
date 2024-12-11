package project.processors.templateMethod;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import project.bot.impl.TelegramBot;
import project.domain.Person;
import project.processors.states.State;
import project.services.impl.PersonServiceImpl;

class StartCommandHandler extends AbstractMessageHandler {

    private final PersonServiceImpl personService;
    private final TelegramBot bot;

    public StartCommandHandler(PersonServiceImpl personService, TelegramBot bot) {
        this.personService = personService;
        this.bot = bot;
    }

    @Override
    protected void handlePrivateMessage(Message message) {
        if ("/start".equals(message.getText())) {
            Long chatId = message.getChatId();
            String userName = message.getFrom().getFirstName();

            if (!personService.existsByPerson(chatId)) {
                personService.savePerson(new Person(chatId, userName));
            }
            personService.updatePersonState(chatId, State.START);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId.toString());
            sendMessage.setText("Привет! Я могу отправлять уведомления о важных сообщениях.");
            try {
                bot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException("Ошибка отправки сообщения: " + e.getMessage(), e);
            }
        }
    }
}