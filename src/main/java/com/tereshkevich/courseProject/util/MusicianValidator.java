package com.tereshkevich.courseProject.util;

import com.tereshkevich.courseProject.models.Musician;
import com.tereshkevich.courseProject.services.MusicianService;
import com.tereshkevich.courseProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MusicianValidator implements Validator {
    private final MusicianService musicianService;

    @Autowired
    public MusicianValidator(MusicianService musicianService) { this.musicianService = musicianService; }

    @Override
    public boolean supports(Class<?> aClass) {
        return Musician.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Musician musician = (Musician) o;

        if (musicianService.getMusicianByName(musician.getName()).isPresent())
            errors.rejectValue("name", "", "Такой исполнитель уже существует");
    }
}
