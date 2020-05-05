package com.example.dilkursu.views.registrar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Course;
import com.example.dilkursu.views.other.CourseInfoActivity;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {
private ArrayAdapter<String> itemsAdapter;
private String branchName;
private ArrayList<Course> courses;
private ArrayList<String> courseNames;
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        courseNames = new ArrayList<>();
        Intent intent = getIntent();
        branchName = intent.getStringExtra("branchName");

        defineViews();

    }

    private void defineViews(){

        courses = GlobalConfig.connection.getCourses(branchName);
        for(Course aCourse : courses){
            courseNames.add(aCourse.getName());
        }

        itemsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, courseNames);
        listView = (ListView) findViewById(R.id.CoursesActivity_listView);
        listView.setAdapter(itemsAdapter);

        //TODO test : this activity not tested yet due to db problems

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer courseId = courses.get(position).getId();
                Intent intent =  new Intent(getApplicationContext(), CourseInfoActivity.class);
                intent.putExtra("courseID",courseId);
                startActivity(intent);
            }
        });  
    }

}
