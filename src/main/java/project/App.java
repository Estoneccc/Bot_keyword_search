package project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import project.bot.impl.TelegramBot;
import project.services.impl.ChatWithBotServiceImpl;
import project.services.impl.PersonChatServiceImpl;
import project.services.impl.PersonServiceImpl;

@SpringBootApplication
public class App
{
    @Autowired
    public void startBot(PersonServiceImpl userService, ChatWithBotServiceImpl chatForBotService, PersonChatServiceImpl personChatService) throws TelegramApiException {
        TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
        bot.registerBot(new TelegramBot(userService, chatForBotService, personChatService));
    }
    public static void main( String[] args ){
        SpringApplication.run(App.class, args);
    }
}
