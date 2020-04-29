package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.dilkursu.R;

public class AddLesson3Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private Spinner SpinnerAvailableTeachers;
    private Spinner SpinnerAvailableClasrooms;
    private Button BtnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson3);

        findViews();
    }

    private void findViews() {
        BtnBack = (ImageButton)findViewById( R.id.AddLesson3Activity_btn_back );
        SpinnerAvailableTeachers = (Spinner)findViewById( R.id.AddLesson3Activity_spinner_availableTeachers );
        SpinnerAvailableClasrooms = (Spinner)findViewById( R.id.AddLesson3Activity_spinner_availableClasrooms );
        BtnComplete = (Button)findViewById( R.id.AddLesson3Activity_btn_complete );

        BtnBack.setOnClickListener( this );
        BtnComplete.setOnClickListener( this );
    }
    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            // Handle clicks for BtnBack
        } else if ( v == BtnComplete ) {
            // Handle clicks for BtnComplete
            //TODO addLesson
        }
    }
}
