package project.domain;

import java.util.List;
import java.util.stream.Stream;

/**
 * Создатель
 */
public abstract class HumanGenerator {

    protected abstract Human newHuman();

    public List<Human> createHumans(int n) {
        return Stream.generate(this::newHuman).limit(n).toList();
    }
}
