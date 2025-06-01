package org.lessons.java.spring_pokemon.repository;

import org.lessons.java.spring_pokemon.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {

    public  List<Pokemon> findByName(String name);
    public Optional<Pokemon> findBySlug(String slug);
    
    public List<Pokemon> findByNameContaining(String name);

}
