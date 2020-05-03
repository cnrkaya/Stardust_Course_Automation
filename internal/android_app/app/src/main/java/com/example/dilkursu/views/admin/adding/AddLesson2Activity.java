package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.dilkursu.models.Lesson;

public class AddLesson2Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private Spinner SpinnerAvailableTeachers;
    private Spinner SpinnerAvailableClasrooms;
    private Button BtnComplete;
    private ProgressBar progressBar;


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
        //TODO Get extras from previous activity
        //TODO list available teachers and classroom on spinner

        BtnBack.setOnClickListener( this );
        BtnComplete.setOnClickListener( this );
    }
    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            // Handle clicks for BtnBack
            finish();
        } else if ( v == BtnComplete ) {
            // Handle clicks for BtnComplete

            if( addLesson() )
                Toast.makeText(getApplicationContext(), "Kurs Başarıyla Eklendi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Eklenme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();
        }
    }

    private boolean addLesson(String lessonName, int courseId, String insructorId, String classroomId, String lessonDate, String lessonTime){
        //If and only if the start-finish times are not appropriate, checkClassAvailability returns false
        if( !checkClassAvailability(classroomId, lessonDate, lessonTime))
            return false;


        //TODO addLesson to db
        Lesson lesson = addLessonToDB(lessonName, courseId, insructorId, classroomId, lessonDate, lessonTime);
        attachLessonWithTeacher(lesson);
        attachLessonWithClassroom(lesson);

        return true;
    }

    public Lesson addLessonToDB(String lessonName, int courseId, String insructorId, String classroomId, String lessonDate, String lessonTime){
        Lesson lesson = new Lesson(lessonName, courseId, insructorId, classroomId, lessonDate, lessonTime);
        new AddLesson2Activity.RegisterLessonAsyncTask().execute(lesson);

        return lesson;
    }

    public void attachLessonWithTeacher(Lesson lesson){
        // attach with lesson.instructorId
    }

    public void attachLessonWithClassroom(Lesson lesson){
        // attach with lesson.classroomId
    }

    public boolean checkClassAvailability(String classroomId, String lessonDate, String lessonTime){

        return false;
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
