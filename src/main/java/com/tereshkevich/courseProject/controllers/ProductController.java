package com.tereshkevich.courseProject.controllers;

import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.services.MusicianService;
import com.tereshkevich.courseProject.services.ProductService;
import com.tereshkevich.courseProject.util.ProductValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductValidator productValidator;
    private final MusicianService musicianService;

    @Autowired
    public ProductController(ProductService productService, ProductValidator productValidator, MusicianService musicianService) {
        this.productService = productService;
        this.productValidator = productValidator;
        this.musicianService = musicianService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("product", productService.findAll());
        return "admin/product/index";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product, Model musician) {
        musician.addAttribute("musician", musicianService.findAll());
        return "admin/product/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult, Model musician) {
        productValidator.validate(product, bindingResult);

        if (bindingResult.hasErrors()) {
            musician.addAttribute("musician", musicianService.findAll());
            return "admin/product/new";
        }

        productService.save(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model product, @PathVariable("id") int id, Model musician) {
        product.addAttribute("product", productService.findOne(id));
        musician.addAttribute("musician", musicianService.findAll());
        return "admin/product/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                         @PathVariable("id") int id, Model musician) {
        if (bindingResult.hasErrors()) {
            musician.addAttribute("musician", musicianService.findAll());
            return "admin/product/edit";
        }

        productService.update(id, product);
        return "redirect:/admin/product";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/admin/product";
    }
}
