package project.domain;

/**
 * Конкретный продукт
 */
public class Man implements Human {

    private final String name;

    private final Integer age;

    public Man(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public Human replicate() {
        return new Man(this.name, this.age);
    }
}
