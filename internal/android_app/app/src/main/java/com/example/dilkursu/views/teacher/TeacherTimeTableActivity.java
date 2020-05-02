package com.example.dilkursu.views.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dilkursu.R;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.ArrayList;

public class TeacherTimeTableActivity extends AppCompatActivity {
ImageButton btn_back;
TimetableView timetable;
ArrayList<Schedule> schedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_time_table);
        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        btn_back = findViewById(R.id.TeacherTimeTableActivity_btn_back);

        timetable = findViewById(R.id.TeacherTimeTableActivity_timetable);

        schedules = new ArrayList<Schedule>();

        //TODO get teacher schedule from db and decode
        addSchedule("Mobil Programming","D111",2, new Time(14,00),new Time(17,00));
        addSchedule("Almanca Listening","A10",3, new Time(9,00),new Time(12,00));
    }

    public void defineListeners(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        timetable.setOnStickerSelectEventListener(new TimetableView.OnStickerSelectedListener() {
            @Override
            public void OnStickerSelected(int idx, ArrayList<Schedule> schedules) {
            }
        });
    }
    private void addSchedule(String title,String place,Integer day,Time startTime, Time endTime){
        Schedule schedule = new Schedule();
        schedule.setClassTitle(title); // sets subject
        schedule.setClassPlace(place); // sets place
        //schedule.setProfessorName(); // sets professor
        schedule.setDay(day);
        schedule.setStartTime(startTime); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(endTime); // sets the end of class time (hour,minute)
        schedules.add(schedule);
//.. add one or more schedules
        timetable.add(schedules);
    }
}
