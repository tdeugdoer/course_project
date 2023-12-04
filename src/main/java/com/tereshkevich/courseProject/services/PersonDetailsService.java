package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.repositories.PersonRepository;
import com.tereshkevich.courseProject.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.peopleRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        Optional<Person> person = peopleRepository.findByLogin(s);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return new PersonDetails(person.get());
    }
}
