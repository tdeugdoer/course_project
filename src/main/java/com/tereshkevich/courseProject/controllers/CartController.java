package com.tereshkevich.courseProject.controllers;

import com.tereshkevich.courseProject.models.Orders;
import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.services.CartService;
import com.tereshkevich.courseProject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final PersonService personService;

    @Autowired
    public CartController(CartService cartService, PersonService personService) {
        this.cartService = cartService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = null;
        if(authentication.isAuthenticated()){
            String login = authentication.getName();
            if(personService.getPersonByLogin(login).isPresent()){
                person = personService.getPersonByLogin(login).get();
            }
            else return "redirect:/catalog";
        }
        model.addAttribute("orders", cartService.findOrder(person));
        return "user/cart";
    }

    @PatchMapping("/{id}")
    public String completeOrder(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = null;
        if(authentication.isAuthenticated()){
            String login = authentication.getName();
            if(personService.getPersonByLogin(login).isPresent()){
                person = personService.getPersonByLogin(login).get();
            }
            else return "redirect:/catalog";
        }
        cartService.completeOrder(id);
        return "redirect:/catalog";
    }

    @DeleteMapping("/{id_prod}/{id_order}")
    public String deleteProduct(@PathVariable("id_prod") int id_prod, @PathVariable("id_order") int id_order) {
        cartService.deleteProduct(id_prod, id_order);
        return "redirect:/cart";
    }
}
