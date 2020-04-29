package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.dilkursu.R;

public class ClasroomInfoActivity extends AppCompatActivity {
    private ImageButton BtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasroom_info);
    }
    public void defineVariables(){
        BtnBack = findViewById(R.id.ClasroomInfoActivity_btn_back);
    }
    private void defineListeners(){
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
