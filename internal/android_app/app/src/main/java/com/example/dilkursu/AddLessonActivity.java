package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class AddLessonActivity extends AppCompatActivity {
    Button  btn_next;
    Spinner  spinner_chooseCountry;
    Spinner  spinner_chooseDistrict;
    Spinner  spinner_chooseCity;
    Spinner  spinner_chooseDepartment;
    Spinner  spinner_chooseLanguageCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        btn_next = findViewById(R.id.AddLessonActivity_btn_next);
        spinner_chooseCountry = findViewById(R.id.AddLessonActivity_spinner_chooseCountry);
        spinner_chooseDistrict = findViewById(R.id.AddLessonActivity_spinner_chooseDistrict);
        spinner_chooseCity= findViewById(R.id.AddLessonActivity_spinner_chooseCity);
        spinner_chooseDepartment= findViewById(R.id.AddLessonActivity_spinner_chooseDepartment);
        spinner_chooseLanguageCourse= findViewById(R.id.AddLessonActivity_spinner_chooseLanguageCourse);
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
