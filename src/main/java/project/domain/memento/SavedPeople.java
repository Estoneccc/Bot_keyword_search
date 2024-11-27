package project.domain.memento;

import java.util.HashMap;
import java.util.Map;

public class SavedPeople {
    private final Map<Long, PersonCaretaker> mementoMap = new HashMap<>();

    public void save(Long chatId, PersonCaretaker memento) {
        mementoMap.put(chatId, memento);
    }

    public PersonCaretaker get(Long chatId) {
        return mementoMap.get(chatId);
    }

    public void remove(Long chatId) {
        mementoMap.remove(chatId);
    }
}