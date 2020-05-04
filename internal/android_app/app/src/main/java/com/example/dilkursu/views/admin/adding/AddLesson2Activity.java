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

import java.util.ArrayList;

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
        // TODO: SQL-Java getAvailableClassrooms



        //TODO list available teachers and classroom on spinner
        instructorId = ""; // TODO: fill in according to spinner
        classroomId = ""; // TODO: fill in according to spinner

        BtnBack.setOnClickListener( this );
        BtnComplete.setOnClickListener( this );
    }

    public ArrayList<Classroom> getAvailableClassrooms(String branchName, int day, int hour){
        ArrayList<Classroom> availableClassrooms = new ArrayList<>();

//        try{
//            ArrayList<Classroom> classrooms = GlobalConfig.connection.getClassrooms(branchName);
//
//            for(Instructor i : instructors){
//                int[][] availableHours = i.getAvailableHours();
//                if( availableHours[day][hour] == 1)
//                    availableInstructors.add(i);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }

        return availableClassrooms;
    }


    public ArrayList<Instructor> getAvailableInstructors(String branchName, int day, int hour){
        // hour -> for 14:00 o'clock, hour=14
        // day -> for tuesday, day = 2

        ArrayList<Instructor> availableInstructors = new ArrayList<>();

        try{
            ArrayList<Instructor> instructors = GlobalConfig.connection.getInstructors(branchName);

            for(Instructor i : instructors){
                int[][] availableHours = i.getAvailableHours();
                if( availableHours[day][hour] == 1)
                    availableInstructors.add(i);
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return availableInstructors;
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
        if(this.courseId == -1)
            return false;

        //If and only if the start-finish times are not appropriate, checkClassAvailability returns false
        if( !checkClassAvailability(this.classroomId, this.lessonDate, this.lessonTs))
            return false;

        Lesson lesson = addLessonToDB(this.lessonName, this.courseId, this.instructorId, this.classroomId, this.lessonDate, this.lessonTs);

        return lesson != null;
    }

    public Lesson addLessonToDB(String lessonName, int courseId, String insructorId, String classroomId, String lessonDate, String lessonTime){
        Lesson lesson = new Lesson(lessonName, courseId, insructorId, classroomId, lessonDate, lessonTime);
        new AddLesson2Activity.RegisterLessonAsyncTask().execute(lesson);

        return lesson;
    }

    public boolean attachClassroomWithLesson(Lesson lesson, String teacherId){
        // SELECT attachClassroomWithLesson('BZ-45', '3/27/2020', '16:52:38', 'Listening', 3, '72');
        try{
            GlobalConfig.connection.attachClassroomWithLesson(lesson, teacherId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean checkClassAvailability(String classroomId, String lessonDate, String lessonTime){
        ArrayList<ArrayList<String>> classroom_schedules;
        try{

            // Get all schedule records which consists of classroom_id, lesson_date, lesson_time
            classroom_schedules = GlobalConfig.connection.getClassSchedules(classroomId);
            for(int i = 0; i < classroom_schedules.size(); i++ ){
                ArrayList<String> classLessons = classroom_schedules.get(i);
                String classroomLessonDate = classLessons.get(1);
                String classroomLessonTs = classLessons.get(2);

                if (classroomLessonDate.equals(lessonDate) && classroomLessonTs.equals(lessonTime))
                    return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Exception when getting class schedules" , Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
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
