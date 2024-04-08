package project.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.services.PersonService;
import project.domain.Person;
import project.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

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
}
