package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository mockPersonRepository;

    private PersonService personServiceUnderTest;

    @BeforeEach
    void setUp() {
        personServiceUnderTest = new PersonService(mockPersonRepository);
    }

    @Test
    void testFindAll() {
        // Setup
        // Configure PersonRepository.findAll(...).
        final List<Person> people = List.of(new Person("login", "password", "role"));
        when(mockPersonRepository.findAll()).thenReturn(people);

        // Run the test
        final List<Person> result = personServiceUnderTest.findAll();

        // Verify the results
    }

    @Test
    void testFindAll_PersonRepositoryReturnsNoItems() {
        // Setup
        when(mockPersonRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Person> result = personServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindOne() {
        // Setup
        // Configure PersonRepository.findById(...).
        final Optional<Person> person = Optional.of(new Person("login", "password", "role"));
        when(mockPersonRepository.findById(0)).thenReturn(person);

        // Run the test
        final Person result = personServiceUnderTest.findOne(0);

        // Verify the results
    }

    @Test
    void testFindOne_PersonRepositoryReturnsAbsent() {
        // Setup
        when(mockPersonRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Person result = personServiceUnderTest.findOne(0);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testSave() {
        // Setup
        final Person person = new Person("login", "password", "role");

        // Run the test
        personServiceUnderTest.save(person);

        // Verify the results
        verify(mockPersonRepository).save(any(Person.class));
    }

    @Test
    void testChangeRole() {
        // Setup
        // Configure PersonRepository.findById(...).
        final Optional<Person> person = Optional.of(new Person("login", "password", "role"));
        when(mockPersonRepository.findById(0)).thenReturn(person);

        // Run the test
        personServiceUnderTest.changeRole(0);

        // Verify the results
        verify(mockPersonRepository).save(any(Person.class));
    }

    @Test
    void testChangeRole_PersonRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockPersonRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        personServiceUnderTest.changeRole(0);

        // Verify the results
    }

    @Test
    void testDelete() {
        // Setup
        // Run the test
        personServiceUnderTest.delete(0);

        // Verify the results
        verify(mockPersonRepository).deleteById(0);
    }

    @Test
    void testGetPersonByLogin() {
        // Setup
        // Configure PersonRepository.findByLogin(...).
        final Optional<Person> person = Optional.of(new Person("login", "password", "role"));
        when(mockPersonRepository.findByLogin("login")).thenReturn(person);

        // Run the test
        final Optional<Person> result = personServiceUnderTest.getPersonByLogin("login");

        // Verify the results
    }

    @Test
    void testGetPersonByLogin_PersonRepositoryReturnsAbsent() {
        // Setup
        when(mockPersonRepository.findByLogin("login")).thenReturn(Optional.empty());

        // Run the test
        final Optional<Person> result = personServiceUnderTest.getPersonByLogin("login");

        // Verify the results
        assertThat(result).isEmpty();
    }
}
