package com.example.dilkursu.models;

import java.util.ArrayList;

public class Registrar extends Person {

    public Registrar() {
        super();
    }

    public Registrar(String fname, String lname, ArrayList<String> phoneNumbers, ArrayList<String> homeNumbers, String address, String id, String branchName) {
        super(fname, lname, phoneNumbers, homeNumbers, address, id, branchName);
    }
}
