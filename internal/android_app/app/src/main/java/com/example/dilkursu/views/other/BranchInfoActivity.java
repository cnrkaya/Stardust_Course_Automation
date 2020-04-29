package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dilkursu.R;

public class  BranchInfoActivity extends AppCompatActivity {
    private TextView BranchName;
    private TextView BranchTransportation;
    private TextView BranchAddress;
    private TextView Courses;
    private TextView SocialFacilities;
    private ImageButton BtnBack;
    private Button BtnClasrooms;
    Bundle bundle;
    String branchId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_info);

        bundle = getIntent().getExtras();
        if(bundle != null){
            branchId = bundle.getString("branchId");
        }
        setBranchInformations(); //TODO get informations from database with given branchId
        defineVariables();
        defineListeners();
    }

    private void setBranchInformations() {
        /*
        BranchName.setText();
        BranchTransportation.setText();
        BranchAddress.setText();
        Courses.setText();
        SocialFacilities.setText();
        */
    }

    private void defineVariables(){
        BranchName = (TextView)findViewById( R.id.BranchInfoActivity_branchName );
        BranchTransportation = (TextView)findViewById( R.id.BranchInfoActivity_branchTransportation );
        BranchAddress = (TextView)findViewById( R.id.BranchInfoActivity_branchAddress );
        Courses = (TextView)findViewById( R.id.BranchInfoActivity_Courses );
        SocialFacilities = (TextView)findViewById( R.id.BranchInfoActivity_socialFacilities );
        BtnBack = (ImageButton)findViewById(R.id.BranchInfoActivity_btn_back);
        BtnClasrooms = (Button) findViewById(R.id.BranchInfoActivity_btn_clasrooms);
    }

    private void defineListeners(){
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        BtnClasrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
