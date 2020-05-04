package com.example.dilkursu.views.registrar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Branch;
import com.example.dilkursu.models.Course;
import com.example.dilkursu.views.other.PayActivity;

import java.util.ArrayList;
import java.util.List;

public class SellActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner SpinnerBranches;
    private Spinner SpinnerCourses;
    private Spinner SpinnerKur;
    private TextView TvAmount;
    private Button BtnNext;
    private Branch selectedBranch;
    private String price;
    private EditText studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        defineVariables();
        initViews();
        defineListeners();

    }

    private void defineVariables() {
        SpinnerBranches = (Spinner) findViewById(R.id.SellActivity_spinner_branches);
        SpinnerCourses = (Spinner) findViewById(R.id.SellActivity_spinner_courses);
//        SpinnerKur = (Spinner) findViewById(R.id.SellActivity_spinner_kur);
        TvAmount = (TextView) findViewById(R.id.SellActivity_tv_amount);
        BtnNext = (Button) findViewById(R.id.SellActivity_btn_next);
        studentId = findViewById(R.id.SellActivity_edtTxt_studentID);
    }


    private void initViews() {
        List<String> branchNames = new ArrayList<>();
        for (Branch branch : GlobalConfig.getAllBranches()) {
            branchNames.add(branch.getName());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, branchNames);
        SpinnerBranches.setAdapter(adapter1);

    }

    private void defineListeners() {
        SpinnerBranches.setOnItemSelectedListener(this);
        SpinnerCourses.setOnItemSelectedListener(this);
        SpinnerKur.setOnItemSelectedListener(this);
        BtnNext.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == BtnNext) {
            // Handle clicks for BtnNext
            Intent intent = new Intent(getApplicationContext(), PayActivity.class);
            intent.putExtra("studentId", studentId.getText().toString());
            startActivity(intent);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        List<Course> all_courses = null;
        TvAmount.setText("$0");
        switch (adapterView.getId()) {
            case R.id.SellActivity_spinner_branches:
                selectedBranch = GlobalConfig.connection.getBranch(adapterView.getItemAtPosition(i).toString());
                List<String> courses = new ArrayList<>();
                all_courses = GlobalConfig.connection.getCourses(selectedBranch.getName());
                for (Course course : all_courses) {
                    courses.add(String.valueOf(course.getId()));
                }
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courses);
                SpinnerCourses.setAdapter(adapter2);
                SpinnerCourses.callOnClick();

            case R.id.SellActivity_spinner_courses:
                List<String> levels = new ArrayList<>();
                if( all_courses == null)
                    all_courses = GlobalConfig.connection.getCourses(selectedBranch.getName());
                for (Course course : all_courses) {
                    if (Integer.toString(course.getId()).equals(adapterView.getItemAtPosition(i).toString())) {
                        price = course.getPrice();
                        levels.add(course.getCourseName());
                    }
                }
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, levels);
                SpinnerKur.setAdapter(adapter3);
                break;
//            case R.id.SellActivity_spinner_kur:
//                TvAmount.setText(price);
//                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
