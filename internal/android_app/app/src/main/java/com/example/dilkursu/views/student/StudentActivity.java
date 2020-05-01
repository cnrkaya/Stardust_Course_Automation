package com.example.dilkursu.views.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Student;
import com.example.dilkursu.views.other.BranchInfoActivity;
import com.example.dilkursu.views.other.PaymentInfoActivity;
import com.example.dilkursu.views.other.SignInActivity;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView EdTxtUserName;
    private Button BtnShowBranch;
    private Button BtnMyPayments;
    private Button BtnMyInfos;
    private Button BtnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        defineCurrentUser();
        defineVariables();
        defineListeners();
        initViews();
    }

    private void defineCurrentUser() {
        if (GlobalConfig.currentUser == null) {
            GlobalConfig.InitializeCurrentUser(GlobalConfig.UserType.STUDENT);
            Intent intent = getIntent();
            String person_id = intent.getStringExtra("person_id");

            GlobalConfig.connection.bindPerson(GlobalConfig.currentUser, person_id);
            GlobalConfig.connection.bindCourse(((Student) GlobalConfig.currentUser).getCourse(), ((Student) GlobalConfig.currentUser).getGroupNo());
            GlobalConfig.connection.bindBranch(GlobalConfig.currentUser.getBranch(), GlobalConfig.currentUser.getBranchName());

            ((Student) GlobalConfig.currentUser).setGroupNo(GlobalConfig.connection.getCourseId(person_id));

        }
    }

    public void defineVariables() {
        EdTxtUserName = (TextView) findViewById(R.id.StudentActivity_edTxt_userName);
        BtnShowBranch = (Button) findViewById(R.id.StudentActivity_btn_showBranch);
        BtnMyPayments = (Button) findViewById(R.id.StudentActivity_btn_myPayments);
        BtnMyInfos = (Button) findViewById(R.id.StudentActivity_btn_myInfos);
        BtnLogout = (Button) findViewById(R.id.StudentActivity_btn_logout);
    }

    public void defineListeners() {
        BtnShowBranch.setOnClickListener(this);
        BtnMyPayments.setOnClickListener(this);
        BtnMyInfos.setOnClickListener(this);
        BtnLogout.setOnClickListener(this);
    }

    private void initViews() {
        String userName = String.format("%s %s", GlobalConfig.currentUser.getFname(), GlobalConfig.currentUser.getLname());
        EdTxtUserName.setText(userName);
    }

    @Override
    public void onClick(View v) {
        if (v == BtnShowBranch) {
            // Handle clicks for BtnShowBranch
            Intent intent = new Intent(getApplicationContext(), BranchInfoActivity.class);
            startActivity(intent);
        } else if (v == BtnMyPayments) {
            // Handle clicks for BtnMyPayments
            Intent intent = new Intent(getApplicationContext(), PaymentInfoActivity.class);
            startActivity(intent);
        } else if (v == BtnMyInfos) {
            // Handle clicks for BtnMyInfos
            Intent intent = new Intent(getApplicationContext(), StudentInfoActivity.class);
            startActivity(intent);
        } else if (v == BtnLogout) {
            // Handle clicks for BtnLogout
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            finish();
            startActivity(intent);
        }
    }
}


