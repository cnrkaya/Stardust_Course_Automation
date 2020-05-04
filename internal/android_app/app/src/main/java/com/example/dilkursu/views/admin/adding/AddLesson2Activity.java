package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Classroom;
import com.example.dilkursu.models.Instructor;
import com.example.dilkursu.models.Lesson;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class AddLesson2Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private Spinner SpinnerAvailableTeachers;
    private Spinner SpinnerAvailableClasrooms;
    private Button BtnComplete;
    private ProgressBar progressBar;

    private String lessonName;
    private int courseId;
    private String lessonDate;
    private String lessonTs;
    private String instructorId;
    private String classroomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson2);

        findViews();
    }

    private void findViews() {
        BtnBack = (ImageButton)findViewById( R.id. AddLesson2Activity_btn_back );
        SpinnerAvailableTeachers = (Spinner)findViewById( R.id. AddLesson2Activity_spinner_availableTeachers );
        SpinnerAvailableClasrooms = (Spinner)findViewById( R.id. AddLesson2Activity_spinner_availableClasrooms );
        BtnComplete = (Button)findViewById( R.id. AddLesson2Activity_btn_complete );
        progressBar = (ProgressBar) findViewById( R.id.AddLesson2Activity_ProgressBar);

        Intent intent = getIntent();
        lessonName = intent.getStringExtra("lessonName");
        courseId = intent.getIntExtra("courseId", -1);
        lessonDate = intent.getStringExtra("lessonDate");
        lessonTs = intent.getStringExtra("lessonTs");

        //TODO: use getAvailableInstructors method(see below) to fill in the spinner

        //TODO list available teachers and classroom on spinner
        // use getAvailableClassroomNames, getAvailableInstructors methods
        instructorId = ""; // TODO: fill in according to spinner
        classroomId = ""; // TODO: fill in according to spinner

        // Test
        System.out.println(getAvailableInstructors("Swindon", "11/29/2019", "15:39:07"));
        System.out.println(getAvailableClassroomNames("11/29/2019", "15:39:07"));


        BtnBack.setOnClickListener( this );
        BtnComplete.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            finish();
        } else if ( v == BtnComplete ) {
            if( addLesson() )
                Toast.makeText(getApplicationContext(), "Kurs Başarıyla Eklendi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Eklenme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();
        }
    }

    private boolean addLesson(){
        if(this.courseId == 0)
            return false;

        Lesson lesson = addLessonToDB(this.lessonName, this.courseId, this.instructorId, this.classroomId, this.lessonDate, this.lessonTs);

        return attachClassroomWithLesson(lesson);
    }

    public Lesson addLessonToDB(String lessonName, int courseId, String insructorId, String classroomId, String lessonDate, String lessonTime){
        Lesson lesson = new Lesson(lessonName, courseId, insructorId, classroomId, lessonDate, lessonTime);
        new AddLesson2Activity.RegisterLessonAsyncTask().execute(lesson);

        return lesson;
    }

    public boolean attachClassroomWithLesson(Lesson lesson){
        // SELECT attachClassroomWithLesson('BZ-45', '3/27/2020', '16:52:38', 'Listening', 3, '72');
        try{
            GlobalConfig.connection.attachClassroomWithLesson(lesson);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public ArrayList<Instructor> getAvailableInstructors(String branchName, String date, String ts){
        // hour -> for 14:00 o'clock, hour=14
        // day -> for tuesday, day = 2
        DateTimeFormatter fIn = DateTimeFormatter.ofPattern( "MM/dd/uuuu" , Locale.UK );
        LocalDate lessonDate_ = LocalDate.parse( date , fIn);
        LocalTime lessonTime_ = LocalTime.parse( ts );

        int day = lessonDate_.getDayOfWeek().getValue();
        int hour = lessonTime_.getHour();

        ArrayList<Instructor> availableInstructors = new ArrayList<>();

        try{
            ArrayList<Instructor> instructors = GlobalConfig.connection.getInstructors(branchName);

            for(Instructor i : instructors){
                instructors.add(i);
                int[][] availableHours = i.getAvailableHours();
                if( availableHours[day][hour] == 0)
                    availableInstructors.remove(i);
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return availableInstructors;
    }

    public ArrayList<String> getAvailableClassroomNames(String lessonDate, String lessonTime){
        ArrayList<String> availableClassrooms = new ArrayList<>();
        ArrayList<ArrayList<String>> classroom_schedules;
        DateTimeFormatter fIn = DateTimeFormatter.ofPattern( "MM/dd/uuuu" , Locale.UK );
        LocalDate lessonDate_ = LocalDate.parse( lessonDate , fIn);
        LocalTime lessonTime_ = LocalTime.parse( lessonTime );
        LocalDate date;
        LocalTime ts;

        try{
            // Get all schedule records which consists of classroom_id, lesson_date, lesson_time
            classroom_schedules = GlobalConfig.connection.getClassSchedules(classroomId);
            for(int i = 0; i < classroom_schedules.size(); i++ ){
                ArrayList<String> classLessons = classroom_schedules.get(i);
                date = LocalDate.parse( classLessons.get(1) , fIn);
                ts = LocalTime.parse(classLessons.get(2));

                if( lessonDate_.equals(date) && lessonTime_.equals(ts))
                    continue;

                availableClassrooms.add(classLessons.get(0));
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Exception when getting class schedules" , Toast.LENGTH_SHORT).show();
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
                GlobalConfig.connection.addLesson((Lesson)objects[0]);
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
