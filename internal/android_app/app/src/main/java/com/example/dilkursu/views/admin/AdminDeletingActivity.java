package com.example.dilkursu.views.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;

public class AdminDeletingActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText DelLessonCourseID;
    private EditText DelLessonLessonName;
    private Button BtnDelLesson;
    private EditText DelClassroomClassroomId;
    private Button BtnDeleteClassroom;
    private EditText DelCourseCourseID;
    private Button BtnDeleteCourse;
    private EditText DelBranchBranchName;
    private Button BtnDeleteBranch;
    private Button BtnBack;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_deleting);

        findViews();
        
    }

    @Override
    public void onClick(View v) {
        //TODO write functions for all delete operations
        if ( v == BtnDelLesson ) {
            if( deleteLesson() )
                Toast.makeText(getApplicationContext(), "Ders Başarıyla Silindi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Silme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();

        } else if ( v == BtnDeleteClassroom ) {
            if( deleteClassroom() )
                Toast.makeText(getApplicationContext(), "Sınıf Başarıyla Silindi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Silme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();

        }else if ( v == BtnDeleteCourse ) {
            if( deleteCourse() )
                Toast.makeText(getApplicationContext(), "Kurs Başarıyla Silindi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Silme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();

        } else if ( v == BtnDeleteBranch ) {
            if( deleteBranch() )
                Toast.makeText(getApplicationContext(), "Şube Başarıyla Silindi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Silme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();

        } else if ( v == BtnBack ) {
            finish();
        }
    }
    
    private void findViews() {
        DelLessonCourseID = (EditText)findViewById( R.id.AdminDeletingActivity_dltLesson_courseID );
        DelLessonLessonName = (EditText)findViewById( R.id.AdminDeletingActivity_dltLesson_lessonName );
        BtnDelLesson = (Button)findViewById( R.id.AdminDeletingActivity_btn_dltLesson );
        DelClassroomClassroomId = (EditText)findViewById( R.id.AdminDeletingActivity_delClassroom_classroomId );
        BtnDeleteClassroom = (Button)findViewById( R.id.AdminDeletingActivity_btn_deleteClassroom );
        DelCourseCourseID = (EditText)findViewById( R.id.AdminDeletingActivity_delCourse_CourseID );
        BtnDeleteCourse = (Button)findViewById( R.id.AdminDeletingActivity_btn_deleteCourse );
        DelBranchBranchName = (EditText)findViewById( R.id.AdminDeletingActivity_delBranch_branchName );
        BtnDeleteBranch = (Button)findViewById( R.id.AdminDeletingActivity_btn_deleteBranch );
        BtnBack = (Button)findViewById( R.id.AdminDeletingActivity_btn_back );

        BtnDelLesson.setOnClickListener( this );
        BtnDeleteClassroom.setOnClickListener( this );
        BtnDeleteKur.setOnClickListener( this );
        BtnDeleteCourse.setOnClickListener( this );
        BtnDeleteBranch.setOnClickListener( this );
        BtnBack.setOnClickListener( this );
    }

    private boolean deleteLesson(){
        String lessonName;
        Integer courseId;
        try {

            courseId = Integer.parseInt(DelLessonCourseID.getText().toString());
            lessonName = DelLessonLessonName.getText().toString();

            GlobalConfig.connection.deleteLesson(lessonName, courseId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean deleteClassroom(){
        String className;
        try {
            className = DelClassroomClassroomId.getText().toString();
            GlobalConfig.connection.deleteClassroom(className);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean deleteCourse(){

        Integer courseId;
        try {
            courseId = Integer.parseInt(DelCourseCourseID.getText().toString());
            GlobalConfig.connection.deleteCourse(courseId);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Kurs id geçerli ve integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private boolean deleteBranch(){
        String branchName = DelBranchBranchName.getText().toString();
        try {
            GlobalConfig.connection.deleteBranch(branchName);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

