package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dilkursu.R;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private EditText EdtTxtLanguage;
    private EditText EdtTxtKurName;
    private EditText EdtTxtPrice;
    private Button BtnAddCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        findViews();
    }

    private void findViews() {
        BtnBack = (ImageButton)findViewById( R.id.AddCourseActivity_btn_back );
        EdtTxtLanguage = (EditText)findViewById( R.id.AddCourseActivity_edtTxt_language );
        EdtTxtKurName = (EditText)findViewById( R.id.AddCourseActivity_edtTxt_kurName );
        EdtTxtPrice = (EditText)findViewById( R.id.AddCourseActivity_edtTxt_price );
        BtnAddCourse = (Button)findViewById( R.id.AddCourseActivity_btn_addCourse );

        BtnBack.setOnClickListener( this );
        BtnAddCourse.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            // Handle clicks for BtnBack
            finish();
        } else if ( v == BtnAddCourse ) {
            // Handle clicks for BtnAddCourse
            if( addCourse() )  //TODO save to db
                Toast.makeText(getApplicationContext(), "Kurs Başarıyla Eklendi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Eklenme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();
        }
    }

    private boolean addCourse(){

        EdtTxtLanguage.getText().toString();
        EdtTxtKurName.getText().toString();
        try {
            Integer.valueOf( EdtTxtPrice.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Ücret integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }

        //TODO save to db
        return true;
    }

}
