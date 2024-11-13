package project.processors.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import project.bot.impl.TelegramBot;
import project.domain.Person;
import project.processors.states.State;
import project.services.impl.PersonServiceImpl;

public class StartCommand implements Command {
    private final TelegramBot bot;
    private final PersonServiceImpl personService;

    public StartCommand(TelegramBot bot, PersonServiceImpl personService) {
        this.bot = bot;
        this.personService = personService;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {
        Long chatId = update.getMessage().getChatId();
        String userFirstName = update.getMessage().getFrom().getFirstName();

        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        if (!personService.existsByPerson(chatId)) {
            message.setText("Привет, я могу отправлять уведомления о важных сообщениях из групп и каналов.");
            bot.sendMessage(message);
            personService.savePerson(new Person(chatId, userFirstName));
        }
        personService.updatePersonState(chatId, State.START);

        message.setText("Выберите список всех чатов или только тех, для которых уже заданы ключевые слова.");
        bot.sendMessage(message);
    }
}