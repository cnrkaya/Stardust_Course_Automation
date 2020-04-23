package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class BranchInfoActivity extends AppCompatActivity {
    private TextView BranchInfoActivityBranchName;
    private TextView BranchInfoActivityBranchTransportation;
    private TextView BranchInfoActivityBranchCountry;
    private TextView BranchInfoActivityBranchCity;
    private TextView BranchInfoActivityBranchDistrict;
    private TextView BranchInfoActivityBranchAddress;
    private TextView BranchInfoActivityCourses;
    private TextView BranchInfoActivitySocialFacilities;
    private ImageButton BtnBack;
    private Button BtnClasrooms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_info);

        defineVariables();
        defineListeners();
    }

    private void defineVariables(){
        BranchInfoActivityBranchName = (TextView)findViewById( R.id.BranchInfoActivity_branchName );
        BranchInfoActivityBranchTransportation = (TextView)findViewById( R.id.BranchInfoActivity_branchTransportation );
        BranchInfoActivityBranchCountry = (TextView)findViewById( R.id.BranchInfoActivity_branchCountry );
        BranchInfoActivityBranchCity = (TextView)findViewById( R.id.BranchInfoActivity_branchCity );
        BranchInfoActivityBranchDistrict = (TextView)findViewById( R.id.BranchInfoActivity_branchDistrict );
        BranchInfoActivityBranchAddress = (TextView)findViewById( R.id.BranchInfoActivity_branchAddress );
        BranchInfoActivityCourses = (TextView)findViewById( R.id.BranchInfoActivity_Courses );
        BranchInfoActivitySocialFacilities = (TextView)findViewById( R.id.BranchInfoActivity_socialFacilities );
    }

    private void defineListeners(){
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        BtnClasrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
