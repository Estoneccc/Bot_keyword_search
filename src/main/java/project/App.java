package project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import project.bot.impl.TelegramBot;
import project.services.impl.UserServiceImpl;

@SpringBootApplication
public class App
{
    @Autowired
    public void startBot(UserServiceImpl userService) throws TelegramApiException {
        TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
        bot.registerBot(new TelegramBot(userService));
    }
    public static void main( String[] args ){
        SpringApplication.run(App.class, args);
    }
}
