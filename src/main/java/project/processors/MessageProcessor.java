package project.processors;

import project.bot.Bot;
import project.processors.command.CommandProcessors;

public class MessageProcessor {
    private final Bot bot;
    private final CommandProcessors commandProcessors;

    public MessageProcessor(Bot bot, CommandProcessors commandProcessors) {
        this.bot = bot;
        this.commandProcessors = commandProcessors;
    }
}
