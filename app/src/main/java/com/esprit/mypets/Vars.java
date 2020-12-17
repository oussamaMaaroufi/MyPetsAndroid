package com.esprit.mypets;

import com.esprit.mypets.entity.User;

public class Vars {

    private static User USER ;
    private static String URL="http://192.168.1.3:3000/";
    private static String phone;
    private static String address;

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        Vars.phone = phone;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Vars.address = address;
    }

    public static User getUSER() {
        return USER;
    }

    public static void setUSER(User USER) {
        Vars.USER = USER;
    }

    public static String getURL() {
        return URL;
    }

}
