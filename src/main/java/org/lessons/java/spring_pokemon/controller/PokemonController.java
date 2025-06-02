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

        List<Pokemon> pokeList = pokemonService.findAll();
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
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pokemon/edit-create";
        }
        pokemonService.create(pokemonForm);
        return "redirect:/pokemons";
    }

    @GetMapping("/edit/{slug}")
    public String edit(Model model, @PathVariable String slug) {

        model.addAttribute("pokemon", pokemonService.getBySlug(slug));
        model.addAttribute("edit", true);
        return "pokemon/edit-create";
    }

    @PostMapping("/edit/{slug}")
    public String update(@PathVariable String slug, @Valid @ModelAttribute("pokemon") Pokemon pokemon,
            BindingResult bindingResult, Model model) {
        Pokemon existing = pokemonService.getBySlug(slug);
        // Se lo slug è cambiato e il nuovo slug esiste già per un altro Pokémon, errore
        if (!pokemon.getSlug().equals(slug) && pokemonService.existBySlug(pokemon.getSlug())) {
            bindingResult.rejectValue("slug", "error.pokemon", "Slug già esistente");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "pokemon/edit-create";
        }
        pokemon.setId(existing.getId());
        pokemonService.update(pokemon);
        return "redirect:/pokemons";
    }


    @PostMapping("/delete/{slug}")
    public String delete(@PathVariable String slug) {
       

        pokemonService.delate(slug);
        return "redirect:/pokemons";
    }
    

}
