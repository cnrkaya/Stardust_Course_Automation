package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dilkursu.R;

public class AddClassroomActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private EditText EdtTxtClassroomName;
    private EditText EdtTxtCapacity;
    private Spinner SpinnerBranches;
    private Button BtnAddClassroom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classroom);

        findViews();
    }
    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            // Handle clicks for BtnBack
            finish();
        } else if ( v == BtnAddClassroom ) {
            // Handle clicks for BtnAddClassroom
            if( addClassroom() )  //TODO save to db
                Toast.makeText(getApplicationContext(), "Ders Başarıyla Eklendi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Eklenme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();
        }
    }
    private void findViews() {
        BtnBack = (ImageButton)findViewById( R.id.AddClassroomActivity_btn_back );
        EdtTxtClassroomName = (EditText)findViewById( R.id.AddClassroomActivity_edtTxt_classroomName );
        EdtTxtCapacity = (EditText)findViewById( R.id.AddClassroomActivity_edtTxt_capacity );
        SpinnerBranches = (Spinner)findViewById( R.id.AddClassroomActivity_spinner_branches );
        BtnAddClassroom = (Button)findViewById( R.id.AddClassroomActivity_btn_addClassroom );

        BtnBack.setOnClickListener( this );
        BtnAddClassroom.setOnClickListener( this );
    }

    private boolean addClassroom(){

        EdtTxtClassroomName.getText().toString();
        try {
            Integer.valueOf( EdtTxtCapacity.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Kapasite Id integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }

        //SpinnerBranches get branchname from spinner also

        return true;
    }

}
