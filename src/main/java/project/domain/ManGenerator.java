package project.domain;

/**
 * Конкретный создатель
 */
public class ManGenerator extends HumanGenerator {

    private final Man exemplary;

    public ManGenerator(Man man) {
        this.exemplary = man;
    }
    @Override
    protected Human newHuman() {
        return exemplary.replicate();
    }
}
