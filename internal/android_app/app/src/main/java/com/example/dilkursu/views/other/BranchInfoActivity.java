package com.example.dilkursu.views.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.views.student.ClassesActivity;

public class BranchInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView BranchName;
    private TextView BranchTransportation;
    private TextView BranchAddress;
    private TextView Courses;
    private TextView SocialFacilities;
    private ImageButton BtnBack;
    private Button BtnClasrooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_info);

        defineVariables();
        defineListeners();
        initViews();
    }


    private void defineVariables() {
        BranchName = (TextView) findViewById(R.id.BranchInfoActivity_branchName);
        BranchTransportation = (TextView) findViewById(R.id.BranchInfoActivity_branchTransportation);
        BranchAddress = (TextView) findViewById(R.id.BranchInfoActivity_branchAddress);
        Courses = (TextView) findViewById(R.id.BranchInfoActivity_Courses);
        SocialFacilities = (TextView) findViewById(R.id.BranchInfoActivity_socialFacilities);
        BtnBack = (ImageButton) findViewById(R.id.BranchInfoActivity_btn_back);
        BtnClasrooms = (Button) findViewById(R.id.BranchInfoActivity_btn_clasrooms);
    }

    private void initViews() {
        BranchName.setText(GlobalConfig.currentUser.getBranch().getName());
        BranchTransportation.setText(GlobalConfig.currentUser.getBranch().getPublicTransports().get(0));
        BranchAddress.setText(GlobalConfig.currentUser.getBranch().getAddress());
        BranchAddress.setText(GlobalConfig.currentUser.getBranch().getFacilities().get(0));
    }

    private void defineListeners() {
        BtnBack.setOnClickListener(this);
        BtnClasrooms.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == BtnClasrooms) {
            Intent intent = new Intent(this, ClassesActivity.class);
            startActivity(intent);
        } else if (view == BtnBack) {
            // Handle clicks for BtnBack
            finish();
        }
    }
}
