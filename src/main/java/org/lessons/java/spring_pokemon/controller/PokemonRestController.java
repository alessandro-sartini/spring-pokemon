package org.lessons.java.spring_pokemon.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.lessons.java.spring_pokemon.dto.PokemonDTO;
import org.lessons.java.spring_pokemon.model.Pokemon;
import org.lessons.java.spring_pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

// import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
@RequestMapping("/api/pokemons")

public class PokemonRestController {

    @Autowired
    private PokemonService pokemonService;

    // @GetMapping("/all")
    // public ResponseEntity<List<Pokemon>> getAll(){
    //     List<Pokemon> pokemons= pokemonService.findAll().stream()
    //     .map(poke-> new Pokemon(poke.getName(), poke.getSlug()))
    //     .collect(Collectors.toList());

    //     return ResponseEntity.ok(pokemons);
    // }
@GetMapping("/dto")
public ResponseEntity<List<PokemonDTO>> getAll() {
    return ResponseEntity.ok(pokemonService.findAllDtos());
}

    @GetMapping
    public ResponseEntity<List<Pokemon>> index(
            @RequestParam(name = "type", required = false) Optional<String> typeName) {
        List<Pokemon> pokemons = pokemonService.findAll(typeName);

        if (pokemons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pokemons, HttpStatus.OK);
        }
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getPokemonNames() {
        List<String> names = pokemonService.findAll().stream()
                .map(Pokemon::getName)
                .collect(Collectors.toList());

        return ResponseEntity.ok(names);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Pokemon> show(@PathVariable Integer id) {
        Optional<Pokemon> pokemOptional = pokemonService.findById(id);

        if (pokemOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(pokemOptional.get(), HttpStatus.OK);
    }





    

    @GetMapping("/bySlug/{slug}")
    public ResponseEntity<Pokemon> show(@PathVariable String slug) {
        Optional<Pokemon> pokemOptional = pokemonService.findBySlug(slug);

        if (pokemOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(pokemOptional.get(), HttpStatus.OK);
    }

}

// @GetMapping("/sortedByName")
// public List<Pokemon> indexByName() {

// return pokemonService.findAllSortedByName();
// }
// @PostMapping("/create")
// public ResponseEntity<Pokemon> create(@Valid @RequestBody Pokemon pokemon) {

// return new ResponseEntity<>(pokemonService.create(pokemon), HttpStatus.OK);
// }

// @PutMapping("/edit/{id}")
// public ResponseEntity<Pokemon> update(@Valid @RequestBody Pokemon pokemon,
// @PathVariable Integer id) {

// if (!pokemonService.existById(id)) {
// return new ResponseEntity<Pokemon>(HttpStatus.NOT_FOUND);
// }
// pokemon.setId(id);
// return new ResponseEntity<>(pokemonService.update(pokemon), HttpStatus.OK);
// }

// @PutMapping("/editBySlug/{slug}")
// public ResponseEntity<Pokemon> update(@Valid @RequestBody Pokemon pokemon,
// @PathVariable String slug) {

// if (!pokemonService.existBySlug(slug)) {
// return new ResponseEntity<Pokemon>(HttpStatus.NOT_FOUND);
// }

// Optional<Pokemon> pokemonId = pokemonService.findBySlug(slug);

// pokemon.setId(pokemonId.get().getId());
// return new ResponseEntity<>(pokemonService.update(pokemon), HttpStatus.OK);
// }

// @DeleteMapping("/delete/{id}")
// public ResponseEntity<Pokemon> delete(@PathVariable Integer id) {

// if (!pokemonService.existById(id)) {
// return new ResponseEntity<Pokemon>(HttpStatus.NOT_FOUND);

// }
// pokemonService.delate(id);
// return new ResponseEntity<>(HttpStatus.OK);

// }

// @DeleteMapping("/deleteBySlug/{slug}")
// public ResponseEntity<Pokemon> delete(@PathVariable String slug) {

// if (!pokemonService.existBySlug(slug)) {
// return new ResponseEntity<Pokemon>(HttpStatus.NOT_FOUND);

// }
// pokemonService.delate(slug);
// return new ResponseEntity<>(HttpStatus.OK);

// }