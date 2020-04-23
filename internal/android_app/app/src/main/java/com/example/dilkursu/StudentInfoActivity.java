package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StudentInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private ImageButton BtnEdit;
    private TextView EdtTxtName;
    private TextView Surname;
    private TextView HomeTelephone;
    private TextView CellPhone;
    private TextView Course;
    private TextView Kur;
    private TextView Branch;
    private TextView Payments;
    private TextView IdentityNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        BtnBack = (ImageButton)findViewById( R.id.StudentInfoActivity_btn_back );
        BtnEdit= (ImageButton)findViewById( R.id.StudentInfoActivity_btn_edit );
        EdtTxtName = (TextView)findViewById( R.id.StudentInfoActivity_edtTxt_name );
        Surname = (TextView)findViewById( R.id.StudentInfoActivity_surname );
        HomeTelephone = (TextView)findViewById( R.id.StudentInfoActivity_homeTelephone );
        CellPhone = (TextView)findViewById( R.id.StudentInfoActivity_cellPhone );
        Course = (TextView)findViewById( R.id.StudentInfoActivity_course );
        Kur = (TextView)findViewById( R.id.StudentInfoActivity_kur );
        Branch = (TextView)findViewById( R.id.StudentInfoActivity_branch );
        Payments = (TextView)findViewById( R.id.StudentInfoActivity_payments );
        IdentityNo = (TextView)findViewById( R.id.StudentInfoActivity_identityNo );
    }

    public void defineListeners(){
        BtnBack.setOnClickListener( this );
        BtnEdit.setOnClickListener( this );
    }
    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            // Handle clicks for BtnBack
        } else if ( v == BtnEdit ) {
            // Handle clicks for Btn
        }
    }
}
