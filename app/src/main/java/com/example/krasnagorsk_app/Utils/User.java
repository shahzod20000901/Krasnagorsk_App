package com.example.krasnagorsk_app.Utils;

public class User {
    public String id, username, email, phone_number, password, date;

    public User() {
    }

    public User(String id, String username, String email, String phone_number, String password, String date) {
        this.id=id;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.date=date;
    }
}
