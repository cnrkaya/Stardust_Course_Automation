package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.adapters.ClassAdapter;

public class BranchListActivity extends AppCompatActivity {

    private ImageButton BtnBack;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list);

        defineVariables();
        defineListeners();
        initRecyclerView();
    }

    public void defineVariables() {
        BtnBack = findViewById(R.id.ListClassesActivity_btn_back);
        recyclerView = findViewById(R.id.ListClassesActivity_recyclerView);
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
    }

}
