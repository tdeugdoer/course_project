package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Musician;
import com.tereshkevich.courseProject.repositories.MusicianRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MusicianServiceTest {

    @Mock
    private MusicianRepository mockMusicianRepository;

    private MusicianService musicianServiceUnderTest;

    @BeforeEach
    void setUp() {
        musicianServiceUnderTest = new MusicianService(mockMusicianRepository);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<Musician> expectedResult = List.of(new Musician("name", "members"));
        when(mockMusicianRepository.findAll()).thenReturn(List.of(new Musician("name", "members")));

        // Run the test
        final List<Musician> result = musicianServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_MusicianRepositoryReturnsNoItems() {
        // Setup
        when(mockMusicianRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Musician> result = musicianServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindOne() {
        // Setup
        final Musician expectedResult = new Musician("name", "members");

        // Configure MusicianRepository.findById(...).
        final Optional<Musician> musician = Optional.of(new Musician("name", "members"));
        when(mockMusicianRepository.findById(0)).thenReturn(musician);

        // Run the test
        final Musician result = musicianServiceUnderTest.findOne(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindOne_MusicianRepositoryReturnsAbsent() {
        // Setup
        when(mockMusicianRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Musician result = musicianServiceUnderTest.findOne(0);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testSave() {
        // Setup
        final Musician musician = new Musician("name", "members");

        // Run the test
        musicianServiceUnderTest.save(musician);

        // Verify the results
        verify(mockMusicianRepository).save(new Musician("name", "members"));
    }

    @Test
    void testUpdate() {
        // Setup
        final Musician updatedMusician = new Musician("name", "members");

        // Run the test
        musicianServiceUnderTest.update(0, updatedMusician);

        // Verify the results
        verify(mockMusicianRepository).save(new Musician("name", "members"));
    }

    @Test
    void testDelete() {
        // Setup
        // Run the test
        musicianServiceUnderTest.delete(0);

        // Verify the results
        verify(mockMusicianRepository).deleteById(0);
    }

    @Test
    void testGetMusicianByName() {
        // Setup
        final Optional<Musician> expectedResult = Optional.of(new Musician("name", "members"));

        // Configure MusicianRepository.findByName(...).
        final Optional<Musician> musician = Optional.of(new Musician("name", "members"));
        when(mockMusicianRepository.findByName("name")).thenReturn(musician);

        // Run the test
        final Optional<Musician> result = musicianServiceUnderTest.getMusicianByName("name");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetMusicianByName_MusicianRepositoryReturnsAbsent() {
        // Setup
        when(mockMusicianRepository.findByName("name")).thenReturn(Optional.empty());

        // Run the test
        final Optional<Musician> result = musicianServiceUnderTest.getMusicianByName("name");

        // Verify the results
        assertThat(result).isEmpty();
    }
}
