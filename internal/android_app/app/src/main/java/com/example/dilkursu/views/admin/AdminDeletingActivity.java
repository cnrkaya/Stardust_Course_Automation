package com.example.dilkursu.views.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dilkursu.R;

public class AdminDeletingActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText DelLessonCourseID;
    private EditText DelLessonLessonName;
    private Button BtnDelLesson;
    private EditText DelClassroomClassroomId;
    private Button BtnDeleteClassroom;
    private EditText DelKurCourseID;
    private EditText DelKurKurName;
    private Button BtnDeleteKur;
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
            // Handle clicks for BtnDelLesson
            if( deleteLesson() )
                Toast.makeText(getApplicationContext(), "Ders Başarıyla Silindi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Silme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();

        } else if ( v == BtnDeleteClassroom ) {
            // Handle clicks for BtnDeleteClassroom
            if( deleteClassroom() )
                Toast.makeText(getApplicationContext(), "Sınıf Başarıyla Silindi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Silme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();

        } else if ( v == BtnDeleteKur ) {
            // Handle clicks for BtnDeleteKur
            if( deleteKur() )
                Toast.makeText(getApplicationContext(), "Kur Başarıyla Silindi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Silme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();

        } else if ( v == BtnDeleteCourse ) {
            // Handle clicks for BtnDeleteCourse
            if( deleteCourse() )
                Toast.makeText(getApplicationContext(), "Kurs Başarıyla Silindi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Silme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();

        } else if ( v == BtnDeleteBranch ) {
            // Handle clicks for BtnDeleteBranch
            if( deleteBranch() )
                Toast.makeText(getApplicationContext(), "Şube Başarıyla Silindi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Silme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();

        } else if ( v == BtnBack ) {
            // Handle clicks for BtnBack
            finish();
        }
    }
    
    private void findViews() {
        DelLessonCourseID = (EditText)findViewById( R.id.AdminDeletingActivity_dltLesson_courseID );
        DelLessonLessonName = (EditText)findViewById( R.id.AdminDeletingActivity_dltLesson_lessonName );
        BtnDelLesson = (Button)findViewById( R.id.AdminDeletingActivity_btn_dltLesson );
        DelClassroomClassroomId = (EditText)findViewById( R.id.AdminDeletingActivity_delClassroom_classroomId );
        BtnDeleteClassroom = (Button)findViewById( R.id.AdminDeletingActivity_btn_deleteClassroom );
        DelKurCourseID = (EditText)findViewById( R.id.AdminDeletingActivity_delKur_CourseID );
        DelKurKurName = (EditText)findViewById( R.id.AdminDeletingActivity_delKur_kurName );
        BtnDeleteKur = (Button)findViewById( R.id.AdminDeletingActivity_btn_deleteKur );
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

        try {
            Integer.valueOf( DelLessonCourseID.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Id integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }
        DelLessonLessonName.getText().toString();

        return true;
    }
    private boolean deleteClassroom(){

        try {
            Integer.valueOf( DelClassroomClassroomId.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Id integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private boolean deleteKur(){

        try {
            Integer.valueOf( DelKurCourseID.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Id integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }
        DelKurKurName.getText().toString();

        return true;
    }
    private boolean deleteCourse(){

        try {
            Integer.valueOf( DelCourseCourseID.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Id integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private boolean deleteBranch(){

        DelBranchBranchName.getText().toString();

        return true;
    }
}

