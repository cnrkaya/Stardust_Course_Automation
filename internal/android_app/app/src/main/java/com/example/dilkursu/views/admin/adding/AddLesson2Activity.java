package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dilkursu.R;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.ArrayList;

public class AddLesson2Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton BtnBack;
    private Button BtnNext;
    private TimetableView timetable;
    ArrayList<Schedule> schedules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson2);

        findViews();
    }
    private void findViews() {
        BtnBack = (ImageButton)findViewById( R.id.AddLesson2Activity_btn_back );
        BtnNext = (Button)findViewById( R.id.AddLesson2Activity_btn_complete );
        timetable = (TimetableView)findViewById(R.id.AddLesson2Activity_timetable);

        BtnBack.setOnClickListener( this );
        BtnNext.setOnClickListener( this );

        schedules= new ArrayList<Schedule>();

        fillschedules();

        timetable.setOnStickerSelectEventListener(new TimetableView.OnStickerSelectedListener() {
            @Override
            public void OnStickerSelected(int idx, ArrayList<Schedule> schedules) {
                timetable.remove(idx);
                Toast.makeText(AddLesson2Activity.this, idx + "basıldı", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick(View v) {
        if ( v ==  BtnBack ) {
            // Handle clicks for  BtnBack
        } else if ( v == BtnNext) {
            // Handle clicks for  BtnNext
            Intent intent = new Intent(getApplicationContext(),AddLesson3Activity.class);
            //intent.putExtra()
            startActivity(intent);
        }
    }

    private void fillschedules(){

        for(int i  = 0 ; i<1 ;i++){
            for (int j = 9; j < 17 ; j++){
                Schedule schedule = new Schedule();
                schedule.setClassTitle("C"+j);
                schedule.setDay(i);
                schedule.setStartTime(new Time(j,0)); // sets the beginning of class time (hour,minute)
                schedule.setEndTime(new Time(j,50));
                schedules.add(schedule);
            }
        }
        timetable.add(schedules);

    }
}
