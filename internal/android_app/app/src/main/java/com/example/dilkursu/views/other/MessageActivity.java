package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dilkursu.R;
import com.example.dilkursu.views.student.StudentActivity;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ImgView;
    private TextView TvMessage;
    private Button BtnOkey;
    private String message;
    private boolean type;  //defines succes or error message
    private Intent returnIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        findViews();


    }

    private void findViews() {
        ImgView = (ImageView)findViewById( R.id.MessageActivity_imgView );
        TvMessage = (TextView)findViewById( R.id.MessageActivity_tv_message );
        BtnOkey = (Button)findViewById( R.id.MessageActivity_btn_okey );

        Bundle bundle = getIntent().getExtras();

        try {
            message = bundle.getString("message");
            TvMessage.setText(message);
            type = bundle.getBoolean("type");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!type){
            //if error messega print error image
            ImgView.setImageResource(R.drawable.error);
        }



        //TODO get authorization of current user and return the user' menu
/*
        if(userstudent){
            returnIntent = new Intent(getApplicationContext(), StudentActivity.class);
        }else if(userteacher){
            returnIntent = new Intent(getApplicationContext(), TeacherActivity.class);
        }else if(userregistrar){
            returnIntent = new Intent(getApplicationContext(), AdminActivity.class);
        }else if(useradmin){
            returnIntent = new Intent(getApplicationContext(), RegistrarActivity.class);

*/
        BtnOkey.setOnClickListener( this );
    }
    @Override
    public void onClick(View v) {
        if ( v == BtnOkey ) {
            // Handle clicks for BtnOkey

            //startActivity(returnIntent);
        }
    }
}


