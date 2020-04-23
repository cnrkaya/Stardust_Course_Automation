package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class EditStudentInfoActivity extends AppCompatActivity {
    private TextView EdtTxtName;
    private TextView Surname;
    private TextView HomeTelephone;
    private TextView CellPhone;
    private TextView IdentityNo;
    private Spinner SpinnerBranch;
    private Spinner SpinnerCourse;
    private Spinner SpinnerKur;
    private Button BtnSignIn;
    private Button BtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student_info);

        defineVariables();
        defineListeners();

    }

    public void defineVariables(){
        BtnBack = (Button) findViewById(R.id.EditStudentInfoActivity_btn_back);
        EdtTxtName = (TextView)findViewById( R.id.EditStudentInfoActivity_edtTxt_name );
        Surname = (TextView)findViewById( R.id.EditStudentInfoActivity_surname );
        HomeTelephone = (TextView)findViewById( R.id.EditStudentInfoActivity_homeTelephone );
        CellPhone = (TextView)findViewById( R.id.EditStudentInfoActivity_cellPhone );
        IdentityNo = (TextView)findViewById( R.id.EditStudentInfoActivity_identityNo );
        SpinnerBranch = (Spinner)findViewById( R.id.EditStudentInfoActivity_spinner_branch );
        SpinnerCourse = (Spinner)findViewById( R.id.EditStudentInfoActivity_spinner_course );
        SpinnerKur = (Spinner)findViewById( R.id.EditStudentInfoActivity_spinner_kur );
        BtnSignIn = (Button)findViewById( R.id.EditStudentInfoActivity_btn_signIn );
    }
    public void defineListeners(){
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
