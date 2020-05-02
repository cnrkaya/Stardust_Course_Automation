package com.example.dilkursu.models;

import com.example.dilkursu.GlobalConfig;

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

    public Branch() {

    }

    public Branch(String name, String facilities, String publicTransport, String privateTransport, String address){
        this.name = name;

        this.phoneNumbers = new ArrayList<>();
        this.faxNumbers = new ArrayList<>();
        this.publicTransports = new ArrayList<>();
        this.privateTransports = new ArrayList<>();
        this.facilities = new ArrayList<>();
        this.classrooms = new ArrayList<>();
        this.courses = new ArrayList<>();

        this.facilities.add(facilities);
        this.publicTransports.add(publicTransport);
        this.privateTransports.add(privateTransport);
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public ArrayList<String> getFaxNumbers() {
        return faxNumbers;
    }

    public void setFaxNumbers(ArrayList<String> faxNumbers) {
        this.faxNumbers = faxNumbers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getPublicTransports() {
        return publicTransports;
    }

    public void setPublicTransports(ArrayList<String> publicTransports) {
        this.publicTransports = publicTransports;
    }

    public ArrayList<String> getPrivateTransports() {
        return privateTransports;
    }

    public void setPrivateTransports(ArrayList<String> privateTransports) {
        this.privateTransports = privateTransports;
    }

    public ArrayList<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(ArrayList<String> facilities) {
        this.facilities = facilities;
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public EducationAcademy getEducationAcademy() {
        return educationAcademy;
    }

    public void setEducationAcademy(EducationAcademy educationAcademy) {
        this.educationAcademy = educationAcademy;
    }

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

    public static Branch BranchFactory(String name, ArrayList<String> phoneNumbers, ArrayList<String> faxNumbers, String address, ArrayList<String> publicTransports, ArrayList<String> privateTransports, ArrayList<String> facilities, ArrayList<Classroom> classrooms, ArrayList<Course> courses, EducationAcademy educationAcademy) {
        return null;
    }

    public boolean addClassroom(Classroom classroom) {
        return false;
    }

    public ArrayList<Instructor> availableInstructors() {
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

    public static Branch getBranch(String branch_name) {
        for (Branch branch : GlobalConfig.getAllBranches()) {
            if (branch.getName().equals(branch_name)) {
                return branch;
            }
        }
        return null;
    }

}
