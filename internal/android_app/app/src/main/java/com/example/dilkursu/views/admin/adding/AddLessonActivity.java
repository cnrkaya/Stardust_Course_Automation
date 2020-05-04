package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Course;
import com.example.dilkursu.models.Lesson;

public class AddLessonActivity extends AppCompatActivity {
    Button  btn_next;
    ImageButton btn_back;
    EditText lessonName;
    Spinner  spinner_chooseCourse;
    Spinner spinner_day;
    Spinner spinner_hour;

    EditText temp_chooseCourse;
    EditText temp_chooseDay;
    EditText temp_chooseHour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        btn_next = findViewById(R.id.AddLessonActivity_btn_next);
        btn_back = findViewById(R.id.AddLessonActivity_btn_back);
        lessonName = findViewById(R.id.AddLessonActivity_edtTxt_lessonName);
        //spinner_chooseCourse = findViewById(R.id.AddLessonActivity_spinner_chooseCourse);
        //spinner_day = findViewById(R.id.AddLessonActivity_spinner_chooseDay);
        //spinner_hour = findViewById(R.id.AddLessonActivity_spinner_chooseHour);
        temp_chooseCourse = findViewById(R.id.AddLessonActivity_edtTxt_chooseCourse);
        temp_chooseDay = findViewById(R.id.AddLessonActivity_edtTxt_chooseDay);
        temp_chooseHour = findViewById(R.id.AddLessonActivity_edtTxt_chooseHour);
    }

    public void defineListeners(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GlobalConfig.connection == null)
                    GlobalConfig.InitializeConnections();

                // TODO: create course spinner with the data provided below
                for(Course course: GlobalConfig.connection.getAllCourses()){
                    // here courses have id, language, name and price.
                    // Add these courses to spinner

                }

                // TODO: get these data from UI spinner instead of EditTexts
                int courseId = Integer.parseInt(temp_chooseCourse.getText().toString());
                String lessonTs = temp_chooseHour.getText().toString();
                String lessonDate = temp_chooseDay.getText().toString();

                Intent intent = new Intent(getApplicationContext(),AddLesson2Activity.class);
                intent.putExtra("lessonName",lessonName.getText().toString());
                intent.putExtra("lessonDate", lessonDate);
                intent.putExtra("lessonTs", lessonTs);
                intent.putExtra("courseId", courseId);

                startActivity(intent);
            }
        });
    }

}
