package com.example.dilkursu.views.student;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.adapters.ClassAdapter;
import com.example.dilkursu.models.Classroom;

import java.util.ArrayList;

public class ClassesActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ImageButton BtnBack;
    private ClassAdapter classAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_classes);

        defineVariables();
        defineListeners();
        initRecyclerView();
    }

    public void defineVariables() {
        recyclerView = (RecyclerView) findViewById(R.id.ListClassesActivity_recyclerView);
        BtnBack = (ImageButton) findViewById(R.id.ListClassesActivity_btn_back);
    }

    private void defineListeners() {
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        classAdapter = new ClassAdapter(this, GlobalConfig.connection.getClassrooms(GlobalConfig.currentUser.getBranchName()));
        recyclerView.setAdapter(classAdapter);
    }


}
