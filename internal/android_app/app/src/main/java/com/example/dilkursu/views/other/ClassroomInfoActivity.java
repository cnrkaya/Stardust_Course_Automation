package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Classroom;

public class ClassroomInfoActivity extends AppCompatActivity {
    private ImageButton BtnBack;
    private TextView classroomName;
    private TextView classroomCapacity;
    private TextView classroomBranch;
    private String classroomID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasroom_info);

        defineVariables();
        initViews();
        defineListeners();
    }
    public void defineVariables(){
        BtnBack = findViewById(R.id.ClassroomInfoActivity_btn_back);
        classroomName= findViewById(R.id.ClassroomInfoActivity_classroomName);
        classroomCapacity= findViewById(R.id.ClassroomInfoActivity_classroomCapacity);
        classroomBranch= findViewById(R.id.ClassroomInfoActivity_classroomBranch);
    }
    private void initViews(){
        Classroom c;
        //init classroom informations
        Intent intent = getIntent();
        classroomID = intent.getStringExtra("classroomID");
        try{
            c = GlobalConfig.connection.getClassroom(classroomID);
            classroomName.setText(c.getName());
            classroomCapacity.setText(String.valueOf(c.getCapacity()));
            classroomBranch.setText(c.getBranchName());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Sinifi yüklerken hata olustu." , Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void defineListeners(){
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
