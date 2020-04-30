package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Credential;
import com.example.dilkursu.views.admin.AdminActivity;
import com.example.dilkursu.views.registrar.RegistrarActivity;
import com.example.dilkursu.views.student.StudentActivity;
import com.example.dilkursu.views.teacher.TeacherActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText edTxt_email;
    private EditText edTxt_password;
    private Button btn_signIn;
    private Button btn_branches;
    private TextView txtV_errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        GlobalConfig.InitializeConnections();

        defineVariables();
        defineListeners();
    }

    public void defineVariables() {
        edTxt_email = findViewById(R.id.SignInActivity_edTxt_email);
        edTxt_password = findViewById(R.id.SignInActivity_edTxt_password);
        btn_signIn = findViewById(R.id.SignInActivity_btn_signIn);
        btn_branches = findViewById(R.id.SignInActivity_btn_branches);
        txtV_errorMessage = findViewById(R.id.SignInActivity_txtV_errorMessage);
    }

    public void defineListeners() {
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Credential credential = login();
                if (credential == null) {
                    //Incorrect login attempt
                    txtV_errorMessage.setVisibility(View.VISIBLE);
                    txtV_errorMessage.setText("Hatalı E-mail/Parola kombinasyonu");
                    clearEditTexts();
                } else {
                    //Login succesfull
                    txtV_errorMessage.setVisibility(View.INVISIBLE);
                    clearEditTexts();
                    Intent intent = null;
                    switch (credential.getAuthorization_level()) {
                        case 1:
                            intent = new Intent(getApplicationContext(), StudentActivity.class);
                            break;
                        case 2:
                            intent = new Intent(getApplicationContext(), TeacherActivity.class);
                            break;
                        case 3:
                            intent = new Intent(getApplicationContext(), RegistrarActivity.class);

                            break;
                        case 4:
                            intent = new Intent(getApplicationContext(), AdminActivity.class);
                            break;
                    }
                    if (intent != null) {
                        intent.putExtra("person_id", credential.getPerson_id());
                        startActivity(intent);
                    }

                }

            }
        });

        btn_branches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BranchListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clearEditTexts() {
        edTxt_email.setText("");
        edTxt_password.setText("");
    }

    private Credential login() {
        String email = edTxt_email.getText().toString();
        String password = edTxt_password.getText().toString();
        Credential credential = GlobalConfig.connection.checkUserCredentials(email, password);
        return credential;
    }
}


