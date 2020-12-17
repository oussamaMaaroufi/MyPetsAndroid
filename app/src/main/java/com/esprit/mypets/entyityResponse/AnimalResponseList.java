package com.esprit.mypets.entyityResponse;

import com.esprit.mypets.entity.Animal;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class AnimalResponseList {

    private String success;
    private String message;
    private ArrayList<Animal> Animal ;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<com.esprit.mypets.entity.Animal> getAnimal() {
        return Animal;
    }

    public void setAnimal(ArrayList<com.esprit.mypets.entity.Animal> animal) {
        Animal = animal;
    }

    @Override
    public String toString() {
        return "AnimalResponseList{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                ", Animal=" + Animal +
                '}';
    }
}
