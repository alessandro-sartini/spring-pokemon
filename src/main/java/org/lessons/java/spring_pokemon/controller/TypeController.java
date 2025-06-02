package org.lessons.java.spring_pokemon.controller;

import org.lessons.java.spring_pokemon.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.lessons.java.spring_pokemon.model.Pokemon;
import org.lessons.java.spring_pokemon.model.Type;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("types", typeService.findAll());
        return "type/index";
    }

    @GetMapping("/{id}")

    public String show(Model model, @PathVariable Integer id) {

        model.addAttribute("type", typeService.getById(id));
        return "type/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("type", new Type());
        return "type/edit-create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("type") Type type, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "type/edit-create";
        }
        typeService.create(type);
        return "redirect:/types";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("edit", true);
        model.addAttribute("type", typeService.getById(id));
        return "type/edit-create";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("type") Type type,
            BindingResult bindingResult, @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return "type/edit-create";
        }
        typeService.create(type);
        return "redirect:/types";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,
            @RequestParam(value = "redirectTo", defaultValue = "/types") String redirectTo) {

        Type typeToDelete = typeService.getById(id);

        for (Pokemon pokemonDelete : typeToDelete.getPokemons()) {
            pokemonDelete.getTypes().remove(typeToDelete);
        }

        typeService.delete(id);
        return "redirect:" + redirectTo;
    }

}
