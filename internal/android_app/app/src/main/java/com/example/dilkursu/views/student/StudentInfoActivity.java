package com.example.dilkursu.views.student;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Student;

public class StudentInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private ImageButton BtnBack;
    private ImageButton BtnEdit;
    private Button BtnSaveEdits;
    private EditText Name;
    private EditText Surname;
    private EditText HomeTelephone;
    private EditText CellPhone;
    private EditText Course;
    private EditText Kur;
    private EditText Branch;
    private EditText Payments;
    private EditText IdentityNo;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        defineVariables();
        defineListeners();
        initViews();
    }


    public void defineVariables() {
        BtnBack = (ImageButton) findViewById(R.id.StudentInfoActivity_btn_back);
        BtnEdit = (ImageButton) findViewById(R.id.StudentInfoActivity_btn_edit);
        Name = (EditText) findViewById(R.id.StudentInfoActivity_edtTxt_name);
        Surname = (EditText) findViewById(R.id.StudentInfoActivity_surname);
        HomeTelephone = (EditText) findViewById(R.id.StudentInfoActivity_homeTelephone);
        CellPhone = (EditText) findViewById(R.id.StudentInfoActivity_cellPhone);
        Course = (EditText) findViewById(R.id.StudentInfoActivity_course);
        Kur = (EditText) findViewById(R.id.StudentInfoActivity_kur);
        Branch = (EditText) findViewById(R.id.StudentInfoActivity_branch);
        Payments = (EditText) findViewById(R.id.StudentInfoActivity_payments);
        IdentityNo = (EditText) findViewById(R.id.StudentInfoActivity_identityNo);
        BtnSaveEdits = (Button) findViewById(R.id.StudentInfoActivity_btn_saveEdits);
        progressBar = (ProgressBar) findViewById(R.id.StudentInfoActivity_ProgressBar);
    }

    public void defineListeners() {
        BtnBack.setOnClickListener(this);
        BtnEdit.setOnClickListener(this);
        BtnSaveEdits.setOnClickListener(this);
    }

    private void initViews() {
        Name.setText(GlobalConfig.currentUser.getFname());
        Surname.setText(GlobalConfig.currentUser.getLname());
        HomeTelephone.setText(GlobalConfig.currentUser.getHomeNumbers().get(0));
        CellPhone.setText(GlobalConfig.currentUser.getPhoneNumbers().get(0));
        Course.setText(Integer.toString(((Student) GlobalConfig.currentUser).getGroupNo()));
        Branch.setText(((Student) GlobalConfig.currentUser).getBranchName());
        IdentityNo.setText(((Student) GlobalConfig.currentUser).getId());
        Kur.setText(((Student) GlobalConfig.currentUser).getCourse().getName());
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v == BtnBack) {
            finish();
        } else if (v == BtnEdit) {
            // Handle clicks for BtnEdits
            setEditable(true);
        } else if (v == BtnSaveEdits) {
            setEditable(false);
            new UpdateInfoAsyncTask().execute();
        }
    }

    private void setEditable(boolean mode) {

        HomeTelephone.setClickable(mode);
        HomeTelephone.setFocusable(mode);
        HomeTelephone.setFocusableInTouchMode(mode);

        CellPhone.setClickable(mode);
        CellPhone.setFocusable(mode);
        CellPhone.setFocusableInTouchMode(mode);


        if (mode) {
            BtnSaveEdits.setVisibility(View.VISIBLE);
            BtnEdit.setBackgroundResource(R.drawable.edit2);
        } else {
            BtnSaveEdits.setVisibility(View.INVISIBLE);
            BtnEdit.setBackgroundResource(R.drawable.edit);
        }
        BtnSaveEdits.setClickable(mode);

    }

    private boolean updateStudentInfo() {
        try {
            GlobalConfig.connection.updateStudentInfo(GlobalConfig.currentUser.getId(),
                    HomeTelephone.getText().toString(),
                    CellPhone.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            setResult(RESULT_CANCELED);
            return false;
        }

        setResult(RESULT_OK);
        finish();

        return true;
    }

    private class UpdateInfoAsyncTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            updateStudentInfo();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }


}
