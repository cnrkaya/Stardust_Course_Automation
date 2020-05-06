package com.example.dilkursu.views.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Instructor;
import com.example.dilkursu.views.student.StudentInfoActivity;

import java.util.ArrayList;

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
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        defineVariables();
        defineListeners();
        initViews();
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
        progressBar = findViewById(R.id.TeacherInfoActivity_ProgressBar);
        progressBar.setVisibility(View.INVISIBLE);
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
            new TeacherInfoActivity.UpdateInfoAsyncTask().execute();
            }
        });

    }

    private void initViews() {
        name.setText(GlobalConfig.currentUser.getFname());
        surname.setText(GlobalConfig.currentUser.getLname());
        homeTelephone.setText(GlobalConfig.currentUser.getHomeNumbers().get(0));
        cellphone.setText(GlobalConfig.currentUser.getPhoneNumbers().get(0));
        startDate.setText(((Instructor) GlobalConfig.currentUser).getStartTimeStamp().toString());
        branch.setText(GlobalConfig.currentUser.getBranchName());
        languages.setText(((Instructor) GlobalConfig.currentUser).getKnownLanguages().get(0));
        identityNo.setText(GlobalConfig.currentUser.getId());
    }

    private boolean updateTeacherInfo() {
        Instructor i = null;
        String updatedHomePhone =homeTelephone.getText().toString();
        String updatedCellPhone = cellphone.getText().toString();
        String updatedLanguages = languages.getText().toString();

        try{
            GlobalConfig.connection.updateInstructorInfo(
                    GlobalConfig.currentUser.getId(),
                    updatedHomePhone,
                    updatedCellPhone,
                    updatedLanguages);
            i = GlobalConfig.connection.getInstructor(GlobalConfig.currentUser.getId());
        }catch (Exception e){
            e.printStackTrace();
            setResult(RESULT_CANCELED);
            return false;
        }
        GlobalConfig.connection.bindPerson(GlobalConfig.currentUser, GlobalConfig.currentUser.getId());
        if( i != null)
            ((Instructor)(GlobalConfig.currentUser)).setKnownLanguages(i.getKnownLanguages());

        setResult(RESULT_OK);
        finish();

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

    private class UpdateInfoAsyncTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            updateTeacherInfo();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }

}
