package project.services.impl;

import project.domain.Person;
import project.processors.states.State;
import project.services.PersonService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Proxy
 */
public class PersonServiceProxy implements PersonService {

    private final PersonService personService;
    private final Set<Long> allowedUserIds;  // Разрешенные пользователи

    public PersonServiceProxy(PersonService personService) {
        this.personService = personService;
        this.allowedUserIds = new HashSet<>(List.of(1L, 2L, 3L));  // Идентификаторы разрешенных пользователей
    }

    @Override
    public void savePerson(Person person) {
        if (checkAccess(person.getChatId())) {
            personService.savePerson(person);
            logAccess("savePerson");
        }
    }

    @Override
    public Person findByPerson(Long chatId) {
        if (checkAccess(chatId)) {
            Person person = personService.findByPerson(chatId);
            logAccess("findByPerson");
            return person;
        }
        System.out.println("Доступ запрещен для метода findByPerson.");
        return null;
    }

    @Override
    public boolean existsByPerson(Long chatId) {
        if (checkAccess(chatId)) {
            boolean exists = personService.existsByPerson(chatId);
            logAccess("existsByPerson");
            return exists;
        }
        System.out.println("Доступ запрещен для метода existsByPerson.");
        return false;
    }

    @Override
    public List<Person> findAllPerson() {
        System.out.println("Метод findAllPerson вызывается без проверки доступа.");
        return personService.findAllPerson();
    }

    @Override
    public void deletePerson(Person person) {
        if (checkAccess(person.getChatId())) {
            personService.deletePerson(person);
            logAccess("deletePerson");
        }
    }

    @Override
    public void updatePersonState(Long chatId, State newState) {
        if (checkAccess(chatId)) {
            personService.updatePersonState(chatId, newState);
            logAccess("updatePersonState");
        }
    }

    @Override
    public State findStateByUserId(Long chatId) {
        if (checkAccess(chatId)) {
            State state = personService.findStateByUserId(chatId);
            logAccess("findStateByUserId");
            return state;
        }
        System.out.println("Доступ запрещен для метода findStateByUserId.");
        return null;
    }

    @Override
    public void updatePersonActiveChat(Long chatId, Long activeChat) {
        if (checkAccess(chatId)) {
            personService.updatePersonActiveChat(chatId, activeChat);
            logAccess("updatePersonActiveChat");
        }
    }

    @Override
    public Long findByActiveChat(Long userId) {
        if (checkAccess(userId)) {
            Long activeChat = personService.findByActiveChat(userId);
            logAccess("findByActiveChat");
            return activeChat;
        }
        System.out.println("Доступ запрещен для метода findByActiveChat.");
        return null;
    }

    private boolean checkAccess(Long userId) {
        // Проверка прав доступа на основе разрешенного списка
        if (allowedUserIds.contains(userId)) {
            System.out.println("Доступ разрешен для пользователя с ID: " + userId);
            return true;
        } else {
            System.out.println("Доступ запрещен для пользователя с ID: " + userId);
            return false;
        }
    }

    private void logAccess(String method) {
        System.out.println("Метод " + method + " вызван через Proxy.");
    }
}
