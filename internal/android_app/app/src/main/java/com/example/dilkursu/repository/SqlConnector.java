package com.example.dilkursu.repository;

import com.example.dilkursu.models.Branch;
import com.example.dilkursu.models.Course;
import com.example.dilkursu.models.Credential;
import com.example.dilkursu.models.Person;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class SqlConnector implements IDataConnection {

    private Database database;
    private ResultSet resultSet;


    public SqlConnector() {
        database = new Database();
        resultSet = null;

    }

    @Override
    public Credential checkUserCredentials(String email, String password) {
        Credential credential = null;
        try {
            String storedProcedureCall = "{CALL checkUserCredentials(?,?,?,?)}";
            CallableStatement callableStatement = database.getConnection().prepareCall(storedProcedureCall);

            //input
            callableStatement.setString(1, email);
            callableStatement.setString(2, password);

            //output
            callableStatement.registerOutParameter(3, Types.CHAR);
            callableStatement.registerOutParameter(4, Types.INTEGER);

            callableStatement.executeUpdate();

            //declare variables
            String person_id = callableStatement.getString(3);
            int authorization_level = callableStatement.getInt(4);

            if (person_id != null) {
                credential = new Credential(person_id, authorization_level);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return credential;
    }

    @Override
    public void bindPerson(Person person, String person_id) {
        person.setId(person_id);

        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT * FROM PERSON WHERE id = ?");
            preparedStatement.setString(1, person_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                person.setFname(resultSet.getString("fname"));
                person.setLname(resultSet.getString("lname"));
                person.setPhoneNumbers(TextProcessor.stringToArray(resultSet.getString("phone_number")));
                person.setHomeNumbers(TextProcessor.stringToArray(resultSet.getString("home_number")));
                person.setAddress(resultSet.getString("home_addr"));
            }

            resultSet.close();
            preparedStatement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindCourse(Course course, int course_id) {
        course.setId(course_id);

        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT * FROM COURSE WHERE id = ?");
            preparedStatement.setInt(1, course_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                course.setLanguage(resultSet.getString("language"));
                course.setName(resultSet.getString("name"));
                course.setPrice(resultSet.getFloat("price"));
            }

            resultSet.close();
            preparedStatement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindBranch(Branch branch, String branch_name) {
        branch.setName(branch_name);

        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT * FROM BRANCH WHERE name = ?");
            preparedStatement.setString(1, branch_name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                branch.setPhoneNumbers(TextProcessor.stringToArray(resultSet.getString("phone_number")));
                branch.setFaxNumbers(TextProcessor.stringToArray(resultSet.getString("fax")));
                branch.setAddress(resultSet.getString("address"));
                branch.setPublicTransports(TextProcessor.stringToArray(resultSet.getString("public_transport")));
                branch.setPrivateTransports(TextProcessor.stringToArray(resultSet.getString("private_transport")));
                branch.setFacilities(TextProcessor.stringToArray(resultSet.getString("facilities")));
            }

            resultSet.close();
            preparedStatement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
