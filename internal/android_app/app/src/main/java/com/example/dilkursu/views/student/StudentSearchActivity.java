package com.example.dilkursu.views.student;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.views.registrar.StudentInfoActivity;

public class StudentSearchActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private EditText EdtTxtIdentityNo;
    private Button BtnSearch;
    private TextView tvErrorMessage;
    private String studentId;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);

        defineVariables();
    }

    private void defineVariables() {
        BtnBack = (ImageButton) findViewById(R.id.StudentSearchActivity_btn_back);
        EdtTxtIdentityNo = (EditText) findViewById(R.id.StudentSearchActivity_edtTxt_identityNo);
        BtnSearch = (Button) findViewById(R.id.StudentSearchActivity_btn_search);
        tvErrorMessage = (TextView) findViewById(R.id.StudentSearchActivity_edtTxt_errorMessage);
        progressBar = (ProgressBar) findViewById(R.id.StudentSearchActivity_Progressbar);

        BtnBack.setOnClickListener(this);
        BtnSearch.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v == BtnBack) {
            // Handle clicks for BtnBack
            finish();
        } else if (v == BtnSearch) {
            studentId = EdtTxtIdentityNo.getText().toString();
            new FindSearchAsyncTask().execute(studentId);

        }
    }


    private class FindSearchAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                int userType = GlobalConfig.connection.getUserType(strings[0]);
                if (userType == 0) {
                    return true;
                }
            } catch (Exception e) {
            }

            return false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean.booleanValue()) {
                Intent intent = new Intent(getApplicationContext(), StudentInfoActivity.class);
                intent.putExtra("StudentId", studentId);
                startActivity(intent);
                tvErrorMessage.setVisibility(View.INVISIBLE);
                finish();

            }else {
                tvErrorMessage.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }


        }
    }


}


