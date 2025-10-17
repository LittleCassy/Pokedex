package com.example.pokedex.model;

public class Pokemon {
    String name;
    int number;
    String image;

    public Pokemon(String name, String image, int number) {
        this.name = name;
        this.image = image;
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
