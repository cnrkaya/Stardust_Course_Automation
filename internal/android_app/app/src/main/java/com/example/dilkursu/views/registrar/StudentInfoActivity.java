package com.example.dilkursu.views.registrar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Student;

import java.util.Random;

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
    private Student student;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        findStudent();
        defineVariables();
        defineListeners();
        initViews();
    }

    private void findStudent() {
        Intent intent = getIntent();
        String studentId = intent.getStringExtra("StudentId");
        try {
            student = GlobalConfig.connection.getStudent(studentId);
        } catch (Exception e) {
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        }


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
        Name.setText(student.getFname());
        Surname.setText(student.getLname());
        HomeTelephone.setText(student.getHomeNumbers().get(0));
        CellPhone.setText(student.getPhoneNumbers().get(0));
        //Course.setText(Integer.toString((student).getGroupNo()));
        Course.setText(Integer.toString(new Random().nextInt(150)));
        Branch.setText((student).getBranchName());
        IdentityNo.setText(student.getId());
        Kur.setText(student.getCourse().getName());
        progressBar.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {
        if (v == BtnBack) {
            // Handle clicks for BtnBack
            finish();
        } else if (v == BtnEdit) {
            // Handle clicks for BtnEdits
            setEditable(true);
        } else if (v == BtnSaveEdits) {
            setEditable(false);
            if (updateStudentInfo()) //TODO save changes of student info to database
                Toast.makeText(this, "Bilgileriniz güncellendi", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "İşlem Başarısız", Toast.LENGTH_LONG).show();
        }
    }

    private void setEditable(boolean mode) {
        Name.setClickable(mode);
        Name.setFocusable(mode);
        Name.setFocusableInTouchMode(mode);

        Surname.setClickable(mode);
        Surname.setFocusable(mode);
        Surname.setFocusableInTouchMode(mode);

        HomeTelephone.setClickable(mode);
        HomeTelephone.setFocusable(mode);
        HomeTelephone.setFocusableInTouchMode(mode);

        CellPhone.setClickable(mode);
        CellPhone.setFocusable(mode);
        CellPhone.setFocusableInTouchMode(mode);

        Course.setClickable(mode);
        Course.setFocusable(mode);
        Course.setFocusableInTouchMode(mode);

        Kur.setClickable(mode);
        Kur.setFocusable(mode);
        Kur.setFocusableInTouchMode(mode);

        Branch.setClickable(mode);
        Branch.setFocusable(mode);
        Branch.setFocusableInTouchMode(mode);

        Payments.setClickable(mode);
        Payments.setFocusable(mode);
        Payments.setFocusableInTouchMode(mode);

        IdentityNo.setClickable(mode);
        IdentityNo.setFocusable(mode);
        IdentityNo.setFocusableInTouchMode(mode);

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

        Name.getText().toString();
        Surname.getText().toString();
        HomeTelephone.getText().toString();
        CellPhone.getText().toString();
        Course.getText().toString();
        Kur.getText().toString();
        Branch.getText().toString();
        Payments.getText().toString();
        IdentityNo.getText().toString();

        return true;
    }
}
