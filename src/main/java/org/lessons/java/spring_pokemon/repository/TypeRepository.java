package org.lessons.java.spring_pokemon.repository;

import org.lessons.java.spring_pokemon.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Integer> {

    
}
