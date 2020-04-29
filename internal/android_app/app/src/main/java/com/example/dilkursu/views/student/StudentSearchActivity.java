package com.example.dilkursu.views.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dilkursu.R;

public class StudentSearchActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private EditText EdtTxtIdentityNo;
    private Button BtnSearch;
    private TextView tvErrorMessage;
    private String studentId;
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
        tvErrorMessage = (TextView)findViewById(R.id.StudentSearchActivity_edtTxt_errorMessage);

        BtnBack.setOnClickListener( this );
        BtnSearch.setOnClickListener( this );

    }

    public void onClick(View v) {
        if ( v == BtnBack ) {
            // Handle clicks for BtnBack
            finish();
        } else if ( v == BtnSearch ) {
            studentId = EdtTxtIdentityNo.getText().toString();
            if(isTrueStudentId(studentId) ) { //TODO check if studentId is true
                Intent intent = new Intent(getApplicationContext(), StudentInfoActivity.class);
                intent.putExtra("StudentId", studentId);
                startActivity(intent);
                tvErrorMessage.setVisibility(View.INVISIBLE);
            } else{
                tvErrorMessage.setVisibility(View.VISIBLE);
            }
        }
    }
    private boolean isTrueStudentId(String studentId){

        return true;
    }



}


