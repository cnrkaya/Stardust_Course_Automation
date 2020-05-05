package com.example.dilkursu.views.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Branch;
import com.example.dilkursu.views.other.CourseInfoActivity;
import com.example.dilkursu.views.registrar.CoursesActivity;

public class BranchInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView BranchName;
    private TextView BranchTransportation;
    private TextView BranchAddress;
    private Button BtnCourses;
    private TextView SocialFacilities;
    private ImageButton BtnBack;
    private Button BtnClasrooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_info);

        defineVariables();
        defineListeners();
        Intent intent = getIntent();
        String strExtra = intent.getStringExtra("branchName");
        initViews(strExtra);
    }


    private void defineVariables() {
        BranchName = (TextView) findViewById(R.id.BranchInfoActivity_branchName);
        BranchTransportation = (TextView) findViewById(R.id.BranchInfoActivity_branchTransportation);
        BranchAddress = (TextView) findViewById(R.id.BranchInfoActivity_branchAddress);
        BtnCourses = (Button) findViewById(R.id.BranchInfoActivity_btn_courses);
        SocialFacilities = (TextView) findViewById(R.id.BranchInfoActivity_socialFacilities);
        BtnBack = (ImageButton) findViewById(R.id.BranchInfoActivity_btn_back);
        BtnClasrooms = (Button) findViewById(R.id.BranchInfoActivity_btn_clasrooms);
    }

    private void initViews() {
        BranchName.setText(GlobalConfig.currentUser.getBranch().getName());
        BranchTransportation.setText(GlobalConfig.currentUser.getBranch().getPublicTransports().get(0));
        BranchAddress.setText(GlobalConfig.currentUser.getBranch().getAddress());
        SocialFacilities.setText(GlobalConfig.currentUser.getBranch().getFacilities().get(0));
    }

    private void initViews(String branchName) {
        if (branchName == null)
            initViews();

        Branch branch = GlobalConfig.connection.getBranch(branchName);
        BranchName.setText(branchName);
        if(branch.getPublicTransports() != null){
            BranchTransportation.setText(branch.getPublicTransports().get(0));
        }
        BranchAddress.setText(branch.getAddress());
        if(branch.getFacilities() != null){
            SocialFacilities.setText(branch.getFacilities().get(0));
        }
    }

    private void defineListeners() {
        BtnBack.setOnClickListener(this);
        BtnClasrooms.setOnClickListener(this);
        BtnCourses.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == BtnClasrooms) {
            Intent intent = new Intent(this, ClassesActivity.class);
            startActivity(intent);
        } else if (view == BtnBack) {
            finish();
        }else if (view == BtnCourses) {
            Intent intent = new Intent(this, CoursesActivity.class);
            intent.putExtra("branchName", BranchName.getText().toString());
            startActivity(intent);
        }
    }
}
