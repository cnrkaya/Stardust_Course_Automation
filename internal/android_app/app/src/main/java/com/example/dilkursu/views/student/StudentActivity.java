package com.example.dilkursu.views.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dilkursu.views.other.BranchListActivity;
import com.example.dilkursu.views.other.PaymentInfoActivity;
import com.example.dilkursu.R;
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
        defineVariables();
        defineListeners();
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
            Intent intent = new Intent(getApplicationContext(), BranchListActivity.class);
            startActivity(intent);
        } else if (v == BtnMyPayments) {
            // Handle clicks for BtnMyPayments
            Intent intent = new Intent(getApplicationContext(), PaymentInfoActivity.class);
            startActivity(intent);
        } else if (v == BtnMyInfos) {
            // Handle clicks for BtnMyInfos
            Intent intent = new Intent(getApplicationContext(),StudentInfoActivity.class);
            startActivity(intent);
        } else if (v == BtnLogout) {
            // Handle clicks for BtnLogout
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            finish();
            startActivity(intent);
        }
    }
}


