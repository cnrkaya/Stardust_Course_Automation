package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Username;
    private Button BtnShowBranch;
    private Button BtnAddBranch;
    private Button BtnAddCourse;
    private Button BtnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
    private void findViews() {
        Username = (TextView)findViewById( R.id.AdminActivity_username );
        BtnShowBranch = (Button)findViewById( R.id.AdminActivity_btn_showBranch );
        BtnAddBranch = (Button)findViewById( R.id.AdminActivity_btn_addBranch );
        BtnAddCourse = (Button)findViewById( R.id.AdminActivity_btn_addCourse );
        BtnLogout = (Button)findViewById( R.id.AdminActivity_btn_logout );

        BtnShowBranch.setOnClickListener( this );
        BtnAddBranch.setOnClickListener( this );
        BtnAddCourse.setOnClickListener( this );
        BtnLogout.setOnClickListener( this );
    }
    @Override
    public void onClick(View v) {
        if ( v == BtnShowBranch ) {
            // Handle clicks for BtnShowBranch
        } else if ( v == BtnAddBranch ) {
            // Handle clicks for BtnAddBranch
        } else if ( v == BtnAddCourse ) {
            // Handle clicks for BtnAddCourse
        } else if ( v == BtnLogout ) {
            // Handle clicks for BtnLogout
        }
    }
}
