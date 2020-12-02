package com.esprit.mypets.entyityResponse;

import com.esprit.mypets.entity.Animal;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class AnimalResponseList {

    private List<Animal> animal = null;

    public List<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(List<Animal> animal) {
        this.animal = animal;
    }
}
