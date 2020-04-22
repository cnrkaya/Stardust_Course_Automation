package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TeacherTimeTableActivity extends AppCompatActivity {
ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_time_table);
        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        btn_back = findViewById(R.id.TeacherTimeTableActivity_btn_back);
    }

    public void defineListeners(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
