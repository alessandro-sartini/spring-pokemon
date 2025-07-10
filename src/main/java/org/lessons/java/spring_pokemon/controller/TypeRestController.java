package org.lessons.java.spring_pokemon.controller;

import java.util.List;

// import org.lessons.java.spring_pokemon.model.Pokemon;
import org.lessons.java.spring_pokemon.model.Type;
import org.lessons.java.spring_pokemon.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Type>> index() {

         List<Type> types = typeService.findAll();

        if (types.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } else {
            return new ResponseEntity<>(types, HttpStatus.OK); 
        }

    }

}
