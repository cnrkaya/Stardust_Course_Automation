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
    private TextView Email;
    private TextView Password;
    private Button BtnSave;
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
        Email = (TextView)findViewById( R.id.StudentRegistrationActivity_email);
        Password = (TextView)findViewById( R.id.StudentRegistrationActivity_password );
        HomeTelephone = (TextView)findViewById( R.id.StudentRegistrationActivity_homeTelephone );
        Cellphone = (TextView)findViewById( R.id.StudentRegistrationActivity_cellphone );
        IdentityNo = (TextView)findViewById( R.id.StudentRegistrationActivity_identityNo );
        BtnSave = (Button)findViewById( R.id.StudentRegistrationActivity_btn_signIn );
    }
    private void defineListeners(){
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStudent();//TODO register student to db
            }
        });
    }

    private void registerStudent(){

        Name.getText().toString();
        Surname.getText().toString();
        Email.getText().toString();
        Password.getText().toString();
        HomeTelephone.getText().toString();
        Cellphone.getText().toString();
        IdentityNo.getText().toString();

    }
}
