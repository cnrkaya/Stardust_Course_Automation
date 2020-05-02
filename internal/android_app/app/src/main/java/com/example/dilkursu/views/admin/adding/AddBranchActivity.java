package com.example.dilkursu.views.admin.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dilkursu.R;
import com.example.dilkursu.models.Branch;

public class AddBranchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton  BtnBack;
    private EditText  EdtTxtBranchName;
    private EditText  EdtTxtFacilities;
    private EditText  EdtTxtPublicTransport;
    private EditText  EdtTxtPrivateTransport;
    private EditText  EdtTxtAddress;
    private Button BtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        findViews();
    }
    private void findViews() {
         BtnBack = (ImageButton)findViewById( R.id.AddBranchActivity_btn_back );
         EdtTxtBranchName = (EditText)findViewById( R.id.AddBranchActivity_edtTxt_branchName );
         EdtTxtFacilities = (EditText)findViewById( R.id.AddBranchActivity_edtTxt_facilities );
         EdtTxtPublicTransport = (EditText)findViewById( R.id.AddBranchActivity_edtTxt_publicTransport );
         EdtTxtPrivateTransport = (EditText)findViewById( R.id.AddBranchActivity_edtTxt_privateTransport );
         EdtTxtAddress =  (EditText)findViewById( R.id.AddBranchActivity_address);
         BtnSave = (Button)findViewById( R.id.AddBranchActivity_btn_signIn );

         BtnBack.setOnClickListener( this );
         BtnSave.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v ==  BtnBack ) {
            // Handle clicks for  BtnBack
            finish();
        } else if ( v == BtnSave) {
            // Handle clicks for  BtnSave

            if( addBranch() )
                Toast.makeText(getApplicationContext(), "Şube Başarıyla Eklendi" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Eklenme Sırasında Hata Oluştu" , Toast.LENGTH_LONG).show();
        }

    }


    private boolean addBranch(){

        EdtTxtBranchName.getText().toString();
        EdtTxtFacilities.getText().toString();
        EdtTxtPublicTransport.getText().toString();
        EdtTxtPrivateTransport.getText().toString();
        EdtTxtAddress.getText().toString();

        //TODO save to db

        return true;

    }
}
