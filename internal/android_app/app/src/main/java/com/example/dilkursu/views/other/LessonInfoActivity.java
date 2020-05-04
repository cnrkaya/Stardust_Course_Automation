package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Lesson;

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
        Lesson l;

        //init lesson informations
        Intent intent = getIntent();
        courseID = intent.getIntExtra("courseID",0);
        lessonName = intent.getStringExtra("lessonName");

        try{
            l = GlobalConfig.connection.getLesson(lessonName, courseID);
            TxtVLessonName.setText(l.getName());
            TxtVTeacherName.setText(l.getInstructorId());
            TxtVClassroom.setText(l.getClassroomId());
            TxtVDate.setText(l.getDate());
            TxtVTime.setText(l.getTs());

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Dersi yüklerken hata olustu." , Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            // Handle clicks for BtnBack
            finish();
        }
    }

}

