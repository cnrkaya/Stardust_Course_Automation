package com.example.dilkursu.models;

public class Lesson {

    private String name;
    private Course course;
    private Instructor instructor;
    private Classroom classroom;

    public Lesson(String name, int courseId, String instructorId, int classroomId){
        this.name = name;
        // TODO: bind ids with their instances
    }

    public static Lesson LessonFactory(String name, Course course, Instructor instructor, Classroom classroom){
        return null;
    }

    //public static Lesson LessonFactory(String name")

}
