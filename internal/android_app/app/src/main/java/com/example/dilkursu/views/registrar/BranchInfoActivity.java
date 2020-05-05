package com.example.dilkursu.views.registrar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.R;
import com.example.dilkursu.views.other.CourseInfoActivity;

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
        initViews();
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

        Intent intent = getIntent();
        BranchName.setText(intent.getStringExtra("name"));
        BranchTransportation.setText(intent.getStringExtra("transport"));
        BranchAddress.setText(intent.getStringExtra("address"));
        SocialFacilities.setText(intent.getStringExtra("facilities"));
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
            intent.putExtra("branch_name", BranchName.getText().toString());
            startActivity(intent);
        } else if (view == BtnBack) {
            finish();
        }else if (view == BtnCourses) {
            Intent intent = new Intent(this, CourseInfoActivity.class);
            intent.putExtra("courseID",5);   // TODO: send the course id from list of courses of the branch
            // GlobalConfig.connection.getCourses(branch_name); // TODO CANER get the course list from this method
            startActivity(intent);
        }
    }
}
