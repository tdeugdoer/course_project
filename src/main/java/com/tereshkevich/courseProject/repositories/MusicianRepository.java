package com.tereshkevich.courseProject.repositories;

import com.tereshkevich.courseProject.models.Musician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Integer> {
    Optional<Musician> findByName(String name);
}
