package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText tmpActivityName;
    private Button tmpButon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tmpActivityName = (EditText)findViewById( R.id.tmp_activityName );
        tmpButon = (Button)findViewById( R.id.tmp_buton );

        tmpButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String activityToStart;
                activityToStart = "com.example.dilkursu.";
                activityToStart += tmpActivityName.getText().toString();
                try {
                    Class<?> c = Class.forName(activityToStart);
                    Intent intent = new Intent(getApplicationContext(), c);
                    startActivity(intent);
                } catch (ClassNotFoundException ignored) {
                }


            }
        });
    }
}
