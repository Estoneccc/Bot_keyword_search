package project.services;

import project.domain.Person;

import java.util.List;

public interface PersonService {
    void savePerson(Person person);
    Person findByPerson(Long chatId);
    boolean existsByPerson(Long chatId);
    List<Person> findAllPerson();
    void deletePerson(Person person);
}
