package org.lessons.java.spring_pokemon.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_pokemon.model.Pokemon;
import org.lessons.java.spring_pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin
@RequestMapping("/api/pokemons")

public class PokemonRestController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> index() {

        return pokemonService.findAll();
    }

    
    @GetMapping("/sortedByName")
    public List<Pokemon> indexByName() {

        return pokemonService.findAllSortedByName();
    }
    


}
