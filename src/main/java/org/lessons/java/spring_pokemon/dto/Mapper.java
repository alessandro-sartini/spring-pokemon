package org.lessons.java.spring_pokemon.dto;

import org.lessons.java.spring_pokemon.model.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.lessons.java.spring_pokemon.model.Pokemon;

public class Mapper {
    public static PokemonDTO toDto(Pokemon p) {
        List<String> typeNames = p.getTypes().stream()
            .map(Type::getName)
            .collect(Collectors.toList());

        return new PokemonDTO(
            p.getName(),
            p.getSlug(),
            p.getDescription(),
            p.getImageUrl(),
            p.getNumberPokedex(),
            typeNames
        );
    }
}
