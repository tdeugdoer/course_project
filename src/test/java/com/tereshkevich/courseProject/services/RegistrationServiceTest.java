package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private PersonRepository mockPersonRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    private RegistrationService registrationServiceUnderTest;

    @BeforeEach
    void setUp() {
        registrationServiceUnderTest = new RegistrationService(mockPersonRepository, mockPasswordEncoder);
    }

    @Test
    void testRegister() {
        // Setup
        final Person person = new Person("login", "password", "role");
        when(mockPasswordEncoder.encode("password")).thenReturn("password");

        // Run the test
        registrationServiceUnderTest.register(person);

        // Verify the results
        verify(mockPersonRepository).save(any(Person.class));
    }
}
