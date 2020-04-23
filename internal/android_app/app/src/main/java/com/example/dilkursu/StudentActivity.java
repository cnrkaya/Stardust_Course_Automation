package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    public void onClick(View v) {
        if (v == BtnShowBranch) {
            // Handle clicks for BtnShowBranch
        } else if (v == BtnMyPayments) {
            // Handle clicks for BtnMyPayments
        } else if (v == BtnMyInfos) {
            // Handle clicks for BtnMyInfos
        } else if (v == BtnLogout) {
            // Handle clicks for BtnLogout
        }
    }
}


