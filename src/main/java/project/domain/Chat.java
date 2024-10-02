package project.domain;

/**
 * Паттерн Builder
 */
public class Chat {

    private final String name;
    private final Long chatId;

    private Chat(String name, Long chatId) {
        this.name = name;
        this.chatId = chatId;
    }

    public static ChatBuilder having() {
        return new ChatBuilder();
    }
    public String getName() {
        return name;
    }

    public Long getChatId() {
        return chatId;
    }

    public static class ChatBuilder {

        private String name;
        private Long chatId;

        public ChatBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ChatBuilder chatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Chat done() {
            return new Chat(name, chatId);
        }
    }
}
