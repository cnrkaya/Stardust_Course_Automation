package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Username;
    private Button BtnSearchBranch;
    private Button BtnStudentRegister;
    private Button BtnStdInfos;
    private Button BtnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        defineVariables();
        defineListeners();
    }
    
    public void defineVariables(){
        Username = (TextView)findViewById( R.id.RegistrarActivity_username );
        BtnSearchBranch = (Button)findViewById( R.id.RegistrarActivity_btn_searchBranch );
        BtnStudentRegister = (Button)findViewById( R.id.RegistrarActivity_btn_studentRegister );
        BtnStdInfos = (Button)findViewById( R.id.RegistrarActivity_btn_stdInfos );
        BtnLogout = (Button)findViewById( R.id.RegistrarActivity_btn_logout );

    }

    public void defineListeners(){
        BtnSearchBranch.setOnClickListener( this );
        BtnStudentRegister.setOnClickListener( this );
        BtnStdInfos.setOnClickListener( this );
        BtnLogout.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {

        if ( v == BtnSearchBranch ) {
            // Handle clicks for BtnShowBranch
        } else if ( v == BtnStudentRegister ) {
            // Handle clicks for BtnStudentRegister
        } else if ( v == BtnStdInfos ) {
            // Handle clicks for BtnStdInfos
        } else if ( v == BtnLogout ) {
            // Handle clicks for BtnLogout
        }
        
    }

}
