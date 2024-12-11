package project.processors.templateMethod;

import org.telegram.telegrambots.meta.api.objects.Message;
import project.domain.ChatWithBot;
import project.services.impl.ChatWithBotServiceImpl;

class SuperGroupMessageHandler extends AbstractMessageHandler {

    private final ChatWithBotServiceImpl chatWithBotService;

    public SuperGroupMessageHandler(ChatWithBotServiceImpl chatWithBotService) {
        this.chatWithBotService = chatWithBotService;
    }

    @Override
    protected void handleSuperGroupMessage(Message message) {
        Long chatId = message.getChatId();
        String text = message.getText();

        if (!chatWithBotService.existsByChat(chatId)) {
            chatWithBotService.saveChat(new ChatWithBot(chatId, message.getChat().getTitle()));
        }

        System.out.println("Сообщение в супергруппе: " + text);
    }
}
