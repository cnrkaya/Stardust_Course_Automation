package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;
import com.example.dilkursu.models.Branch;
import com.example.dilkursu.models.Classroom;

import java.util.ArrayList;

public class AddClassroomActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton BtnBack;
    private EditText EdtTxtClassroomName;
    private EditText EdtTxtCapacity;
    private Spinner SpinnerBranches;
    private Button BtnAddClassroom;
    private ProgressBar progressBar;
    private ArrayAdapter<String> adapterBranchList;
    private ArrayList<String> branchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classroom);

        findViews();
    }
    @Override
    public void onClick(View v) {
        if ( v == BtnBack ) {
            finish();
        } else if ( v == BtnAddClassroom ) {
            if( addClassroom() )
                Toast.makeText(getApplicationContext(), "Ders Başarıyla Eklendi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Eklenme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();
        }
    }
    private void findViews() {
        BtnBack = (ImageButton)findViewById( R.id.AddClassroomActivity_btn_back );
        EdtTxtClassroomName = (EditText)findViewById( R.id.AddClassroomActivity_edtTxt_classroomName );
        EdtTxtCapacity = (EditText)findViewById( R.id.AddClassroomActivity_edtTxt_capacity );
        SpinnerBranches = (Spinner)findViewById( R.id.AddClassroomActivity_spinner_branches );
        BtnAddClassroom = (Button)findViewById( R.id.AddClassroomActivity_btn_addClassroom );
        progressBar = (ProgressBar)findViewById( R.id.AddClassroomActivity_ProgressBar);

        branchList = new ArrayList<>();
        //TODO fix : can't pull branch list
        if(GlobalConfig.connection == null)
            GlobalConfig.InitializeConnection();
        ArrayList<Branch> branches = GlobalConfig.getAllBranches();
        for (Branch branch : branches){
            String curr_branch_name = branch.getName();
            branchList.add(curr_branch_name);
        }
        adapterBranchList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,branchList);
        adapterBranchList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerBranches.setAdapter(adapterBranchList);



        BtnBack.setOnClickListener( this );
        BtnAddClassroom.setOnClickListener( this );
    }

    private boolean addClassroom(){
        Classroom classroom;

        String className = EdtTxtClassroomName.getText().toString();
        Integer capacity;
        // TODO: TEST
        String branchName = SpinnerBranches.getSelectedItem().toString();;
        try {
            capacity = Integer.valueOf( EdtTxtCapacity.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Kapasite integer olmalı" , Toast.LENGTH_SHORT).show();
            return false;
        }
        classroom = new Classroom(className, capacity, branchName);
        new AddClassroomActivity.RegisterClassroomAsyncTask().execute(classroom);
        return true;
    }

    private class RegisterClassroomAsyncTask extends AsyncTask<Object, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Object... objects) {
            try {
                GlobalConfig.connection.addClassroom((Classroom)objects[0]);
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
