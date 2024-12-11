package project.processors.visitor;

public interface Visitable {
    void accept(MessageVisitor visitor);
}
