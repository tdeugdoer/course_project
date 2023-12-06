package com.tereshkevich.courseProject.controllers;

import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.services.PersonService;
import com.tereshkevich.courseProject.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("person", personService.findAll());
        return "admin/person/index";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = null;
        if(authentication.isAuthenticated()){
            String login = authentication.getName();
            if(personService.getPersonByLogin(login).isPresent()){
                person = personService.getPersonByLogin(login).get();
                if(id != person.getId()){
                    personService.changeRole(id);
                }
            }
            else return "redirect:/catalog";
        }
        return "redirect:/person";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/person";
    }
}
