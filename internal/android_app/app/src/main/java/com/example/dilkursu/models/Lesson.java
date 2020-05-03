package com.example.dilkursu.models;

public class Lesson {

    private String name;
    private int courseId;
    private String instructorId;
    private String classroomId;
    private String date;
    private String ts;

    public Lesson(){

    }

    public Lesson(String name, int courseId, String instructorId, String classroomId, String date, String ts){
        this.name = name;
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.classroomId = classroomId;
        this.date = date;
        this.ts = ts;
    }

    public static Lesson LessonFactory(String name, Course course, Instructor instructor, Classroom classroom){
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}
