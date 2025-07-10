package org.lessons.java.spring_pokemon.dto;

import java.util.List;

public class PokemonDTO {

    private String name;
    private String slug;
    private String description;
    private String imageUrl;
    private Integer numberPokedex;
    private List<String> types;

    public PokemonDTO() {
    }

    public PokemonDTO(String name, String slug, String description,
            String imageUrl, Integer numberPokedex, List<String> types) {
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.imageUrl = imageUrl;
        this.numberPokedex = numberPokedex;
        this.types = types;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getTypes() {
        return this.types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
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

    public Integer getNumberPokedex() {
        return this.numberPokedex;
    }

    public void setNumberPokedex(Integer numberPokedex) {
        this.numberPokedex = numberPokedex;
    }

}
