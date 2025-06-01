package org.lessons.java.spring_pokemon.controller;

import java.util.List;

import org.lessons.java.spring_pokemon.model.Pokemon;
import org.lessons.java.spring_pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public String index(Model model) {
        
        List<Pokemon> pokeList=pokemonService.findAll();
        model.addAttribute("pokemons", pokeList);


        return "pokemon/index";
    }
    

    @GetMapping("/{slug}")
    public String show(@PathVariable String slug, Model model) {

        model.addAttribute("pokemon", pokemonService.getBySlug(slug));


        return "pokemon/show";
    }


    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("pokemon", new Pokemon());

        return "pokemon/edit-create";
    }
    
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("pokemon") Pokemon pokemonForm,
    BindingResult bindingResult ) {
       if (bindingResult.hasErrors()) {
          System.out.println("--- ERRORI DI VALIDAZIONE ---");
         return "pokemon/edit-create";
       }
        pokemonService.create(pokemonForm);
        return "redirect:/pokemons";
    }
    


}
