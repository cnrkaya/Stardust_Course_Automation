package com.example.dilkursu.repository;

import android.util.Log;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.models.Branch;
import com.example.dilkursu.models.Classroom;
import com.example.dilkursu.models.Course;
import com.example.dilkursu.models.Credential;
import com.example.dilkursu.models.Instructor;
import com.example.dilkursu.models.Lesson;
import com.example.dilkursu.models.Login;
import com.example.dilkursu.models.Person;
import com.example.dilkursu.models.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void attachClassroomWithLesson(Lesson lesson) throws Exception {

        // SELECT attachClassroomWithLesson('BZ-45', '3/27/2020', '16:52:38', 'Listening', 3, '37781245624');
        CallableStatement callableStatement = database.getConnection().prepareCall("{ CALL attachClassroomWithLesson(?, ?, ?, ?, ?, ?) }");
        callableStatement.setString(1, lesson.getClassroomId());
        callableStatement.setString(2, lesson.getDate());
        callableStatement.setString(3, lesson.getTs());
        callableStatement.setString(4, lesson.getName());
        callableStatement.setInt(5, lesson.getCourseId());
        callableStatement.setString(6, lesson.getInstructorId());
        callableStatement.execute();
        callableStatement.close();
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
            ResultSet resultSet = database.execute2(query);
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
    public ArrayList<Course> getCourses(String branch_name) {
        ArrayList<Course> courses = new ArrayList<>();

        try {
            String query = String.format("SELECT getCourses('%s')", branch_name);
            ResultSet resultSet = database.execute2(query);
            while (resultSet.next()) {
                Course course = new Course();
                String[] attributes = TextProcessor.parseRecords(resultSet.getString("getcourses"));
                course.setId(Integer.parseInt(attributes[0]));
                course.setLanguage(attributes[1]);
                course.setName(attributes[2]);
                course.setPrice(attributes[3]);

                courses.add(course);

                Log.i("APP_TEST - DB: COURSE", course.toString());

            }

            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    @Override
    public ArrayList<Course> getAllCourses() {

        ArrayList<Course> courses = new ArrayList<>();

        try {
            String query = "SELECT * FROM Course";
            ResultSet resultSet = database.execute2(query);
            while (resultSet.next()) {
                Course course = Course.courseFactory(
                        resultSet.getString("name"),
                        resultSet.getString("language"),
                        resultSet.getString("price")
                );
                course.setId(resultSet.getInt("id"));
                courses.add(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    @Override
    public ArrayList<String> getAllBranchNames() {
        ArrayList<String> branches = new ArrayList<>();

        try {

            String query = "SELECT name FROM BRANCH";
            ResultSet resultSet = database.execute2(query);
            while (resultSet.next()) {
                branches.add(resultSet.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return branches;
    }

    @Override
    public void addEntryToWorksOn(String branchName, String person_id) {
        String query = String.format("INSERT INTO WORKS_ON VALUES('%s', '%s')", branchName, person_id);
        database.execute2(query);
    }

    @Override
    public ArrayList<Branch> getAllBranches() {

        ArrayList<Branch> branches = new ArrayList<>();

        try {

            String query = "SELECT * FROM BRANCH";
            ResultSet resultSet = database.execute2(query);
            while (resultSet.next()) {
                Branch branch = new Branch();
                branch.setName(resultSet.getString("name"));
                branch.setPhoneNumbers(TextProcessor.stringToArray(resultSet.getString("phone_number")));
                branch.setFaxNumbers(TextProcessor.stringToArray(resultSet.getString("fax")));
                branch.setAddress(resultSet.getString("address"));
                branch.setPublicTransports(TextProcessor.stringToArray(resultSet.getString("public_transport")));
                branch.setPrivateTransports(TextProcessor.stringToArray(resultSet.getString("private_transport")));
                branch.setFacilities(TextProcessor.stringToArray(resultSet.getString("facilities")));

                branch.setClassrooms(getClassrooms(branch.getName()));
                Log.i("APP_TEST - DB: BRANCH", branch.toString());
                branches.add(branch);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return branches;
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
                course.setPrice(resultSet.getString("price"));
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
        int userType = -1;
        try {
            String storedProcedureCall = "{CALL getUserType(?, ?)}";
            CallableStatement callableStatement = database.getConnection().prepareCall(storedProcedureCall);

            callableStatement.setString(1, person_id);
            callableStatement.registerOutParameter(2, Types.INTEGER);

            callableStatement.executeUpdate();

            userType = callableStatement.getInt(2);

        } catch (Exception e) {

        }

        return userType;
    }

    @Override
    public Student getStudent(String person_id) throws Exception {

        Student student = new Student();
        bindPerson(student, person_id);

        try {
            student.setBranchName(GlobalConfig.connection.getBranchName(person_id));
        } catch (Exception e) {
            student.setBranchName("");
        }

        GlobalConfig.connection.bindPerson(student, person_id);
        GlobalConfig.connection.bindCourse(student.getCourse(), student.getGroupNo());
        GlobalConfig.connection.bindBranch(student.getBranch(), student.getBranchName());

        student.setGroupNo(GlobalConfig.connection.getCourseId(person_id));

        return student;
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
    public void addCourse(Course course) {
        Connection conn = database.getConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall("{ CALL addCourse(?, ?, ?)}");
            callableStatement.setString(1, course.getName());
            callableStatement.setString(2, course.getLanguage());
            callableStatement.setInt(3, Integer.parseInt(course.getPrice()));

            callableStatement.execute();
            callableStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addLesson(Lesson lesson) {
        Connection conn = database.getConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall("{ CALL addLesson(?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, lesson.getName());
            callableStatement.setInt(2, lesson.getCourseId());
            callableStatement.setString(3, lesson.getInstructorId());
            callableStatement.setString(4, lesson.getClassroomId());
            callableStatement.setString(5, lesson.getDate());
            callableStatement.setString(6, lesson.getTs());
            callableStatement.execute();
            callableStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addClassroom(Classroom classroom) {
        Connection conn = database.getConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall("{ CALL addClassroom(?, ?, ?)}");
            callableStatement.setString(1, classroom.getName());
            callableStatement.setInt(2, classroom.getCapacity());
            callableStatement.setString(3, classroom.getBranchName());

            callableStatement.execute();
            callableStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBranch(Branch branch) {
        Connection conn = database.getConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall("{ CALL addBranch(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, branch.getName());
            callableStatement.setArray(2, conn.createArrayOf("text", new ArrayList[]{branch.getPhoneNumbers()}));
            callableStatement.setArray(3, conn.createArrayOf("text", new ArrayList[]{branch.getFaxNumbers()}));
            callableStatement.setString(4, branch.getAddress());
            callableStatement.setArray(5, conn.createArrayOf("text", new ArrayList[]{branch.getPublicTransports()}));
            callableStatement.setArray(6, conn.createArrayOf("text", new ArrayList[]{branch.getPrivateTransports()}));
            callableStatement.setArray(7, conn.createArrayOf("text", new ArrayList[]{branch.getFacilities()}));

            callableStatement.execute();
            callableStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteClassroom(String classroomId) throws Exception {
        Connection conn = database.getConnection();
        CallableStatement callableStatement = conn.prepareCall("{ CALL deleteClassroom(?)}");
        callableStatement.setString(1, classroomId);
        callableStatement.execute();
        callableStatement.close();
    }

    public void deleteCourse(int courseId) throws Exception {
        Connection conn = database.getConnection();
        CallableStatement callableStatement = conn.prepareCall("{ CALL deleteCourse(?)}");
        callableStatement.setInt(1, courseId);
        callableStatement.execute();
        callableStatement.close();
    }

    public void deleteBranch(String branchName) throws Exception {
        Connection conn = database.getConnection();
        CallableStatement callableStatement = conn.prepareCall("{ CALL deleteBranch(?)}");
        callableStatement.setString(1, branchName);
        callableStatement.execute();
        callableStatement.close();
    }

    public void deleteLesson(String lessonName, int courseId) throws Exception {
        Connection conn = database.getConnection();
        CallableStatement callableStatement = conn.prepareCall("{ CALL deleteLesson(?, ?)}");
        callableStatement.setString(1, lessonName);
        callableStatement.setInt(2, courseId);
        callableStatement.execute();
        callableStatement.close();
    }

    @Override
    public void updateStudentInfo(String id, String homePhone, String cellPhone) throws Exception {
        String[] homePhones = {homePhone};
        String[] cellPhones = {cellPhone};

        Connection conn = database.getConnection();
        CallableStatement callableStatement = conn.prepareCall("{ CALL updateStudentInfo(?, ?, ?)}");
        callableStatement.setString(1, id);
        callableStatement.setArray(2, database.getConnection().createArrayOf("text", homePhones));
        callableStatement.setArray(3, database.getConnection().createArrayOf("text", cellPhones));
        callableStatement.execute();
        callableStatement.close();
    }

    @Override
    public void updateInstructorInfo(String instructorId, String homePhone, String cellPhone, String knownLanguages) throws Exception {
        String[] homePhones = {homePhone};
        String[] cellPhones = {cellPhone};
        String[] knownLanguageList = {knownLanguages};

        Connection conn = database.getConnection();
        CallableStatement callableStatement = conn.prepareCall("{ CALL updateInstructorInfo(?, ?, ?, ?)}");
        callableStatement.setString(1, instructorId);
        callableStatement.setArray(2, database.getConnection().createArrayOf("text", homePhones));
        callableStatement.setArray(3, database.getConnection().createArrayOf("text", cellPhones));
        callableStatement.setArray(4, database.getConnection().createArrayOf("text", knownLanguageList));
        callableStatement.execute();
        callableStatement.close();
    }

    @Override
    public ArrayList<Lesson> getInstructorLessons(String instructorId) throws Exception {
        if (instructorId == null)
            return null;

        ArrayList<Lesson> lessons = new ArrayList<>();

        PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT name, classroom_id, lesson_date, lesson_ts FROM getInstructorLessons(?);");
        preparedStatement.setString(1, instructorId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Lesson l = new Lesson();
            l.setName(resultSet.getString("name"));
            l.setClassroomId(resultSet.getString("classroom_id"));
            l.setDate(resultSet.getString("lesson_date"));
            l.setTs(resultSet.getString("lesson_ts"));
            lessons.add(l);
        }

        resultSet.close();
        preparedStatement.close();

        return lessons;
    }

    @Override
    public ArrayList<Instructor> getInstructors(String branch_name) throws Exception {
        ArrayList<Instructor> instructors = new ArrayList<>();

        PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT * FROM getInstructors(?);");
        preparedStatement.setString(1, branch_name);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Instructor i = new Instructor();
            i.setId(resultSet.getString("id"));

            ArrayList<String> known_langs = new ArrayList<>();
            known_langs.add(resultSet.getString("known_languages"));
            i.setKnownLanguages(known_langs);

            i.setPworking_hours(resultSet.getString("pworking_hours"));
            instructors.add(i);
        }

        resultSet.close();
        preparedStatement.close();

        return instructors;
    }

    @Override
    public ArrayList<ArrayList<String>> getClassSchedules(String classroomId) throws Exception {
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT * FROM getClassroomSchedule(?);");
        preparedStatement.setString(1, classroomId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ArrayList<String> data = new ArrayList<>();
            data.add(resultSet.getString("classroom_id"));
            data.add(resultSet.getString("lesson_date"));
            data.add(resultSet.getString("lesson_ts"));
            list.add(data);
        }

        resultSet.close();
        preparedStatement.close();

        return list;
    }

    @Override
    public Course getCourse(int courseId) throws Exception {
        PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT * FROM course WHERE id = (?);");
        preparedStatement.setInt(1, courseId);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String lang = resultSet.getString("language");
        String price = resultSet.getString("price");

        resultSet.close();
        preparedStatement.close();

        Course c = new Course();
        c.setName(name);
        c.setLanguage(lang);
        c.setPrice(price);
        c.setId(id);

        return c;
    }

    @Override
    public Branch getBranch(String branchName) {
        Branch branch = new Branch();

        // In case invalid branchName is send, just return empty branch
        if (branchName == null || branchName.length() < 2)
            return branch;

        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT * FROM BRANCH WHERE name = ?;");
            preparedStatement.setString(1, branchName);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            branch.setName(resultSet.getString("name"));
            branch.setPhoneNumbers(TextProcessor.stringToArray(resultSet.getString("phone_number")));
            branch.setFaxNumbers(TextProcessor.stringToArray(resultSet.getString("fax")));
            branch.setAddress(resultSet.getString("address"));
            branch.setPublicTransports(TextProcessor.stringToArray(resultSet.getString("public_transport")));
            branch.setPrivateTransports(TextProcessor.stringToArray(resultSet.getString("private_transport")));
            branch.setFacilities(TextProcessor.stringToArray(resultSet.getString("facilities")));
            branch.setClassrooms(getClassrooms(branch.getName()));
            resultSet.next();

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return branch;
    }

    @Override
    public Classroom getClassroom(String name) throws Exception {
        Classroom c = null;

        // In case invalid name is send, just return empty branch
        if (name == null || name.length() < 2)
            return null;

        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT * FROM classroom WHERE name = (?);");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int capacity = resultSet.getInt("capacity");
            String branchName = resultSet.getString("branch_name");

            resultSet.close();
            preparedStatement.close();

            c = new Classroom(name, capacity, branchName);

            return c;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public Lesson getLesson(String name, int courseNo) throws Exception {
        Lesson l = null;

        // In case invalid name is send, just return empty branch
        if (name == null || name.length() < 2)
            return null;

        PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT * FROM lesson WHERE name = INITCAP(?) and course_no = ?;");
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, courseNo);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        name = resultSet.getString("name");
        String lessonDate = resultSet.getString("lesson_date");
        String lessonTs = resultSet.getString("lesson_ts");
        String classroomId = resultSet.getString("classroom_id");
        String instructorId = resultSet.getString("instructor_id");

        resultSet.close();
        preparedStatement.close();

        l = new Lesson(name, courseNo, instructorId, classroomId, lessonDate, lessonTs);

        return l;
    }
}
