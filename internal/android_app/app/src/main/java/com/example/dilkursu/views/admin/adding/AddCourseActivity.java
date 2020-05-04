package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Course;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private EditText EdtTxtLanguage;
    private EditText EdtTxtKurName;
    private EditText EdtTxtPrice;
    private Button BtnAddCourse;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        findViews();
    }

    private void findViews() {
        BtnBack = (ImageButton)findViewById( R.id.AddCourseActivity_btn_back );
        EdtTxtLanguage = (EditText)findViewById( R.id.AddCourseActivity_edtTxt_language );
        EdtTxtKurName = (EditText)findViewById( R.id.AddCourseActivity_edtTxt_kurName );
        EdtTxtPrice = (EditText)findViewById( R.id.AddCourseActivity_edtTxt_price );
        BtnAddCourse = (Button)findViewById( R.id.AddCourseActivity_btn_addCourse );
        progressBar = (ProgressBar) findViewById(R.id.AddCourseActivity_ProgressBar);

        BtnBack.setOnClickListener( this );
        BtnAddCourse.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            finish();
        } else if ( v == BtnAddCourse ) {
            if( addCourse() )
                Toast.makeText(getApplicationContext(), "Kurs Başarıyla Eklendi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Eklenme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();
        }
    }

    private boolean addCourse(){
            String price;
        try {
            price = EdtTxtPrice.getText().toString();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Ücret integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }
        Course course = Course.courseFactory(
                EdtTxtKurName.getText().toString(),
                EdtTxtLanguage.getText().toString(),
                price
                );

        new AddCourseActivity.RegisterCourseAsyncTask().execute(course);
        return true;
    }

    private class RegisterCourseAsyncTask extends AsyncTask<Object, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Object... objects) {
            try {
                GlobalConfig.connection.addCourse((Course)objects[0]);
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
