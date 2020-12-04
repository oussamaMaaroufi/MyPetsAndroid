package com.esprit.mypets.entity;

public class Animal {

    private String _id;
    private String IdUser;
    private String Type;
    private String Name;
    private String Race;
    private String Image;

    public Animal(String name, String race, String image) {
        Name = name;
        Race = race;
        Image = image;
    }

    public Animal() {
    }

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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRace() {
        return Race;
    }

    public void setRace(String race) {
        Race = race;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + _id + '\'' +
                ", IdUser='" + IdUser + '\'' +
                ", Type='" + Type + '\'' +
                ", Name='" + Name + '\'' +
                ", Race='" + Race + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }
}
