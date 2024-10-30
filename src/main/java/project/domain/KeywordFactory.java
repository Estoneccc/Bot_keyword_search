package project.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Паттерн Flyweight
 */
public class KeywordFactory {
    private final Map<String, Keyword> keywordMap = new HashMap<>();

    public Keyword getKeyword(String keyword) {

        if (!keywordMap.containsKey(keyword)) {
            keywordMap.put(keyword, new ConcreteKeyword(keyword));
        }
        return keywordMap.get(keyword);
    }

    public int getTotalKeywords() {
        return keywordMap.size();
    }
}