package com.example.dilkursu.models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Classroom {

    private int id;
    private int capacity;
    private ArrayList<Lesson> lessons;

    public Classroom(int capacity) {
        this.capacity = capacity;
    }

    public static Classroom ClassroomFactory(int id, int capacity, ArrayList<Lesson> lessons){
        return null;
    }

    public static Classroom ClassroomFactory(int id, ArrayList<Integer> lessonIds){
        return null;
    }

    public boolean isClassOccupied(Timestamp timestamp){
        return false;
    }


}
