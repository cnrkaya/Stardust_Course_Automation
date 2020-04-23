package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class BranchListActivity extends AppCompatActivity {
private LinearLayout linearLayout;
private ImageButton BtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list);
    }

    public void defineVariables(){
        linearLayout = findViewById(R.id.BranchListActivity_linearLay);
        BtnBack = findViewById(R.id.BranchListActivity_btn_back);
    }
    private void defineListeners(){
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
