package com.esprit.mypets.entity;

public class LostAndFound {
    private String _id;
    private String IdAnimal;
    private String IdUser;
    private String UserName;
    private String Desc;
    private String Type;
    private String Image;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIdAnimal() {
        return IdAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        IdAnimal = idAnimal;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public String getUserName() { return UserName; }

    public void setUserName(String UserName) { this.UserName = UserName; }


    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "LostAndFound{" +
                "_id='" + _id + '\'' +
                ", IdAnimal='" + IdAnimal + '\'' +
                ", IdUser='" + IdUser + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Desc='" + Desc + '\'' +
                ", Type=" + Type +
                '}';
    }
}
