package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class BranchSearchActivity extends AppCompatActivity {
    private Spinner SpinnerChooseCountry;
    private Spinner SpinnerChooseCity;
    private Spinner SpinnerChooseDistrict;
    private Button BtnSearchBranch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_search);

        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        SpinnerChooseCountry = (Spinner)findViewById( R.id.BranchSearchActivity_spinner_chooseCountry );
        SpinnerChooseCity = (Spinner)findViewById( R.id.BranchSearchActivity_spinner_chooseCity );
        SpinnerChooseDistrict = (Spinner)findViewById( R.id.BranchSearchActivity_spinner_chooseDistrict );
        BtnSearchBranch = (Button)findViewById( R.id.BranchSearchActivity_btn_searchBranch );
    }
    public void defineListeners() {
        BtnSearchBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
