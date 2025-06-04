package org.lessons.java.spring_pokemon.controller;

import java.util.List;

import org.lessons.java.spring_pokemon.model.Type;
import org.lessons.java.spring_pokemon.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/types")
public class TypeRestController {

    @Autowired
    private TypeService typeService;

    @GetMapping
    public List<Type> index() {
        return typeService.findAll();

    }

}
