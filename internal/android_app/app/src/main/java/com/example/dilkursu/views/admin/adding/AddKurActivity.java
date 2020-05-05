package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dilkursu.R;

public class AddKurActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private EditText EdtTxtKurName;
    private EditText EdtTxtCourseID;
    private Button BtnAddKur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kur);

        findViews();
    }


    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            // Handle clicks for BtnBack
            finish();
        } else if ( v == BtnAddKur ) {
            // Handle clicks for BtnAddKur
            if( addKur() )
                Toast.makeText(getApplicationContext(), "Kurs Başarıyla Eklendi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Eklenme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();

        }
    }
    private void findViews() {
        BtnBack = (ImageButton)findViewById( R.id.AddKurActivity_btn_back );
        EdtTxtKurName = (EditText)findViewById( R.id.AddKurActivity_edtTxt_KurName );
        EdtTxtCourseID = (EditText)findViewById( R.id.AddKurActivity_edtTxt_CourseID );
        BtnAddKur = (Button)findViewById( R.id.AddKurActivity_btn_addKur );

        BtnBack.setOnClickListener( this );
        BtnAddKur.setOnClickListener( this );
    }

    private boolean addKur(){

        EdtTxtKurName.getText().toString();
        try {
            Integer.valueOf( EdtTxtCourseID.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Course Id integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}


