package com.esprit.mypets.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Abris {
    private String id;
    private String IdUser;
    private String Adresse;
    private String telephon;
    private String image;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getTelephon() {
        return telephon;
    }

    public void setTelephon(String telephon) {
        this.telephon = telephon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Abris{" +
                "id='" + id + '\'' +
                ", IdUser='" + IdUser + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", telephon='" + telephon + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
