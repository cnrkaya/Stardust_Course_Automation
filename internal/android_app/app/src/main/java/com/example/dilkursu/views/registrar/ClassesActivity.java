package com.example.dilkursu.views.registrar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.adapters.ClassAdapter;

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
        Intent intent = getIntent();

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        classAdapter = new ClassAdapter(this, GlobalConfig.getBranchClassrooms(intent.getStringExtra("branch_name")));
        recyclerView.setAdapter(classAdapter);
    }


}
