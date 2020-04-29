package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Username;
    private Button BtnViewing;
    private Button BtnAdding;
    private Button BtnDeleting;
    private Button BtnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        findViews();
    }
    private void findViews() {
        Username = (TextView)findViewById( R.id.AdminActivity_username );
        BtnViewing = (Button)findViewById( R.id.AdminActivity_btn_show );
        BtnAdding = (Button)findViewById( R.id.AdminActivity_btn_add );
        BtnDeleting = (Button)findViewById( R.id.AdminActivity_btn_delete );
        BtnLogout = (Button)findViewById( R.id.AdminActivity_btn_logout );

        BtnViewing.setOnClickListener( this );
        BtnAdding.setOnClickListener( this );
        BtnDeleting.setOnClickListener( this );
        BtnLogout.setOnClickListener( this );
    }
    @Override
    public void onClick(View v) {
        if ( v == BtnViewing ) {
            // Handle clicks for BtnShowBranch
            Intent intent = new Intent(getApplicationContext(),AdminViewingActivity.class);
            startActivity(intent);
        } else if ( v == BtnAdding ) {
            // Handle clicks for BtnAddBranch
            Intent intent = new Intent(getApplicationContext(),AdminAddingActivity.class);
            startActivity(intent);
        } else if ( v == BtnDeleting) {
            // Handle clicks for BtnAddCourse
            Intent intent = new Intent(getApplicationContext(),AdminDeletingActivity.class);
            startActivity(intent);
        } else if ( v == BtnLogout ) {
            // Handle clicks for BtnLogout
            Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
            finish();
            startActivity(intent);
        }
    }
}
