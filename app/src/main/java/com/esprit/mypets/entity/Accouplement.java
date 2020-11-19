package com.esprit.mypets.entity;

public class Accouplement {
    private String id;
    private Interesse interesse;
    private Volontaires volontaires1;
    private Volontaires volontaires2;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Interesse getInteresse() {
        return interesse;
    }

    public void setInteresse(Interesse interesse) {
        this.interesse = interesse;
    }

    public Volontaires getVolontaires1() {
        return volontaires1;
    }

    public void setVolontaires1(Volontaires volontaires1) {
        this.volontaires1 = volontaires1;
    }

    public Volontaires getVolontaires2() {
        return volontaires2;
    }

    public void setVolontaires2(Volontaires volontaires2) {
        this.volontaires2 = volontaires2;
    }
}
