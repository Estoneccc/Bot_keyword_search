package project.processors;

/**
 * Паттерн Chain of responsibility
 */
public class AccessCheckHandler extends AbstractHandler {
    @Override
    protected boolean check(Request request) {
        System.out.println("Проверка доступа для пользователя: " + request.getUserId());
        boolean hasAccess = request.getUserId() % 2 == 0; // Пример: доступ только для четных ID
        if (!hasAccess) {
            System.out.println("Доступ запрещен для пользователя: " + request.getUserId());
            return false;
        }
        return true;
    }
}
