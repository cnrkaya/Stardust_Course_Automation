package com.example.dilkursu.views.other;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        defineVariables();
        defineListeners();
    }

    public void defineVariables() {
        edTxt_email = findViewById(R.id.SignInActivity_edTxt_email);
        edTxt_password = findViewById(R.id.SignInActivity_edTxt_password);
        btn_signIn = findViewById(R.id.SignInActivity_btn_signIn);
        btn_branches = findViewById(R.id.SignInActivity_btn_branches);
        txtV_errorMessage = findViewById(R.id.SignInActivity_txtV_errorMessage);
        progressBar = findViewById(R.id.SignInActivity_Progressbar);
    }

    public void defineListeners() {
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edTxt_email.getText().toString();
                String password = edTxt_password.getText().toString();
                //Credential credential = GlobalConfig.connection.checkUserCredentials(email, password);
                new CheckUserAsyncTask().execute(email, password);
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


    private class CheckUserAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            Credential credential = GlobalConfig.connection.checkUserCredentials(strings[0], strings[1]);
            if (credential != null) {
                login(credential);
                return true;
            }

            return false;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressBar.setVisibility(View.INVISIBLE);

            if (!aBoolean) {
                //Incorrect login attempt
                txtV_errorMessage.setVisibility(View.VISIBLE);
                txtV_errorMessage.setText("Hatalı E-mail/Parola kombinasyonu");
                clearEditTexts();
            }

        }

        private void login(Credential credential) {
            if (credential == null) {
                return;
            } else {
                //Login succesfull
                txtV_errorMessage.setVisibility(View.INVISIBLE);
                //clearEditTexts();
                Intent intent = null;
                switch (credential.getAuthorization_level()) {
                    case 0:
                        intent = new Intent(getApplicationContext(), StudentActivity.class);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), TeacherActivity.class);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), RegistrarActivity.class);

                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(), AdminActivity.class);
                        break;
                }
                if (intent != null) {
                    intent.putExtra("person_id", credential.getPerson_id());
                    startActivity(intent);
                }

            }

        }

    }


}


