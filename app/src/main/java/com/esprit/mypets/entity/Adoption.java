package com.esprit.mypets.entity;

public class Adoption {
    private String _id;
    private String IdUser;
    private String  IdAnimal;
    private String Description;


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
}
