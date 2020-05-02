package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.dilkursu.R;

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
                //get day and hour from spinner and put extra
                //get course !!id!! from spinner and put extra

                startActivity(intent);
            }
        });
    }
}
