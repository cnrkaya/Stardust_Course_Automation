package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Branch;
import com.example.dilkursu.models.Course;
import com.example.dilkursu.models.Lesson;

import java.util.ArrayList;

public class AddLessonActivity extends AppCompatActivity {
    Button  btn_next;
    ImageButton btn_back;
    EditText lessonName;
    Spinner  spinner_chooseCourse;
    Spinner spinner_days;
    Spinner spinner_hours;
    Spinner spinner_branches;

    EditText temp_chooseCourse;
    EditText temp_chooseDay;
    EditText temp_chooseHour;

    ArrayList<String> courseNames;
    ArrayList<String> courseIDs;
    ArrayList<String> branchList;

    ArrayAdapter<String> adapterCourseNames;
    ArrayAdapter<String> adapterDaysOfWeek;
    ArrayAdapter<String> adaptertimesOfDay;
    ArrayAdapter<String> adapterBranchList;
    Integer courseId;
    String branchName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        btn_next = findViewById(R.id.AddLessonActivity_btn_next);
        btn_back = findViewById(R.id.AddLessonActivity_btn_back);
        lessonName = findViewById(R.id.AddLessonActivity_edtTxt_lessonName);
        spinner_chooseCourse = findViewById(R.id.AddLessonActivity_spinner_chooseCourse);
        spinner_days = findViewById(R.id.AddLessonActivity_spinner_chooseDay);
        spinner_hours = findViewById(R.id.AddLessonActivity_spinner_chooseHour);
        spinner_branches = findViewById(R.id.AddLessonActivity_spinner_chooseBranch);
       // temp_chooseCourse = findViewById(R.id.AddLessonActivity_edtTxt_chooseCourse);
      //  temp_chooseDay = findViewById(R.id.AddLessonActivity_edtTxt_chooseDay);
      //  temp_chooseHour = findViewById(R.id.AddLessonActivity_edtTxt_chooseHour);

        courseNames = new ArrayList<>();
        courseIDs = new ArrayList<>();
        branchList = new ArrayList<>();

        setSpinner_branches();

        setSpinner_daysAndTimes();



    }
    private void setSpinner_chooseCourse(){

        //TODO get courses of selected branchName
        //global variable branchName is ready to use here
        for(Course course: GlobalConfig.connection.getAllCourses()){
            // here courses have id, language, name and price.
            // Add these courses to spinner
            String aName = course.getCourseName();
            String aId = Integer.toString(course.getId());
            courseNames.add(aName);
            courseIDs.add(aId);
        }

        adapterCourseNames = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,courseNames);
        adapterCourseNames.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_chooseCourse.setAdapter(adapterCourseNames);
    }
    private void setSpinner_daysAndTimes(){

        String[] daysOfWeek = {"Pazartesi", "Salı", "Çarşamba", "Persembe", "Cuma", "Cumartesi", "Pazar"};

        //TODO time interval can be extended if there are no constraint in the db
        String [] timesOfDay = {"09","10","11","12","13","14","15","16","17"};

        adapterDaysOfWeek = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,daysOfWeek);
        adapterDaysOfWeek.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_days.setAdapter(adapterDaysOfWeek);

        adaptertimesOfDay = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,timesOfDay);
        adaptertimesOfDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_hours.setAdapter(adaptertimesOfDay);
    }

    private void setSpinner_branches(){

        //TODO fix : can't pull branch list
        if(GlobalConfig.connection == null)
            GlobalConfig.InitializeConnection();
        ArrayList<Branch> branches = GlobalConfig.getAllBranches();
        for (Branch branch : branches){
            String curr_branch_name = branch.getName();
            Log.i("branch","  :  " + curr_branch_name);
            branchList.add(curr_branch_name);
        }
        adapterBranchList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,branchList);
        adapterBranchList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_branches.setAdapter(adapterBranchList);
    }

    public void defineListeners(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spinner_branches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                branchName = parent.getSelectedItem().toString();
                setSpinner_chooseCourse();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                int course_item_pos = (int)spinner_chooseCourse.getSelectedItemPosition();
                courseId =Integer.valueOf(courseIDs.get(course_item_pos));
                Log.i("courseId"," : " + courseId);

                int lessonDay = spinner_days.getSelectedItemPosition() +1 ; //returns 1 for monday //
                Log.i("WeekDay"," : " + lessonDay);

                String  lessonTime = spinner_hours.getSelectedItem().toString() ;
                Log.i("DayTime"," : " + lessonTime);



              //  int courseId = Integer.parseInt(temp_chooseCourse.getText().toString());
               // String lessonTs = temp_chooseHour.getText().toString();
               // String lessonDate = temp_chooseDay.getText().toString();

                Intent intent = new Intent(getApplicationContext(),AddLesson2Activity.class);
                intent.putExtra("lessonName",lessonName.getText().toString());
                intent.putExtra("lessonDate",Integer.toString(lessonDay));
                intent.putExtra("lessonTs", lessonTime);
               // intent.putExtra("lessonDate", lessonDate);
               // intent.putExtra("lessonTs", lessonTs);
                intent.putExtra("courseId", courseId);
                intent.putExtra("branchName",branchName);

                startActivity(intent);
            }
        });
    }

}
