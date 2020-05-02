package com.example.dilkursu.views.registrar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.views.other.BranchListActivity;
import com.example.dilkursu.views.other.SignInActivity;
import com.example.dilkursu.views.student.StudentSearchActivity;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Username;
    private Button BtnListBranch;
    private Button BtnStudentRegister;
    private Button BtnStdInfos;
    private Button BtnLogout;
    private Button BtnSell;

    private static final int STUDENT_REGISTER_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        defineCurrentUser();
        defineVariables();
        defineListeners();
        initViews();
    }

    private void defineCurrentUser() {
        if (GlobalConfig.currentUser == null) {
            GlobalConfig.InitializeCurrentUser(GlobalConfig.UserType.STUDENT);
            Intent intent = getIntent();
            String person_id = intent.getStringExtra("person_id");

            GlobalConfig.connection.bindPerson(GlobalConfig.currentUser, person_id);
            GlobalConfig.connection.bindBranch(GlobalConfig.currentUser.getBranch(), GlobalConfig.currentUser.getBranchName());

        }
    }

    public void defineVariables() {
        Username = (TextView) findViewById(R.id.RegistrarActivity_username);
        BtnListBranch = (Button) findViewById(R.id.RegistrarActivity_btn_listBranch);
        BtnStudentRegister = (Button) findViewById(R.id.RegistrarActivity_btn_studentRegister);
        BtnStdInfos = (Button) findViewById(R.id.RegistrarActivity_btn_stdInfos);
        BtnLogout = (Button) findViewById(R.id.RegistrarActivity_btn_logout);
        BtnSell = (Button) findViewById(R.id.RegistrarActivity_btn_sell);

    }

    public void defineListeners() {
        BtnListBranch.setOnClickListener(this);
        BtnStudentRegister.setOnClickListener(this);
        BtnStdInfos.setOnClickListener(this);
        BtnLogout.setOnClickListener(this);
        BtnSell.setOnClickListener(this);
    }

    private void initViews() {
        String userName = String.format("%s %s", GlobalConfig.currentUser.getFname(), GlobalConfig.currentUser.getLname());
        Username.setText(userName);
    }

    @Override
    public void onClick(View v) {

        if (v == BtnListBranch) {
            // Handle clicks for BtnShowBranch
            Intent intent = new Intent(getApplicationContext(), BranchListActivity.class);
            startActivity(intent);
        } else if (v == BtnStudentRegister) {
            // Handle clicks for BtnStudentRegister
            Intent intent = new Intent(getApplicationContext(), StudentRegistrationActivity.class);
            startActivityForResult(intent, STUDENT_REGISTER_ACTIVITY_REQUEST_CODE);
        } else if (v == BtnStdInfos) {
            // Handle clicks for BtnStdInfos
            Intent intent = new Intent(getApplicationContext(), StudentSearchActivity.class);
            startActivity(intent);
        } else if (v == BtnLogout) {
            // Handle clicks for BtnLogout
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            GlobalConfig.currentUser = null;
            finish();
            startActivity(intent);
        } else if (v == BtnSell) {
            // Handle clicks for BtnSell
            Intent intent = new Intent(getApplicationContext(), SellActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == STUDENT_REGISTER_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Kayıt işlemi başaryıla gerçekleşti.", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Kayıt işlemi gerçekleşirken bir hata oluştu!", Toast.LENGTH_LONG).show();
            }
        }

    }
}
