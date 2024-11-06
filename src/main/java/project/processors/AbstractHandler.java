package project.processors;

/**
 * Паттерн Chain of responsibility
 */
public abstract class AbstractHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public Handler getNextHandler() {
        return nextHandler;
    }

    @Override
    public void handle(Request request) {
        if (check(request) && nextHandler != null) {
            nextHandler.handle(request);
        }
    }

    // Абстрактный метод проверки, который реализуют подклассы
    protected abstract boolean check(Request request);
}
