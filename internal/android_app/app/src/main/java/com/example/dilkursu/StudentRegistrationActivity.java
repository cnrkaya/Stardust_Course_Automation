package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class StudentRegistrationActivity extends AppCompatActivity {
    private ImageButton BtnBack;
    private TextView Name;
    private TextView Surname;
    private TextView HomeTelephone;
    private TextView Cellphone;
    private TextView IdentityNo;
    private Spinner SpinnerBranch;
    private Spinner SpinnerCourse;
    private Spinner SpinnerKur;
    private Button BtnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        defineVariables();
        defineListeners();

    }
    private void defineVariables() {
        BtnBack = (ImageButton)findViewById( R.id.StudentRegistrationActivity_btn_back );
        Name = (TextView)findViewById( R.id.StudentRegistrationActivity_name );
        Surname = (TextView)findViewById( R.id.StudentRegistrationActivity_surname );
        HomeTelephone = (TextView)findViewById( R.id.StudentRegistrationActivity_homeTelephone );
        Cellphone = (TextView)findViewById( R.id.StudentRegistrationActivity_cellphone );
        IdentityNo = (TextView)findViewById( R.id.StudentRegistrationActivity_identityNo );
        SpinnerBranch = (Spinner)findViewById( R.id.StudentRegistrationActivity_spinner_branch );
        SpinnerCourse = (Spinner)findViewById( R.id.StudentRegistrationActivity_spinner_course );
        SpinnerKur = (Spinner)findViewById( R.id.StudentRegistrationActivity_spinner_kur );
        BtnSignIn = (Button)findViewById( R.id.StudentRegistrationActivity_btn_signIn );
    }
    private void defineListeners(){
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
