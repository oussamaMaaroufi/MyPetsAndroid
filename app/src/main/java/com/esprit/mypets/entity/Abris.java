package com.esprit.mypets.entity;

import java.util.List;

public class Abris extends User {

    private String id;
    private String adress;
    private String telephone;
    private List<Animal> ListAnimal;

    public Abris() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Animal> getListAnimal() {
        return ListAnimal;
    }

    public void setListAnimal(List<Animal> listAnimal) {
        ListAnimal = listAnimal;
    }
}
