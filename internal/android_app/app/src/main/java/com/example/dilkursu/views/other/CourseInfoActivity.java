package com.example.dilkursu.views.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Course;

public class CourseInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private TextView TxtVCourseName;
    private TextView TxtVKurName;
    private TextView TxtVPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        findViews();
        initViews();
    }

    private void findViews() {
        BtnBack = (ImageButton) findViewById(R.id.CoursesInfoActivity_btn_back);
        TxtVCourseName = (TextView) findViewById(R.id.CoursesInfoActivity_txtV_courseName);
        TxtVKurName = (TextView) findViewById(R.id.CoursesInfoActivity_txtV_kurName);
        TxtVPrice = (TextView) findViewById(R.id.CoursesInfoActivity_txtV_price);

        BtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == BtnBack) {
            finish();
        }
    }

    private void initViews() {
        Intent intent = getIntent();
        Integer courseId = intent.getIntExtra("courseID", -1);
        if( courseId != -1 ){               // if course came from AdminViewingActivity
            try{
                Course c =  GlobalConfig.connection.getCourse(courseId);
                TxtVCourseName.setText(c.getCourseName());
                TxtVKurName.setText(c.getName());
                TxtVPrice.setText(c.getPrice() + "TL");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            // İf course came from BranchListing
            TxtVCourseName.setText(intent.getStringExtra("name"));
            TxtVKurName.setText(intent.getStringExtra("language"));
            TxtVPrice.setText(intent.getStringExtra("price") + " TL");
        }
    }


}
