package com.example.dilkursu;

import android.util.Log;

import com.example.dilkursu.models.AcademyAdmin;
import com.example.dilkursu.models.Instructor;
import com.example.dilkursu.models.Person;
import com.example.dilkursu.models.Registrar;
import com.example.dilkursu.models.Student;
import com.example.dilkursu.repository.IDataConnection;
import com.example.dilkursu.repository.SqlConnector;

public class GlobalConfig {

    private static String host = "swprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com";
    private static String database = "langcoursedb";
    private static int port = 5432;
    public static String user = "swprojectadmin";
    public static String pass = "swproject12345";

    public static IDataConnection connection;
    public static Person currentUser = null;

    public static UserType currentUserType;

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

    public static void InitializeConnections() {
        SqlConnector sql = new SqlConnector();
        connection = sql;
    }

    public static void InitializeCurrentUser(UserType userType) {
        currentUserType = userType;
        Log.i("APP_TEST", cu)
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

}
