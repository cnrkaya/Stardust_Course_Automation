package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.views.admin.AdminActivity;
import com.example.dilkursu.views.registrar.RegistrarActivity;
import com.example.dilkursu.views.student.StudentActivity;
import com.example.dilkursu.views.teacher.TeacherActivity;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ImgView;
    private TextView TvMessage;
    private Button BtnOkey;
    private String message;
    private boolean type;  //defines succes or error message

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        findViews();
    }

    private void findViews() {
        ImgView = (ImageView) findViewById(R.id.MessageActivity_imgView);
        TvMessage = (TextView) findViewById(R.id.MessageActivity_tv_message);
        BtnOkey = (Button) findViewById(R.id.MessageActivity_btn_okey);

        Bundle bundle = getIntent().getExtras();

        try {
            message = bundle.getString("message");
            TvMessage.setText(message);
            type = bundle.getBoolean("type");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!type) {
            //if error messega print error image
            ImgView.setImageResource(R.drawable.error);
        }

        BtnOkey.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == BtnOkey) {
            // Handle clicks for BtnOkey
            switch (GlobalConfig.currentUserType) {
                case ADMIN:
                    intent = new Intent(getApplicationContext(), AdminActivity.class);
                    break;
                case STUDENT:
                    intent = new Intent(getApplicationContext(), StudentActivity.class);
                    break;
                case REGISTRAR:
                    intent = new Intent(getApplicationContext(), RegistrarActivity.class);
                    break;
                case INSTRUCTOR:
                    intent = new Intent(getApplicationContext(), TeacherActivity.class);
                    break;
            }
            intent.putExtra("person_id", GlobalConfig.currentUser.getId());
            startActivity(intent);
            finish();

        }
    }
}


