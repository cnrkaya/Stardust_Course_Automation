package com.example.dilkursu.views.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Student;
import com.example.dilkursu.views.other.PaymentInfoActivity;
import com.example.dilkursu.views.other.SignInActivity;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView EdTxtUserName;
    private Button BtnShowBranch;
    private Button BtnMyPayments;
    private Button BtnMyInfos;
    private Button BtnLogout;


    private static final int STUDENT_INFO_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        defineCurrentUser();
        defineVariables();
        defineListeners();
        initViews();
    }

    private void defineCurrentUser() {
        GlobalConfig.InitializeCurrentUser(GlobalConfig.UserType.STUDENT);
        Intent intent = getIntent();
        String person_id = intent.getStringExtra("person_id");

        try {
            GlobalConfig.currentUser.setBranchName(GlobalConfig.connection.getBranchName(person_id));
        } catch (Exception e) {
            GlobalConfig.currentUser.setBranchName("");
        }
        GlobalConfig.connection.bindPerson(GlobalConfig.currentUser, person_id);
        GlobalConfig.connection.bindCourse(((Student) GlobalConfig.currentUser).getCourse(), ((Student) GlobalConfig.currentUser).getGroupNo());
        GlobalConfig.connection.bindBranch(GlobalConfig.currentUser.getBranch(), GlobalConfig.currentUser.getBranchName());

        ((Student) GlobalConfig.currentUser).setGroupNo(GlobalConfig.connection.getCourseId(person_id));
    }

    public void defineVariables() {
        EdTxtUserName = (TextView) findViewById(R.id.StudentActivity_edTxt_userName);
        BtnShowBranch = (Button) findViewById(R.id.StudentActivity_btn_showBranch);
        BtnMyPayments = (Button) findViewById(R.id.StudentActivity_btn_myPayments);
        BtnMyInfos = (Button) findViewById(R.id.StudentActivity_btn_myInfos);
        BtnLogout = (Button) findViewById(R.id.StudentActivity_btn_logout);

    }

    public void defineListeners() {
        BtnShowBranch.setOnClickListener(this);
        BtnMyPayments.setOnClickListener(this);
        BtnMyInfos.setOnClickListener(this);
        BtnLogout.setOnClickListener(this);
    }

    private void initViews() {
        String userName = String.format("%s %s", GlobalConfig.currentUser.getFname(), GlobalConfig.currentUser.getLname());
        EdTxtUserName.setText(userName);
    }

    @Override
    public void onClick(View v) {
        if (v == BtnShowBranch) {
            if ("".equals(GlobalConfig.currentUser.getBranch().getName())) {
                // Handle clicks for BtnShowBranch
                Intent intent = new Intent(getApplicationContext(), BranchInfoActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Şube kaydınızı yapılmamış.\nKayıt Yöneticisiyle görüşünüz", Toast.LENGTH_LONG).show();
            }
        } else if (v == BtnMyPayments) {
            // Handle clicks for BtnMyPayments
            Intent intent = new Intent(getApplicationContext(), PaymentInfoActivity.class);
            startActivity(intent);
        } else if (v == BtnMyInfos) {
            // Handle clicks for BtnMyInfos
            Intent intent = new Intent(getApplicationContext(), StudentInfoActivity.class);
            startActivityForResult(intent, STUDENT_INFO_ACTIVITY_REQUEST_CODE);
        } else if (v == BtnLogout) {
            // Handle clicks for BtnLogout
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            GlobalConfig.currentUser = null;
            finish();
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == STUDENT_INFO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Bilgileriniz güncellendi..", Toast.LENGTH_LONG).show();
                defineCurrentUser();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "İşlem başarısız!", Toast.LENGTH_LONG).show();
            }
        }


    }
}


