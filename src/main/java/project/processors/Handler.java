package project.processors;

/**
 * Паттерн Chain of responsibility
 */
public interface Handler {
    void setNext(Handler nextHandler);         // Устанавливает следующий обработчик
    void handle(Request request);              // Выполняет проверку
    Handler getNextHandler();                  // Получает следующий обработчик в цепочке
}
