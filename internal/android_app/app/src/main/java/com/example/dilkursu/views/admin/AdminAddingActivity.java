package com.example.dilkursu.views.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dilkursu.R;
import com.example.dilkursu.views.admin.adding.AddBranchActivity;
import com.example.dilkursu.views.admin.adding.AddClassroomActivity;
import com.example.dilkursu.views.admin.adding.AddCourseActivity;
import com.example.dilkursu.views.admin.adding.AddKurActivity;
import com.example.dilkursu.views.admin.adding.AddLessonActivity;
import com.example.dilkursu.views.other.SignInActivity;

public class AdminAddingActivity extends AppCompatActivity implements View.OnClickListener{
    private Button BtnAddLesson;
    private Button BtnAddClassroom;
    private Button BtnAddKur;
    private Button BtnAddCourse;
    private Button BtnAddBranch;
    private Button BtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_adding);

        findViews();
    }
    private void findViews() {
        BtnAddLesson = (Button)findViewById( R.id.AdminAddingActivity_btn_addLesson );
        BtnAddClassroom = (Button)findViewById( R.id.AdminAddingActivity_btn_addClassroom );
        BtnAddKur = (Button)findViewById( R.id.AdminAddingActivity_btn_addKur );
        BtnAddCourse = (Button)findViewById( R.id.AdminAddingActivity_btn_addCourse );
        BtnAddBranch = (Button)findViewById( R.id.AdminAddingActivity_btn_addBranch );
        BtnBack = (Button)findViewById( R.id.AdminAddingActivity_btn_back );

        BtnAddLesson.setOnClickListener( this );
        BtnAddClassroom.setOnClickListener( this );
        BtnAddKur.setOnClickListener( this );
        BtnAddCourse.setOnClickListener( this );
        BtnAddBranch.setOnClickListener( this );
        BtnBack.setOnClickListener( this );
    }
    @Override
    public void onClick(View v) {
        if ( v == BtnAddLesson ) {
            // Handle clicks for BtnAddLesson
            Intent intent = new Intent(getApplicationContext(), AddLessonActivity.class);
            startActivity(intent);

        } else if ( v == BtnAddClassroom ) {
            // Handle clicks for BtnAddClassroom
            Intent intent = new Intent(getApplicationContext(), AddClassroomActivity.class);
            startActivity(intent);
        } else if ( v == BtnAddKur ) {
            // Handle clicks for BtnAddKur
            Intent intent = new Intent(getApplicationContext(), AddKurActivity.class);
            startActivity(intent);
        } else if ( v == BtnAddCourse ) {
            // Handle clicks for BtnAddCourse
            Intent intent = new Intent(getApplicationContext(), AddCourseActivity.class);
            startActivity(intent);
        } else if ( v == BtnAddBranch ) {
            // Handle clicks for BtnAddBranch
            Intent intent = new Intent(getApplicationContext(), AddBranchActivity.class);
            startActivity(intent);
        } else if ( v == BtnBack ) {
            // Handle clicks for BtnBack
            finish();
        }
    }
}


