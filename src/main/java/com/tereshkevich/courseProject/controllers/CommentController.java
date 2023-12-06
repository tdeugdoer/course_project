package com.tereshkevich.courseProject.controllers;

import com.tereshkevich.courseProject.models.Comment;
import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.services.CommentService;
import com.tereshkevich.courseProject.services.PersonService;
import com.tereshkevich.courseProject.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final ProductService productService;
    private final PersonService personService;

    @Autowired
    public CommentController(CommentService commentService, ProductService productService, PersonService personService) {
        this.commentService = commentService;
        this.productService = productService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("comment", commentService.findAll());
        return "comment/index";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("comment") Comment comment, Model product) {
        product.addAttribute("product", productService.findAll());
        return "comment/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("comment") @Valid Comment comment,
                         BindingResult bindingResult, Model product) {
        if (bindingResult.hasErrors()) {
            product.addAttribute("product", productService.findAll());
            return "comment/new";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person;
        if(authentication.isAuthenticated()){
            String login = authentication.getName();
            if(personService.getPersonByLogin(login).isPresent()){
                person = personService.getPersonByLogin(login).get();

            }
            else return "redirect:/catalog";
        } else return "redirect:/auth/login";
        comment.setPerson(person.getLogin());
        commentService.save(comment);
        return "redirect:/catalog";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        commentService.delete(id);
        return "redirect:/catalog";
    }
}
