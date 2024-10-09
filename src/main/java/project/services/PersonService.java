package project.services;

import project.domain.Person;
import project.processors.states.State;

import java.util.List;

/**
 * Таргет
 */
public interface PersonService {
    void savePerson(Person person);
    Person findByPerson(Long chatId);
    boolean existsByPerson(Long chatId);
    List<Person> findAllPerson();
    void deletePerson(Person person);
    void updatePersonState(Long chatId, State newState);
    State findStateByUserId(Long chatId);
    void updatePersonActiveChat(Long chatId, Long activeChat);
    Long findByActiveChat(Long userId);
}
