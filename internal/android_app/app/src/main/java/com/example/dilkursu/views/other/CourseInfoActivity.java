package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dilkursu.R;

public class CourseInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton BtnBack;
    private TextView TxtVCourseName;
    private TextView TxtVKurName;
    private TextView TxtVPrice;
    private Integer courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        findViews();
        initViews();
    }

    private void findViews() {
        BtnBack = (ImageButton)findViewById( R.id.CoursesInfoActivity_btn_back );
        TxtVCourseName = (TextView)findViewById( R.id.CoursesInfoActivity_txtV_courseName );
        TxtVKurName = (TextView)findViewById( R.id.CoursesInfoActivity_txtV_kurName );
        TxtVPrice = (TextView)findViewById( R.id.CoursesInfoActivity_txtV_price );

        BtnBack.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            // Handle clicks for BtnBack
            finish();
        }
    }
    private void initViews(){
        //init course informations
        Intent intent = getIntent();
        courseID = intent.getIntExtra("courseID",0);

        //TODO connect with DB
        /*
        TxtVCourseName.setText();
        TxtVKurName.setText();
        TxtVPrice.setText();
        */

    }


}
