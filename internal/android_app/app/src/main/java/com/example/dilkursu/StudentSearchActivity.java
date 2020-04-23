package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class StudentSearchActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private EditText EdtTxtIdentityNo;
    private Button BtnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);

        defineVariables();
    }

    private void defineVariables() {
        BtnBack = (ImageButton)findViewById( R.id.StudentSearchActivity_btn_back );
        EdtTxtIdentityNo = (EditText)findViewById( R.id.StudentSearchActivity_edtTxt_identityNo );
        BtnSearch = (Button)findViewById( R.id.StudentSearchActivity_btn_search );

        BtnBack.setOnClickListener( this );
        BtnSearch.setOnClickListener( this );

    }

    public void onClick(View v) {
        if ( v == BtnBack ) {
            // Handle clicks for BtnBack
        } else if ( v == BtnSearch ) {
            // Handle clicks for BtnSearch
        }
    }

}
