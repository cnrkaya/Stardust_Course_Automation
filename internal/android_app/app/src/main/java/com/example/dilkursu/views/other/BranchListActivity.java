package com.example.dilkursu.views.other;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.adapters.BranchAdapter;

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
        BtnBack = findViewById(R.id.BranchListActivity_btn_back);
        recyclerView = findViewById(R.id.BranchListActivity_RecyclerView);
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
        BranchAdapter branchAdapter = new BranchAdapter(this, GlobalConfig.getAllBranches());
        recyclerView.setAdapter(branchAdapter);
    }

}
