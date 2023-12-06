package com.tereshkevich.courseProject.controllers;

import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private final ProductService productService;
    private final PersonService personService;
    private final CatalogService catalogService;

    @Autowired
    public CatalogController(ProductService productService, PersonService personService, CatalogService catalogService) {
        this.productService = productService;
        this.personService = personService;
        this.catalogService = catalogService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("product", productService.findAll());
        return "user/catalog";
    }

    @PatchMapping("/{id}")
    public String addToCart(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person;
        if(authentication.isAuthenticated()){
            String login = authentication.getName();
            if(personService.getPersonByLogin(login).isPresent()){
                person = personService.getPersonByLogin(login).get();
            }
            else return "redirect:/catalog";
        }
        else return "redirect:/catalog";
        catalogService.addProduct(productService.findOne(id), person);
        return "redirect:/catalog";
    }


}
