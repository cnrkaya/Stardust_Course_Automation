package com.example.dilkursu.models;

import android.util.Log;

import com.example.dilkursu.repository.Database;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Student extends Person {

    private ArrayList<Sales> sales;

    public Student(String fname, String lname, ArrayList<String> phoneNumbers, ArrayList<String> homeNumbers, String address, String id, String branchName) {
        super(fname, lname, phoneNumbers, homeNumbers, address, id, branchName);
    }

    public static ArrayList<Person> getPersons() {
        Database database = new Database();
        ArrayList<Person> people = new ArrayList<>();
        try {
            ResultSet resultSet = database.execute("SELECT * FROM person");
            if (resultSet != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String fanme = resultSet.getString("fname");
                    String lname = resultSet.getString("lname");
                    String phone_number = resultSet.getString("phone_number");
                    String home_addr = resultSet.getString("home_addr");
                    String work_addr = resultSet.getString("work_addr");

                    Log.i("OSE", fanme + lname);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        };

        return null;
    }


}
