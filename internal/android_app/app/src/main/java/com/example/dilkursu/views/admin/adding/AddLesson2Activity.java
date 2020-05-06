package com.example.dilkursu.views.admin.adding;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Instructor;
import com.example.dilkursu.models.Lesson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddLesson2Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private Spinner SpinnerAvailableTeachers;
    private Spinner SpinnerAvailableClasrooms;
    private Button BtnComplete;
    private ProgressBar progressBar;

    private ArrayList<String> availableClassrooms;
    private ArrayList<Instructor> availableInstructors;
    private ArrayList<String> availableInstructorNames;
    private ArrayList<String> availableInstructorIDs;
    private ArrayAdapter<String> adapterClassroomList;
    private ArrayAdapter<String> adapterInstructorList;
    private String lessonName;
    private int courseId;
    private String lessonDate;
    private String lessonTs;
    private String instructorId;
    private String classroomId;
    private String branchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson2);

        findViews();
    }

    private void findViews() {
        BtnBack = (ImageButton) findViewById(R.id.AddLesson2Activity_btn_back);
        SpinnerAvailableTeachers = (Spinner) findViewById(R.id.AddLesson2Activity_spinner_availableTeachers);
        SpinnerAvailableClasrooms = (Spinner) findViewById(R.id.AddLesson2Activity_spinner_availableClasrooms);
        BtnComplete = (Button) findViewById(R.id.AddLesson2Activity_btn_complete);
        progressBar = (ProgressBar) findViewById(R.id.AddLesson2Activity_ProgressBar);

        Intent intent = getIntent();
        lessonName = intent.getStringExtra("lessonName");
        courseId = intent.getIntExtra("courseId", -1);
        lessonDate = intent.getStringExtra("lessonDate");
        lessonTs = intent.getStringExtra("lessonTs");
        //branchName =  intent.getStringExtra("branchName");
        setSpinners();

        BtnBack.setOnClickListener(this);
        BtnComplete.setOnClickListener(this);
    }

    private void setSpinners() {
        /*TEACHER SPINNERS*/

        //Month July used because compitable with given days of week
        //  01/06 Monday
        //  02/06 Tuesday
        String formattedLessonDate = "0" + lessonDate + "/06/2020";
        String formattedLessonTime = lessonTs + ":00:00";
        //TODO edit the branchName when the passing was completed in the previous activity
        availableInstructors = getAvailableInstructors("Swindon", formattedLessonDate, formattedLessonTime);
        //availableInstructors = getAvailableInstructors(branchName, formattedLessonDate, formattedLessonTime);
        //availableInstructors = getAvailableInstructors("Swindon", "11/29/2019", "15:39:07");

        availableInstructorNames = new ArrayList<>();
        availableInstructorIDs = new ArrayList<>();
        for (Instructor aInst : availableInstructors) {
            String curr_instructor_name = aInst.getFname() + " " + aInst.getLname();
            String curr_instructorID = aInst.getId();
            availableInstructorNames.add(curr_instructor_name);
            availableInstructorIDs.add(curr_instructorID);
        }

        adapterInstructorList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, availableInstructorNames);
        adapterInstructorList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerAvailableTeachers.setAdapter(adapterInstructorList);

        /*CLASSROOM SPINNER*/

        //availableClassrooms = getAvailableClassroomNames("11/29/2019", "15:39:07");
        availableClassrooms = getAvailableClassroomNames(formattedLessonDate, formattedLessonTime);
        adapterClassroomList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, availableClassrooms);
        adapterClassroomList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerAvailableClasrooms.setAdapter(adapterClassroomList);
    }

    @Override
    public void onClick(View v) {
        if (v == BtnBack) {
            finish();
        } else if (v == BtnComplete) {

            int item_pos = SpinnerAvailableTeachers.getSelectedItemPosition(); // TODO TEST
            instructorId = availableInstructorIDs.get(item_pos);

            //TODO Provide classroom Ids to somewhere
            String classroom_name = SpinnerAvailableClasrooms.getSelectedItem().toString(); //returns classroom name
            //The below method can be used to get the selected item position
            //int classroom_item_pos = SpinnerAvailableClasrooms.getSelectedItemPosition();
            classroomId = "";

            if (addLesson())
                Toast.makeText(getApplicationContext(), "Kurs Başarıyla Eklendi", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Eklenme Sırasında Hata Oluştu", Toast.LENGTH_LONG).show();
        }
    }

    private boolean addLesson() {
        if (this.courseId == 0)
            return false;

        Lesson lesson = addLessonToDB(this.lessonName, this.courseId, this.instructorId, this.classroomId, this.lessonDate, this.lessonTs);

        return attachClassroomWithLesson(lesson);
    }

    public Lesson addLessonToDB(String lessonName, int courseId, String insructorId, String classroomId, String lessonDate, String lessonTime) {
        Lesson lesson = new Lesson(lessonName, courseId, insructorId, classroomId, lessonDate, lessonTime);
        new AddLesson2Activity.RegisterLessonAsyncTask().execute(lesson);

        return lesson;
    }

    public boolean attachClassroomWithLesson(Lesson lesson) {
        // SELECT attachClassroomWithLesson('BZ-45', '3/27/2020', '16:52:38', 'Listening', 3, '72');
        try {
            GlobalConfig.connection.attachClassroomWithLesson(lesson);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public ArrayList<Instructor> getAvailableInstructors(String branchName, String date, String ts) {
        // TODO: Fix Required
        int day;
        int month;
        int year;
        try {
            Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            day = d.getDay();
            month = d.getMonth();
            year = d.getYear();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        LocalTime localTime = LocalTime.parse(ts, DateTimeFormatter.ofPattern("HH:mm:ss"));
        int hour = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
        int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);
        int second = localTime.get(ChronoField.SECOND_OF_MINUTE);


//
//
//        String DATA_FORMAT = "dd/MM/yyyy";
//        String NEW_FORMAT = "yyyy/MM/dd";
//
//        SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT);
//        String day = sdf.format(Date.parse(payback.creationDate.date));
//        sdf.da
//
//
//        Date d = sdf.parse(oldDateString);
//        sdf.applyPattern(NEW_FORMAT);
//        newDateString = sdf.format(d);


//        // hour -> for 14:00 o'clock, hour=14
//        // day -> for tuesday, day = 2
//        DateTimeFormatter fIn = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate lessonDate_ = LocalDate.parse(date, fIn);
//        LocalTime lessonTime_ = LocalTime.parse(ts);
//
//        int day = lessonDate_.getDayOfWeek().getValue();
//        int hour = lessonTime_.getHour();
//
//        ArrayList<Instructor> availableInstructors = new ArrayList<>();
//
//        try {
//            ArrayList<Instructor> instructors = GlobalConfig.connection.getInstructors(branchName);
//
//            for (Instructor i : instructors) {
//                instructors.add(i);
//                int[][] availableHours = i.getAvailableHours();
//                if (availableHours[day][hour] == 0)
//                    availableInstructors.remove(i);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        return availableInstructors;
    }

    public ArrayList<String> getAvailableClassroomNames(String lessonDate, String lessonTime) {
        ArrayList<String> availableClassrooms = new ArrayList<>();
        ArrayList<ArrayList<String>> classroom_schedules;
        DateTimeFormatter fIn = DateTimeFormatter.ofPattern("MM/dd/uuuu", Locale.UK);
        LocalDate lessonDate_ = LocalDate.parse(lessonDate, fIn);
        LocalTime lessonTime_ = LocalTime.parse(lessonTime);
        LocalDate date;
        LocalTime ts;

        try {
            // Get all schedule records which consists of classroom_id, lesson_date, lesson_time
            classroom_schedules = GlobalConfig.connection.getClassSchedules(classroomId);
            for (int i = 0; i < classroom_schedules.size(); i++) {
                ArrayList<String> classLessons = classroom_schedules.get(i);
                date = LocalDate.parse(classLessons.get(1), fIn);
                ts = LocalTime.parse(classLessons.get(2));

                if (lessonDate_.equals(date) && lessonTime_.equals(ts))
                    continue;

                availableClassrooms.add(classLessons.get(0));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Exception when getting class schedules", Toast.LENGTH_SHORT).show();
            return null;
        }

        return availableClassrooms;
    }

    private class RegisterLessonAsyncTask extends AsyncTask<Object, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Object... objects) {
            try {
                GlobalConfig.connection.addLesson((Lesson) objects[0]);
                return true;
            } catch (Exception e) {
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressBar.setVisibility(View.INVISIBLE);

            if (aBoolean.booleanValue() == true) {
                setResult(RESULT_OK);
            } else {
                setResult(RESULT_CANCELED);
            }

            finish();
        }
    }
}
