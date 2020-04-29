package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.dilkursu.R;

public class ClassroomListActivity extends AppCompatActivity {
    private ImageButton BtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_list);
    }
    public void defineVariables(){
        BtnBack = findViewById(R.id.ClasroomListActivity_btn_back);
    }
    private void defineListeners(){
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
