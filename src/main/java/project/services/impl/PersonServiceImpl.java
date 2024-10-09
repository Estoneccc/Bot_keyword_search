package project.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.processors.states.State;
import project.services.PersonService;
import project.domain.Person;
import project.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

/**
 * Адаптер
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void savePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person findByPerson(Long chatId) {
        Optional<Person> userOptional = personRepository.findById(chatId);
        return userOptional.orElse(null);
    }

    @Override
    public boolean existsByPerson(Long chatId) {
        return personRepository.existsById(chatId);
    }

    @Override
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    @Override
    public void updatePersonState(Long chatId, State newState) {
        Optional<Person> personOptional = personRepository.findById(chatId);
        personOptional.ifPresent(person -> {
            person.setState(newState);
            personRepository.save(person);
        });
    }

    @Override
    public State findStateByUserId(Long chatId) {
        return personRepository.findStateByUserId(chatId);
    }

    @Override
    public void updatePersonActiveChat(Long chatId, Long activeChat) {
        Optional<Person> personOptional = personRepository.findById(chatId);
        personOptional.ifPresent(person -> {
            person.setActiveChat(activeChat);
            personRepository.save(person);
        });
    }

    @Override
    public Long findByActiveChat(Long userId) {
        return personRepository.findActiveChatByUserId(userId);
    }
}
