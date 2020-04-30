package com.example.dilkursu.models;

import java.util.ArrayList;

public class Branch {

    private String name;
    private ArrayList<String> phoneNumbers;
    private ArrayList<String> faxNumbers;
    private String address;
    private ArrayList<String> publicTransports;
    private ArrayList<String> privateTransports;
    private ArrayList<String> facilities;
    private ArrayList<Classroom> classrooms;
    private ArrayList<Course> courses;
    private EducationAcademy educationAcademy;

    public Branch(String name, ArrayList<String> phoneNumbers, ArrayList<String> faxNumbers, String address, ArrayList<String> publicTransports, ArrayList<String> privateTransports, ArrayList<String> facilities, ArrayList<Classroom> classrooms, ArrayList<Course> courses, EducationAcademy educationAcademy) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.faxNumbers = faxNumbers;
        this.address = address;
        this.publicTransports = publicTransports;
        this.privateTransports = privateTransports;
        this.facilities = facilities;
        this.classrooms = classrooms;
        this.courses = courses;
        this.educationAcademy = educationAcademy;

        phoneNumbers = new ArrayList<>();
        faxNumbers = new ArrayList<>();
        publicTransports = new ArrayList<>();
        privateTransports = new ArrayList<>();
        facilities = new ArrayList<>();
        classrooms = new ArrayList<>();
        courses = new ArrayList<>();

    }

    public static Branch BranchFactory(String name, ArrayList<String> phoneNumbers, ArrayList<String> faxNumbers, String address, ArrayList<String> publicTransports, ArrayList<String> privateTransports, ArrayList<String> facilities, ArrayList<Classroom> classrooms, ArrayList<Course> courses, EducationAcademy educationAcademy){
        return null;
    }

    public boolean addClassroom(Classroom classroom){
        return false;
    }

    public ArrayList<Instructor> availableInstructors(){
        return null;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "name='" + name + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", faxNumbers=" + faxNumbers +
                ", address='" + address + '\'' +
                ", publicTransports=" + publicTransports +
                ", privateTransports=" + privateTransports +
                ", facilities=" + facilities +
                ", classrooms=" + classrooms +
                ", courses=" + courses +
                ", educationAcademy=" + educationAcademy +
                '}';
    }
}
