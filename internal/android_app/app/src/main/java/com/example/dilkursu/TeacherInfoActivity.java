package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherInfoActivity extends AppCompatActivity {
    TextView name;
    TextView surname;
    EditText homeTelephone;
    EditText cellphone;
    TextView startDate;
    TextView branch;
    EditText languages;
    TextView identityNo;
    Button btn_saveEdits;
    ImageButton btn_edit ,btn_back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        defineVariables();
        defineListeners();
    }
    public void defineVariables(){
        name = findViewById(R.id.TeacherInfoActivity_name);
        surname = findViewById(R.id.TeacherInfoActivity_surname);
        homeTelephone = findViewById(R.id.TeacherInfoActivity_homeTelephone);
        cellphone = findViewById(R.id.TeacherInfoActivity_cellphone);
        startDate = findViewById(R.id.TeacherInfoActivity_startDate);
        branch = findViewById(R.id.TeacherInfoActivity_branch);
        languages = findViewById(R.id.TeacherInfoActivity_languages);
        identityNo = findViewById(R.id.TeacherInfoActivity_identityNo);
        btn_edit = findViewById(R.id.TeacherInfoActivity_btn_edit);
        btn_back = findViewById(R.id.TeacherInfoActivity_btn_back);
        btn_saveEdits = findViewById(R.id.TeacherInfoActivity_btn_saveEdits);
    }
    public void defineListeners(){
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditable(true);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_saveEdits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            setEditable(false);
            if( updateTeacherInfo() ) //TODO save changes of teacher's info to database
                Toast.makeText(TeacherInfoActivity.this, "Bilgileriniz", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(TeacherInfoActivity.this, "İşlem Başarısız", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean updateTeacherInfo() {

            homeTelephone.getText().toString();
            cellphone.getText().toString();
            languages.getText().toString();

            return true;

    }

    private void setEditable(boolean mode){

        homeTelephone.setClickable(mode);
        homeTelephone.setFocusable(mode);
        homeTelephone.setFocusableInTouchMode(mode);

        cellphone.setClickable(mode);
        cellphone.setFocusable(mode);
        cellphone.setFocusableInTouchMode(mode);


        languages.setClickable(mode);
        languages.setFocusable(mode);
        languages.setFocusableInTouchMode(mode);



        if(mode) {
            btn_saveEdits.setVisibility(View.VISIBLE);
            btn_edit.setBackgroundResource(R.drawable.edit2);
        }
        else {
            btn_saveEdits.setVisibility(View.INVISIBLE);
            btn_edit.setBackgroundResource(R.drawable.edit);
        }
        btn_saveEdits.setClickable(mode);

    }
}
