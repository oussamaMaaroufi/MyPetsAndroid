package com.esprit.mypets.entity;

public class Adoption {
    private String _id;
    private String IdUser;
    private String  IdAnimal;
    private String image;
    private String nameAnimal;

    private String Description;

    public String getNameAnimal() {
        return nameAnimal;
    }

    public void setNameAnimal(String nameAnimal) {
        this.nameAnimal = nameAnimal;
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

    public String getIdAnimal() {
        return IdAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        IdAnimal = idAnimal;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }


}
