package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }

    @Transactional
    public void changeRole(int id) {
        Optional<Person> optPerson = personRepository.findById(id);
        if(optPerson.isPresent()){
            optPerson.get().changeRole();
            personRepository.save(optPerson.get());
        }
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getPersonByLogin(String login) {
        return personRepository.findByLogin(login);
    }
}
