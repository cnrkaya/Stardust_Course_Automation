package com.example.dilkursu.views.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dilkursu.views.other.BranchInfoActivity;
import com.example.dilkursu.R;
import com.example.dilkursu.views.other.SignInActivity;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {
    TextView edTxt_userName;
    Button  btn_showBranch;
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
        btn_showBranch = findViewById(R.id.TeacherActivity_btn_showBranch);
        btn_timeTable = findViewById(R.id.TeacherActivity_btn_timeTable);
        btn_myInfos = findViewById(R.id.TeacherActivity_btn_myInfos);
        btn_logout = findViewById(R.id.TeacherActivity_btn_logout);
    }

    public void defineListeners(){
        btn_myInfos.setOnClickListener(this);
        btn_timeTable.setOnClickListener(this);
        btn_showBranch.setOnClickListener(this);
        btn_logout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == btn_showBranch) {
            // Handle clicks for BtnShowBranch
            Intent intent = new Intent(getApplicationContext(), BranchInfoActivity.class);
            intent.putExtra("BranchID","11111"); //TODO get branch id from teacher bracnh
            startActivity(intent);
        } else if (v == btn_timeTable) {
            // Handle clicks for BtnMyPayments
            Intent intent = new Intent(getApplicationContext(),TeacherTimeTableActivity.class);
            startActivity(intent);
        } else if (v == btn_myInfos) {
            // Handle clicks for BtnMyInfos
            Intent intent = new Intent(getApplicationContext(),TeacherInfoActivity.class);
            startActivity(intent);
        } else if (v == btn_logout) {
            // Handle clicks for BtnLogout
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            finish();
            startActivity(intent);
        }
    }
}
