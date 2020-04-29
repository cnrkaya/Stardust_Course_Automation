package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dilkursu.R;
import com.example.dilkursu.views.admin.AdminActivity;
import com.example.dilkursu.views.registrar.RegistrarActivity;
import com.example.dilkursu.views.student.StudentActivity;
import com.example.dilkursu.views.teacher.TeacherActivity;

public class SignInActivity extends AppCompatActivity {

    EditText  edTxt_email;
    EditText  edTxt_password;
    Button    btn_signIn;
    Button    btn_branches;
    TextView txtV_errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        edTxt_email = findViewById(R.id.SignInActivity_edTxt_email);
        edTxt_password = findViewById(R.id.SignInActivity_edTxt_password);
        btn_signIn = findViewById(R.id.SignInActivity_btn_signIn);
        btn_branches = findViewById(R.id.SignInActivity_btn_branches);
        txtV_errorMessage = findViewById(R.id.SignInActivity_txtV_errorMessage);
    }
    public void defineListeners(){
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO login(email,password) : returns person details or null
                String aPerson = "asd"; //tmp value replace with returning person object
                if(aPerson == null){
                    //Incorrect login attempt
                    txtV_errorMessage.setVisibility(1);
                    txtV_errorMessage.setText("Hatalı E-mail/Parola kombinasyonu");
                    clearEditTexts();
                }else {
                    //Login succesfull
                    txtV_errorMessage.setVisibility(0);
                    clearEditTexts();
                    Integer personAuthentication = 1;
                    Intent intent;
                    switch (personAuthentication) {
                        case 1:
                            intent = new Intent(getApplicationContext(), StudentActivity.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(getApplicationContext(), TeacherActivity.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(getApplicationContext(), RegistrarActivity.class);
                            startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);
                            break;
                    }

                }

            }
        });

        btn_branches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BranchListActivity.class);
                startActivity(intent);
            }
        });
    }
    private void clearEditTexts(){
        edTxt_email.setText("");
        edTxt_password.setText("");
    }
    private void login(){
        String email = edTxt_email.getText().toString();
        String password = edTxt_password.getText().toString();
        //check from db
        return; // return person object
    }
}


