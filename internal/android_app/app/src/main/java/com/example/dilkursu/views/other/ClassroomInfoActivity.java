package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dilkursu.R;

public class ClassroomInfoActivity extends AppCompatActivity {
    private ImageButton BtnBack;
    private TextView classroomName;
    private TextView classroomCapacity;
    private TextView classroomBranch;
    private Integer classroomID;

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
        //init classroom informations
        Intent intent = getIntent();
        classroomID = intent.getIntExtra("classroomID",0);

        //TODO connect with DB
        /*
        classroomName.setText();
        classroomCapacity.setText();
        classroomBranch.setText();
        */


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
