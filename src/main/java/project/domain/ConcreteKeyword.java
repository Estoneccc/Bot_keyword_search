package project.domain;

public class ConcreteKeyword implements Keyword {
    private final String keyword;

    public ConcreteKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getKeyword() {
        return keyword;
    }
}
