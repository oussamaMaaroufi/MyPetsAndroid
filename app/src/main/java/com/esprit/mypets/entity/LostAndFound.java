package com.esprit.mypets.entity;

public class LostAndFound {
    private String _id;
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

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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
                ", IdUser='" + IdUser + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Desc='" + Desc + '\'' +
                ", Type='" + Type + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }
}
