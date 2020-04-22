package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {
    EditText  edTxt_userName;
    EditText  edTxt_email;
    EditText  edTxt_password;
    Button    btn_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        edTxt_userName = findViewById(R.id.SignInActivity_edTxt_userName);
        edTxt_email = findViewById(R.id.SignInActivity_edTxt_email);
        edTxt_password = findViewById(R.id.SignInActivity_edTxt_password);
        btn_signIn = findViewById(R.id.SignInActivity_btn_signIn);
    }
    public void defineListeners(){
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ilerle
            }
        });
    }
}


