package com.example.dilkursu.repository;

import com.example.dilkursu.models.Branch;
import com.example.dilkursu.models.Course;
import com.example.dilkursu.models.Credential;
import com.example.dilkursu.models.Person;

public interface IDataConnection {

    public Credential checkUserCredentials(String email, String password);

    public void bindPerson(Person person, String person_id);

    public void bindCourse(Course course, int course_id);

    public void bindBranch(Branch branch, String branch_name);


}
