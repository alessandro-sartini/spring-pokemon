package org.lessons.java.spring_pokemon.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_pokemon.model.Type;
import org.lessons.java.spring_pokemon.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
    
    @Autowired
    private TypeRepository typeRepository; 

    public List<Type> findAll(){
        return typeRepository.findAll();
    }

    public List<Type> findAllSortedByName() {
        return typeRepository.findAll(Sort.by("name"));
    }

    public Type getById(Integer id){
        Optional <Type> typeAttenmpt = typeRepository.findById(id);
        return typeAttenmpt.get();
    }

    public Type create(Type type){
        return typeRepository.save(type);
    }

    public Type update(Type type){
        return typeRepository.save(type);
    }

    public void delete( Integer id){
        Type type = getById(id);
        typeRepository.delete(type);
    }

    public boolean existById(Integer id) {
        return typeRepository.existsById(id);
    }

    public boolean existByPokemon(Type type) {

        return typeRepository.existsById(type.getId());
    }
}
