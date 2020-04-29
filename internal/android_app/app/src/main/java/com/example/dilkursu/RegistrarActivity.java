package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Username;
    private Button BtnListBranch;
    private Button BtnStudentRegister;
    private Button BtnStdInfos;
    private Button BtnLogout;
    private Button BtnSell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        defineVariables();
        defineListeners();
    }
    
    public void defineVariables(){
        Username = (TextView)findViewById( R.id.RegistrarActivity_username );
        BtnListBranch = (Button)findViewById( R.id.RegistrarActivity_btn_listBranch );
        BtnStudentRegister = (Button)findViewById( R.id.RegistrarActivity_btn_studentRegister );
        BtnStdInfos = (Button)findViewById( R.id.RegistrarActivity_btn_stdInfos );
        BtnLogout = (Button)findViewById( R.id.RegistrarActivity_btn_logout );
        BtnSell = (Button)findViewById(R.id.RegistrarActivity_btn_sell);

    }

    public void defineListeners(){
        BtnListBranch.setOnClickListener( this );
        BtnStudentRegister.setOnClickListener( this );
        BtnStdInfos.setOnClickListener( this );
        BtnLogout.setOnClickListener( this );
        BtnSell.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if ( v == BtnListBranch ) {
            // Handle clicks for BtnShowBranch
            Intent intent = new Intent(getApplicationContext(),BranchListActivity.class);
            startActivity(intent);
        } else if ( v == BtnStudentRegister ) {
            // Handle clicks for BtnStudentRegister
            Intent intent = new Intent(getApplicationContext(),StudentRegistrationActivity.class);
            startActivity(intent);
        } else if ( v == BtnStdInfos ) {
            // Handle clicks for BtnStdInfos
            Intent intent = new Intent(getApplicationContext(),StudentSearchActivity.class);
            startActivity(intent);
        } else if ( v == BtnLogout ) {
            // Handle clicks for BtnLogout
            Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
            finish();
            startActivity(intent);
        }else if ( v == BtnSell ) {
        // Handle clicks for BtnSell
        Intent intent = new Intent(getApplicationContext(),SellActivity.class);
        startActivity(intent);
        }
        
    }

}
