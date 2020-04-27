package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AddLesson2Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton BtnBack;
    private Button BtnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson2);

        findViews();
    }
    private void findViews() {
         BtnBack = (ImageButton)findViewById( R.id.AddLesson2Activity_btn_back );
         BtnNext = (Button)findViewById( R.id.AddLesson2Activity_btn_complete );

         BtnBack.setOnClickListener( this );
         BtnNext.setOnClickListener( this );
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
}
