package com.yakisan.springrestapitest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Created by yunusemreyakisan on 30.03.2023
 */
public class Game {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("genre")
    private String genre;

    public Game() {
    }

    public Game(int id, String name, String description, String genre, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.genre = genre;
    }

    public Game(String name, String description,  String genre, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && Objects.equals(name, game.name) && Objects.equals(description, game.description) && Objects.equals(imageUrl, game.imageUrl) && Objects.equals(genre, game.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, imageUrl, genre);
    }
}