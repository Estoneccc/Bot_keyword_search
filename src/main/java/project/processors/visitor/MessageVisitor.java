package project.processors.visitor;

public interface MessageVisitor {
    void visitPrivateMessage(PrivateMessage privateMessage);
    void visitGroupMessage(GroupMessage groupMessage);
    void visitCallbackQuery(CallbackQuery callbackQuery);
}
