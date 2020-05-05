package com.example.dilkursu.views.other;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Log.i("APP_TEST", "sadas");

        new InitAsyncTask().execute();
    }


    private class InitAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            GlobalConfig.InitializeConnection();
            GlobalConfig.InitializeArrays();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            finish();
        }
    }


}
