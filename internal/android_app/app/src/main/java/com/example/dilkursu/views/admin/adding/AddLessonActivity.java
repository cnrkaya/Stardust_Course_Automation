package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Course;
import com.example.dilkursu.models.Lesson;

public class AddLessonActivity extends AppCompatActivity {
    Button  btn_next;
    EditText lessonName;
    Spinner  spinner_chooseCourse;
    Spinner spinner_day;
    Spinner spinner_hour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        btn_next = findViewById(R.id.AddLessonActivity_btn_next);
        lessonName = findViewById(R.id.AddLessonActivity_edtTxt_lessonName);
        spinner_chooseCourse = findViewById(R.id.AddLessonActivity_spinner_chooseCourse);
        spinner_day = findViewById(R.id.AddLessonActivity_spinner_chooseDay);
        spinner_hour = findViewById(R.id.AddLessonActivity_spinner_chooseHour);

        //TODO get course list from db and make spinner

    }

    public void defineListeners(){
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddLesson2Activity.class);
                intent.putExtra("LessonName",lessonName.getText().toString());
                // TODO: Get day and time with textview
                // TODO: create spinner with the data provided below

                for(Course course: GlobalConfig.connection.getAllCourses()){
                    // here courses have id, language, name and price.
                }

                // TODO: class addLessonToDB to add the lessons to the database, function is found in AddLesson2Activity

                startActivity(intent);
            }
        });
    }

}
