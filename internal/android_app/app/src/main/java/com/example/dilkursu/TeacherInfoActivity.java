package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TeacherInfoActivity extends AppCompatActivity {
    TextView name;
    TextView surname;
    TextView homeTelephone;
    TextView cellphone;
    TextView course;
    TextView startDate;
    TextView branch;
    TextView languages;
    TextView identityNo;
    ImageButton btn_edit ,btn_back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);
    }
    public void defineVariables(){
        name = findViewById(R.id.TeacherInfoActivity_name);
        surname = findViewById(R.id.TeacherInfoActivity_surname);
        homeTelephone = findViewById(R.id.TeacherInfoActivity_homeTelephone);
        cellphone = findViewById(R.id.TeacherInfoActivity_cellphone);
        course = findViewById(R.id.TeacherInfoActivity_course);
        startDate = findViewById(R.id.TeacherInfoActivity_startDate);
        branch = findViewById(R.id.TeacherInfoActivity_branch);
        languages = findViewById(R.id.TeacherInfoActivity_languages);
        identityNo = findViewById(R.id.TeacherInfoActivity_identityNo);
        btn_edit = findViewById(R.id.TeacherInfoActivity_btn_edit);
        btn_back = findViewById(R.id.TeacherInfoActivity_btn_back);
    }
    public void defineListeners(){
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ilerle
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //geri
            }
        });
    }
}
