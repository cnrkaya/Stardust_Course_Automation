package com.example.dilkursu.models;

import java.util.ArrayList;

public class Course {

    private int id;
    private String name;
    private String language;
    private int price;
    private ArrayList<Lesson> lessons;
    private ArrayList<Person> persons;

    public Course(){

    }

    /**
     * Create Course Object
     * @param name is the name of the course
     * @param language is the language of the course
     * @param price is the price of the course where no discount is added.
     * @return newly created course object
     */
    public static Course courseFactory(String name, String language, int price){
        Course course = new Course();
        course.name = name;
        course.language = language;
        course.price = price;
        course.lessons = new ArrayList<Lesson>();
        course.persons = new ArrayList<Person>();
        course.id = -1;                              // User sets the id using setId method

        return course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }


}
