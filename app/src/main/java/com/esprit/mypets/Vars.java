package com.esprit.mypets;

import com.esprit.mypets.entity.User;

public class Vars {

    private static User USER ;
    private static String URL="http://192.168.1.13:3000/";


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
