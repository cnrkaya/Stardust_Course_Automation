package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class AddLessonActivity extends AppCompatActivity {
    Button  btn_next;
    Spinner  spinner_chooseBranch;
    Spinner  spinner_chooseCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        btn_next = findViewById(R.id.AddLessonActivity_btn_next);
        spinner_chooseBranch = findViewById(R.id.AddLessonActivity_spinner_chooseBranch);
        spinner_chooseCourse = findViewById(R.id.AddLessonActivity_spinner_chooseCourse);


    }

    public void defineListeners(){
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ilerle
            }
        });
    }
}
