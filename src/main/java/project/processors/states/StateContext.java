package project.processors.states;

import project.bot.impl.TelegramBot;

public class StateContext {
    private final TelegramBot bot;
    private final Long userId;

    public StateContext(TelegramBot bot, Long userId) {
        this.bot = bot;
        this.userId = userId;
    }

    public TelegramBot getBot() {
        return bot;
    }

    public Long getUserId() {
        return userId;
    }
}