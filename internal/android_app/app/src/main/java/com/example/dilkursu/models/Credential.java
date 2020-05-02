package com.example.dilkursu.models;

public class Credential {

    private String person_id;
    private int authorization_level;

    public Credential(String person_id, int authorization_level) {
        this.person_id = person_id;
        this.authorization_level = authorization_level;
    }

    public String getPerson_id() {
        return person_id;
    }

    public int getAuthorization_level() {
        return authorization_level;
    }
}
