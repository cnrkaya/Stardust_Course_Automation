package com.example.dilkursu.views.registrar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.adapters.CourseAdapter;
import com.example.dilkursu.models.Course;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {
    private ArrayAdapter<String> itemsAdapter;
    private String branchName;
    private ArrayList<Course> courses;
    private ArrayList<String> courseNames;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        courseNames = new ArrayList<>();
        Intent intent = getIntent();
        branchName = intent.getStringExtra("branchName");

        defineViews();
        new GetCoursesAsyncTask().execute();
        initViews();

    }


    private void defineViews() {

        recyclerView = (RecyclerView) findViewById(R.id.CoursesActivity_RecyclerView);
        progressBar = (ProgressBar) findViewById(R.id.CoursesActivity_ProgressBar);
        backButton = (ImageButton) findViewById(R.id.CoursesActivity_Button_Back);

    }

    private void initViews() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        CourseAdapter courseAdapter = new CourseAdapter(getApplicationContext(), courses);
        recyclerView.setAdapter(courseAdapter);
    }

    private class GetCoursesAsyncTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            courses = GlobalConfig.connection.getCourses(branchName);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.INVISIBLE);
            initRecyclerView();

        }


    }

}
