package project.domain.memento;

import project.processors.states.State;

public class PersonCaretaker {
    private final State state;
    private final Long activeChat;

    public PersonCaretaker(State state, Long activeChat) {
        this.state = state;
        this.activeChat = activeChat;
    }

    public State getState() {
        return state;
    }

    public Long getActiveChat() {
        return activeChat;
    }
}
