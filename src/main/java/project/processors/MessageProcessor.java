package project.processors;

import project.bot.Bot;
import project.processors.commands.CommandProcessors;

public class MessageProcessor {
    private final Bot bot;
    private final CommandProcessors commandProcessors;

    public MessageProcessor(Bot bot, CommandProcessors commandProcessors) {
        this.bot = bot;
        this.commandProcessors = commandProcessors;
    }

    public void processMessage(String username, String message) {
        
    }
}
