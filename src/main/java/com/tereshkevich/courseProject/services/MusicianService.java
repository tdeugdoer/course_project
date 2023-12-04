package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Comment;
import com.tereshkevich.courseProject.models.Musician;
import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.repositories.CommentRepository;
import com.tereshkevich.courseProject.repositories.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MusicianService {
    private final MusicianRepository musicianRepository;

    @Autowired
    public MusicianService(MusicianRepository musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    public List<Musician> findAll() {
        return musicianRepository.findAll();
    }

    public Musician findOne(int id) {
        Optional<Musician> foundMusician = musicianRepository.findById(id);
        return foundMusician.orElse(null);
    }

    @Transactional
    public void save(Musician musician) {
        musicianRepository.save(musician);
    }

    @Transactional
    public void update(int id, Musician updatedMusician) {
        updatedMusician.setId(id);
        musicianRepository.save(updatedMusician);
    }

    @Transactional
    public void delete(int id) {
        musicianRepository.deleteById(id);
    }

    public Optional<Musician> getMusicianByName(String name) {
        return musicianRepository.findByName(name);
    }
}
