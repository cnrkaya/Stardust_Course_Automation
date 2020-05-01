package com.example.dilkursu.models;

public class Login {

    private String email;
    private String password;
    private String person_id;
    private int authorization_level;

    public Login(String email, String password, String person_id, int authorization_level) {
        this.email = email;
        this.password = password;
        this.person_id = person_id;
        this.authorization_level = authorization_level;
    }

    public Login() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public int getAuthorization_level() {
        return authorization_level;
    }

    public void setAuthorization_level(int authorization_level) {
        this.authorization_level = authorization_level;
    }
}
