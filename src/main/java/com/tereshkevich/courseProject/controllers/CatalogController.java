package com.tereshkevich.courseProject.controllers;

import com.tereshkevich.courseProject.services.MusicianService;
import com.tereshkevich.courseProject.util.MusicianValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class CatalogController {



    @GetMapping()
    public String index() {
        return "user/catalog";
    }
}
