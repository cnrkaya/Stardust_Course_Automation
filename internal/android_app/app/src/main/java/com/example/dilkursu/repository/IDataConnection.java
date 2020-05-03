package com.example.dilkursu.repository;

import com.example.dilkursu.models.Branch;
import com.example.dilkursu.models.Classroom;
import com.example.dilkursu.models.Course;
import com.example.dilkursu.models.Credential;
import com.example.dilkursu.models.Lesson;
import com.example.dilkursu.models.Login;
import com.example.dilkursu.models.Person;
import com.example.dilkursu.models.Student;

import java.util.ArrayList;

public interface IDataConnection {

    public Credential checkUserCredentials(String email, String password);

    public void bindPerson(Person person, String person_id);

    public void bindCourse(Course course, int course_id);

    public void bindBranch(Branch branch, String branch_name);

    public String getBranchName(String person_id) throws Exception;

    public int getCourseId(String person_id);

    public Person getPerson(String person_id);

    public Student getStudent(String person_id) throws Exception;

    public ArrayList<Classroom> getClassrooms(String branch_name);

    public ArrayList<Course> getCourses(String branch_name);

    public ArrayList<Branch> getAllBranches();

    public void addPerson(Person person) throws Exception;

    public void addLogin(Login login) throws Exception;

    public int getUserType(String person_id) throws Exception;

    void addBranch(Branch branch);

    void addCourse(Course course);

    void addClassroom(Classroom classroom);

    void addLesson(Lesson lesson);

    ArrayList<ArrayList<String>> getClassSchedules(String classroomId) throws Exception;

    void attachClassroomWithLesson(Lesson lesson, String teacherId) throws Exception;

    ArrayList<Course> getAllCourses();

    Branch getBranch(String branchName);

    /****** Deletion Methods ******/
    void deleteLesson(String lessonName, int courseId) throws Exception;

    void deleteBranch(String branchName) throws Exception;

    void deleteCourse(int courseId) throws Exception;

    void deleteClassroom(String classroomId) throws Exception;
}
