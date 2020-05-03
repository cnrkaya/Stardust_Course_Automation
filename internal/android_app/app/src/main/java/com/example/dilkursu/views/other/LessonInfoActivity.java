package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dilkursu.R;

public class  LessonInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton BtnBack;
    private TextView TxtVLessonName;
    private TextView TxtVTeacherName;
    private TextView TxtVClassroom;
    private TextView TxtVDate;
    private TextView TxtVTime;
    private Integer courseID;
    private String lessonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_info);

        findViews();
        initViews();
    }
    private void findViews() {
        BtnBack = (ImageButton)findViewById( R.id.LessonInfoActivity_btn_back );
        TxtVLessonName = (TextView)findViewById( R.id.LessonInfoActivity_txtV_lessonName );
        TxtVTeacherName = (TextView)findViewById( R.id.LessonInfoActivity_txtV_teacherName );
        TxtVClassroom = (TextView)findViewById( R.id.LessonInfoActivity_txtV_classroom );
        TxtVDate = (TextView)findViewById( R.id.LessonInfoActivity_txtV_date );
        TxtVTime = (TextView)findViewById( R.id.LessonInfoActivity_txtV_time );



        BtnBack.setOnClickListener( this );
    }

    private void initViews(){
        //init lesson informations
        Intent intent = getIntent();
        courseID = intent.getIntExtra("courseID",0);
        lessonName = intent.getStringExtra("lessonName");

        //TODO connect with DB
        /*
        TxtVLessonName.setText();
        TxtVClassroom.setText(); //may need extra search for finding classromname
        TxtVTeacherName.setText();//may need extra search for finding name
        TxtVDate.setText();
        TxtVTime.setText();
        */

    }
    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            // Handle clicks for BtnBack
            finish();
        }
    }

}

