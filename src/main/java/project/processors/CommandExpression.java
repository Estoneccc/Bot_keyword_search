package project.processors;

public class CommandExpression implements Expression {
    private final String command;

    public CommandExpression(String command) {
        this.command = command;
    }

    @Override
    public boolean interpret(String context) {
        return context.equalsIgnoreCase(command);
    }
}