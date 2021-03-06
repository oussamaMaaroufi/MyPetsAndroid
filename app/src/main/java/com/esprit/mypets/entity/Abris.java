package com.esprit.mypets.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Abris {
    private String _id;
    private String IdUser;
    private String name;
    private String Adresse;
    private String telephon;
    private String image;


    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Abris{" +
                "id='" + _id + '\'' +
                ", IdUser='" + IdUser + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", telephon='" + telephon + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
