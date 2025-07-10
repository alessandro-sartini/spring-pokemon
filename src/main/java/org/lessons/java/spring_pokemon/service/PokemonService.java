package org.lessons.java.spring_pokemon.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.lessons.java.spring_pokemon.dto.Mapper;
import org.lessons.java.spring_pokemon.dto.PokemonDTO;
import org.lessons.java.spring_pokemon.model.Pokemon;
import org.lessons.java.spring_pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

public List<PokemonDTO> findAllDtos() {
    return pokemonRepository.findAll().stream()
        .map(Mapper::toDto)
        .collect(Collectors.toList());
}



    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    public List<Pokemon> findAll(Optional<String> typeName) {
        if (typeName.isPresent() && !typeName.get().isBlank()) {
            return pokemonRepository.findByTypesNameContainingIgnoreCase(typeName.get());
        } else {
            return pokemonRepository.findAll();
        }
    }

    public List<Pokemon> findAllSortedByName() {
        return pokemonRepository.findAll(Sort.by("name"));
    }

    public Optional<Pokemon> findBySlug(String slug) {
        return pokemonRepository.findBySlug(slug);
    }

    public Optional<Pokemon> findById(Integer id) {

        return pokemonRepository.findById(id);

    }

    public Pokemon getById(Integer id) {

        Optional<Pokemon> pokemonAttempt = pokemonRepository.findById(id);
        return pokemonAttempt.get();
    }

    public List<Pokemon> serchByName(String query) {

        return pokemonRepository.findByNameContaining(query);
    }

    public Pokemon getBySlug(String slug) {

        Optional<Pokemon> pokemonAttempt = pokemonRepository.findBySlug(slug);
        return pokemonAttempt.get();
    }

    public Pokemon create(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public Pokemon update(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public void delate(Integer id) {
        Pokemon pokemon = getById(id);

        pokemonRepository.delete(pokemon);
    }

    public void delate(String slug) {
        Pokemon pokemon = getBySlug(slug);

        pokemonRepository.delete(pokemon);
    }

    public boolean existById(Integer id) {
        return pokemonRepository.existsById(id);
    }

    public boolean existBySlug(String slug) {
        return pokemonRepository.existsBySlug(slug);
    }

    public boolean existByPokemon(Pokemon pokemon) {

        return pokemonRepository.existsById(pokemon.getId());
    }

}
