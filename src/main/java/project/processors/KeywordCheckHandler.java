package project.processors;

/**
 * Паттерн Chain of responsibility
 */
public class KeywordCheckHandler extends AbstractHandler {
    @Override
    protected boolean check(Request request) {
        System.out.println("Проверка ключевых слов...");
        if (request.getKeyWords() == null || request.getKeyWords().isEmpty()) {
            System.out.println("Ключевые слова отсутствуют");
            return false;
        }
        return true;
    }
}
