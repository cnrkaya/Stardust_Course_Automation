package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TeacherActivity extends AppCompatActivity {
    EditText  edTxt_userName;
    Button  btn_showDepartman;
    Button  btn_timeTable;
    Button  btn_myInfos;
    Button  btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        defineVariables();
        defineListeners();
    }
    public void defineVariables(){
        edTxt_userName = findViewById(R.id.TeacherActivity_edTxt_userName);
        btn_showDepartman = findViewById(R.id.TeacherActivity_btn_showDepartman);
        btn_timeTable = findViewById(R.id.TeacherActivity_btn_timeTable);
        btn_myInfos = findViewById(R.id.TeacherActivity_btn_myInfos);
        btn_logout = findViewById(R.id.TeacherActivity_btn_logout);
    }

    public void defineListeners(){
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
