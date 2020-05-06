package com.example.dilkursu;

import android.content.Context;

import com.example.dilkursu.models.AcademyAdmin;
import com.example.dilkursu.models.Branch;
import com.example.dilkursu.models.Classroom;
import com.example.dilkursu.models.Instructor;
import com.example.dilkursu.models.Person;
import com.example.dilkursu.models.Registrar;
import com.example.dilkursu.models.Student;
import com.example.dilkursu.repository.IDataConnection;
import com.example.dilkursu.repository.SqlConnector;

import java.util.ArrayList;

public class GlobalConfig {

    private static String host = "swprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com";
    private static String database = "langcoursedb";
    private static int port = 5432;
    public static String user = "swprojectadmin";
    public static String pass = "swproject12345";

    public static IDataConnection connection;
    public static Person currentUser = null;
    private static ArrayList<Branch> branches = null;
    private static ArrayList<Classroom> classrooms = null;
    private static ArrayList<String> branch_names;

    public static UserType currentUserType;

    public static boolean branchLoadingDone = false;

    public enum UserType {
        STUDENT,
        INSTRUCTOR,
        REGISTRAR,
        ADMIN
    }

    public static String getConnectionString() {
        String url = "jdbc:postgresql://%s:%d/%s";
        return String.format(url, host, port, database);
    }

    public static void InitializeConnection() {
        connection = new SqlConnector();
    }

    public static void InitializeArrays() {
        branches = connection.getAllBranches();
//        for(Branch branch : branches){
//            branch.setCourses(connection.getCourses(branch.getName()));
//        }

        branch_names = connection.getAllBranchNames();
    }

    public static void InitializeCurrentUser(UserType userType) {
        currentUserType = userType;
        switch (userType) {
            case STUDENT:
                currentUser = new Student();
                break;
            case INSTRUCTOR:
                currentUser = new Instructor();
                break;
            case REGISTRAR:
                currentUser = new Registrar();
                break;
            case ADMIN:
                currentUser = new AcademyAdmin();
                break;
        }

    }

    public static ArrayList<String> getAllBranchNames(){
        if (GlobalConfig.connection == null)
            GlobalConfig.InitializeConnection();

        if (GlobalConfig.branches == null)
            GlobalConfig.InitializeArrays();

        return GlobalConfig.branch_names;
    }

    public static ArrayList<Branch> getAllBranches() {
//        if (GlobalConfig.connection == null)
//            GlobalConfig.InitializeConnection();
//
//        if (GlobalConfig.branches == null)
//            GlobalConfig.InitializeArrays();

        return GlobalConfig.branches;
    }

    public static ArrayList<Classroom> getBranchClassrooms(String branchName) {
        if (GlobalConfig.connection == null)
            GlobalConfig.InitializeConnection();

        if (GlobalConfig.branches == null)
            GlobalConfig.InitializeArrays();

        for (Branch branch : branches) {
            if (branch.getName().equals(branchName)) {
                return branch.getClassrooms();
            }
        }
        return null;
    }

}
