package com.example.lab04.Model;

import java.util.Date;

public class User {
    private String name;
    private String email;
    private String birthday;
    private String birthtime;

    private String gender;
    private String country;
    private String[] favoriteIDE;
    private String toeic;
    private String message;

    public User(String name, String email, String birthday, String birthtime, String gender, String country, String[] favoriteIDE, String toeic, String message) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.birthtime = birthtime;
        this.gender = gender;
        this.country = country;
        this.favoriteIDE = favoriteIDE;
        this.toeic = toeic;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthtime() {
        return birthtime;
    }

    public void setBirthtime(String birthtime) {
        this.birthtime = birthtime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String[] getFavoriteIDE() {
        return favoriteIDE;
    }

    public void setFavoriteIDE(String[] favoriteIDE) {
        this.favoriteIDE = favoriteIDE;
    }

    public String getToeic() {
        return toeic;
    }

    public void setToeic(String toeic) {
        this.toeic = toeic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
