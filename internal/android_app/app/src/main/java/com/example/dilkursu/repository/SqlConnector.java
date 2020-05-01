package com.example.dilkursu.repository;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.models.Branch;
import com.example.dilkursu.models.Classroom;
import com.example.dilkursu.models.Course;
import com.example.dilkursu.models.Credential;
import com.example.dilkursu.models.Login;
import com.example.dilkursu.models.Person;
import com.example.dilkursu.models.Student;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

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
    public Person getPerson(String person_id) {

        Person person = new Person();
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

        return person;
    }

    @Override
    public int getCourseId(String person_id) {

        int course_id = -1;
        try {

            String query = String.format("SELECT * FROM SALES WHERE customer_id = '%s'", person_id);
            ResultSet resultSet = database.execute(query);
            while (resultSet.next()) {
                course_id = resultSet.getInt("course_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return course_id;

    }

    @Override
    public ArrayList<Classroom> getClassrooms(String branch_name) {

        ArrayList<Classroom> classrooms = new ArrayList<>();

        try {

            String query = String.format("SELECT * FROM CLASSROOM WHERE branch_name = '%s'", branch_name);
            ResultSet resultSet = database.execute(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");

                classrooms.add(new Classroom(name, capacity, branch_name));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return classrooms;

    }

    @Override
    public String getBranchName(String person_id) throws Exception {

        String branch_name = null;
        try {

            String query = String.format("SELECT * FROM WORKS_ON WHERE person_id = '%s'", person_id);
            ResultSet resultSet = database.execute(query);
            while (resultSet.next()) {
                branch_name = resultSet.getString("branch_name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return branch_name;
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

    @Override
    public void addPerson(Person person) throws Exception {

        try {
            CallableStatement callableStatement = database.getConnection().prepareCall("{ CALL addPerson(?, ?, ?, ?, ?, ?, ?) }");
            callableStatement.setString(1, person.getId());
            callableStatement.setString(2, person.getFname());
            callableStatement.setString(3, person.getLname());
            callableStatement.setArray(4, database.getConnection().createArrayOf("text", new ArrayList[]{person.getPhoneNumbers()}));
            callableStatement.setArray(5, database.getConnection().createArrayOf("text", new ArrayList[]{person.getHomeNumbers()}));
            callableStatement.setString(6, person.getAddress());
            callableStatement.setString(7, ""); //work_addr

            callableStatement.execute();

            callableStatement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addLogin(Login login) throws Exception {

        try {
            CallableStatement callableStatement = database.getConnection().prepareCall("{ CALL addLogin(?, ?, ?, ?) }");
            callableStatement.setString(1, login.getEmail());
            callableStatement.setString(2, login.getPassword());
            callableStatement.setString(3, login.getPerson_id());
            callableStatement.setInt(4, login.getAuthorization_level());

            callableStatement.execute();

            callableStatement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getUserType(String person_id) throws Exception {
        int userType = 0;
        try {
            String storedProcedureCall = "{CALL getUserType(?, ?)}";
            CallableStatement callableStatement = database.getConnection().prepareCall(storedProcedureCall);

            callableStatement.setString(1, person_id);
            callableStatement.registerOutParameter(2, Types.INTEGER);

            callableStatement.executeUpdate();

            userType = callableStatement.getInt(2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userType;
    }
    


}
