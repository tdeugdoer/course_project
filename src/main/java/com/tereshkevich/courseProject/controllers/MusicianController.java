package com.tereshkevich.courseProject.controllers;

import com.tereshkevich.courseProject.models.Musician;
import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.services.MusicianService;
import com.tereshkevich.courseProject.services.ProductService;
import com.tereshkevich.courseProject.util.MusicianValidator;
import com.tereshkevich.courseProject.util.ProductValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/musician")
public class MusicianController {
    private final MusicianService musicianService;
    private final MusicianValidator musicianValidator;

    @Autowired
    public MusicianController(MusicianService musicianService, MusicianValidator musicianValidator) {
        this.musicianService = musicianService;
        this.musicianValidator = musicianValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("musician", musicianService.findAll());
        return "admin/musician/index";
    }

    @GetMapping("/new")
    public String newMusician(@ModelAttribute("musician") Musician musician) {
        return "admin/musician/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("musician") @Valid Musician musician,
                         BindingResult bindingResult) {
        musicianValidator.validate(musician, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/musician/new";
        }

        musicianService.save(musician);
        return "redirect:/musician";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model musician, @PathVariable("id") int id) {
        musician.addAttribute("musician", musicianService.findOne(id));
        return "admin/musician/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("musician") @Valid Musician musician, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "admin/musician/edit";
        }

        musicianService.update(id, musician);
        return "redirect:/musician";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        musicianService.delete(id);
        return "redirect:/musician";
    }
}
