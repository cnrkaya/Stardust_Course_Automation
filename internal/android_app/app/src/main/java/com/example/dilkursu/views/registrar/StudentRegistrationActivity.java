package com.example.dilkursu.views.registrar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Login;
import com.example.dilkursu.models.Person;

public class StudentRegistrationActivity extends AppCompatActivity {
    private ImageButton BtnBack;
    private TextView Name;
    private TextView Surname;
    private TextView HomeTelephone;
    private TextView Cellphone;
    private TextView IdentityNo;
    private TextView Email;
    private TextView Password;
    private Button BtnSave;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        defineVariables();
        defineListeners();

    }

    private void defineVariables() {
        BtnBack = (ImageButton) findViewById(R.id.StudentRegistrationActivity_btn_back);
        Name = (TextView) findViewById(R.id.StudentRegistrationActivity_name);
        Surname = (TextView) findViewById(R.id.StudentRegistrationActivity_surname);
        Email = (TextView) findViewById(R.id.StudentRegistrationActivity_email);
        Password = (TextView) findViewById(R.id.StudentRegistrationActivity_password);
        HomeTelephone = (TextView) findViewById(R.id.StudentRegistrationActivity_homeTelephone);
        Cellphone = (TextView) findViewById(R.id.StudentRegistrationActivity_cellphone);
        IdentityNo = (TextView) findViewById(R.id.StudentRegistrationActivity_identityNo);
        BtnSave = (Button) findViewById(R.id.StudentRegistrationActivity_btn_signIn);
        progressBar = (ProgressBar) findViewById(R.id.StudentRegistrationActivity_ProgressBar);
    }

    private void defineListeners() {
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                person.setId(IdentityNo.getText().toString());
                person.setFname(Name.getText().toString());
                person.setLname(Surname.getText().toString());
                person.getPhoneNumbers().add(Cellphone.getText().toString());
                person.getHomeNumbers().add(HomeTelephone.getText().toString());
                person.setBranchName("");
                person.setAddress("");

                Login login = new Login();
                login.setAuthorization_level(0);
                login.setEmail(Email.getText().toString());
                login.setPassword(Password.getText().toString());
                login.setPerson_id(IdentityNo.getText().toString());

                new RegisterUserAsyncTask().execute(person, login);

            }
        });
    }


    private class RegisterUserAsyncTask extends AsyncTask<Object, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Object... objects) {
            try {
                GlobalConfig.connection.addPerson((Person) objects[0]);
                GlobalConfig.connection.addLogin((Login) objects[1]);
                return true;
            } catch (Exception e) {
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressBar.setVisibility(View.INVISIBLE);

            if (aBoolean.booleanValue() == true) {
                setResult(RESULT_OK);
            } else {
                setResult(RESULT_CANCELED);
            }

            finish();


        }
    }

}
