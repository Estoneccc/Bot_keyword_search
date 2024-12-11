package project.processors.visitor;

public class CallbackQuery implements Visitable {
    private final String data;

    public CallbackQuery(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public void accept(MessageVisitor visitor) {
        visitor.visitCallbackQuery(this);
    }
}
