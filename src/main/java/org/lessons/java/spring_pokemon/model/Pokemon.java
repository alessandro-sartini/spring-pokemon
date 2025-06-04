package org.lessons.java.spring_pokemon.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pokemons")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2, message = "The name must be have 2 char")
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 30, message = "The slug must be between 3 and 30 characters")
    private String slug;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
    private String description;

    @NotBlank(message = "the url can't be null/empty")
    private String imageUrl;

    @Min(value = 1, message = "The Pokédex number must be at least 1")
    @Max(value = 151, message = "The Pokédex number must not exceed 151")
    @NotNull
    @Column(nullable = false)
    private Integer numberPokedex;

    @ManyToMany
    @JoinTable(name = "pokemon_type", joinColumns = @JoinColumn(name = "pokemon_id"), inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Type> types;

    public List<Type> getTypes() {
        return this.types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getNumberPokedex() {
        return this.numberPokedex;
    }

    public void setNumberPokedex(Integer numberPokedex) {
        this.numberPokedex = numberPokedex;
    }


}
