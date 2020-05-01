package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasroom_info);

        defineVariables();
        defineListeners();
    }
    public void defineVariables(){
        BtnBack = findViewById(R.id.ClassroomInfoActivity_btn_back);
        classroomName= findViewById(R.id.ClassroomInfoActivity_classroomName);
        classroomCapacity= findViewById(R.id.ClassroomInfoActivity_classroomCapacity);
        classroomBranch= findViewById(R.id.ClassroomInfoActivity_classroomBranch);
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
