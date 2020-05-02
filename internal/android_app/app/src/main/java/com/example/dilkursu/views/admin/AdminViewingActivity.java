package com.example.dilkursu.views.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dilkursu.R;
import com.example.dilkursu.views.student.BranchInfoActivity;
import com.example.dilkursu.views.other.ClassroomInfoActivity;
import com.example.dilkursu.views.other.CourseInfoActivity;
import com.example.dilkursu.views.other.LessonInfoActivity;

public class AdminViewingActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText VLessonCourseID;
    private EditText VLessonLessonName;
    private Button BtnViewLesson;
    private EditText VClassromClassroomID;
    private Button BtnViewClassroom;
    private EditText VCourseCourseID;
    private Button BtnViewCourse;
    private EditText VBranchBranchName;
    private Button BtnViewBranch;
    private Button BtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_viewing);
        
        findViews();
    }
    private void findViews() {
        VLessonCourseID = (EditText)findViewById( R.id.AdminViewingActivity_vLesson_courseID );
        VLessonLessonName = (EditText)findViewById( R.id.AdminViewingActivity_vLesson_lessonName );
        BtnViewLesson = (Button)findViewById( R.id.AdminViewingActivity_btn_vLesson );
        VClassromClassroomID = (EditText)findViewById( R.id.AdminViewingActivity_vClassrom_classroomID );
        BtnViewClassroom = (Button)findViewById( R.id.AdminViewingActivity_btn_vClassroom );
        VCourseCourseID = (EditText)findViewById( R.id.AdminViewingActivity_vCourse_courseID );
        BtnViewCourse = (Button)findViewById( R.id.AdminViewingActivity_btn_vCourse );
        VBranchBranchName = (EditText)findViewById( R.id.AdminViewingActivity_vBranch_branchName );
        BtnViewBranch = (Button)findViewById( R.id.AdminViewingActivity_btn_vBranch );
        BtnBack = (Button)findViewById( R.id.AdminViewingActivity_btn_back );

        BtnViewLesson.setOnClickListener( this );
        BtnViewClassroom.setOnClickListener( this );
        BtnViewCourse.setOnClickListener( this );
        BtnViewBranch.setOnClickListener( this );
        BtnBack.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == BtnViewLesson ) {
            // Handle clicks for BtnViewLesson
            viewLesson();

        } else if ( v == BtnViewClassroom ) {
            // Handle clicks for BtnViewClassroom
            viewClassroom();

        } else if ( v == BtnViewCourse ) {
            // Handle clicks for BtnViewCourse
            viewCourse();

        } else if ( v == BtnViewBranch ) {
            // Handle clicks for BtnViewBranch
            viewBranch();

        } else if ( v == BtnBack ) {
            // Handle clicks for BtnBack
            finish();
        }
    }
    private boolean viewLesson(){
        Integer courseID;
        String lessonName;

        try {
            courseID = Integer.valueOf(  VLessonCourseID.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Id integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }
         lessonName = VLessonLessonName.getText().toString();
        Intent intent = new Intent(getApplicationContext(), LessonInfoActivity.class);
        intent.putExtra("courseID",courseID);
        intent.putExtra("lessonName",lessonName);
        startActivity(intent);
        return true;
    }
    private boolean viewClassroom(){
        Integer classroomID;
        try {
            classroomID = Integer.valueOf(  VClassromClassroomID.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Id integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }

        Intent intent = new Intent(getApplicationContext(), ClassroomInfoActivity.class);
        intent.putExtra("classroomID",classroomID);
        startActivity(intent);
        return true;
    }


    private boolean viewCourse(){
        Integer courseID;
        try {
            courseID = Integer.valueOf(  VCourseCourseID.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Id integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }
        Intent intent = new Intent(getApplicationContext(), CourseInfoActivity.class);
        intent.putExtra("courseID",courseID);
        startActivity(intent);
        return true;
    }
    private boolean viewBranch(){
    String branchName;
         branchName = VBranchBranchName.getText().toString();
        Intent intent = new Intent(getApplicationContext(), BranchInfoActivity.class);
        intent.putExtra("branchName",branchName);
        startActivity(intent);

        return true;
    }


}

