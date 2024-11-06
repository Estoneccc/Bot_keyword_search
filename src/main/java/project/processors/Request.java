package project.processors;

public class Request {
    private Long userId;
    private String message;
    private String keyWords;

    public Request(Long userId, String message, String keyWords) {
        this.userId = userId;
        this.message = message;
        this.keyWords = keyWords;
    }

    public Long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getKeyWords() {
        return keyWords;
    }
}
