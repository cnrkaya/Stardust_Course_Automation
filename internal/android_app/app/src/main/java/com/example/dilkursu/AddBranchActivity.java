package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class AddBranchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton  BtnBack;
    private EditText  EdtTxtBranchName;
    private EditText  EdtTxtFacilities;
    private EditText  EdtTxtPublicTransport;
    private EditText  EdtTxtPrivateTransport;
    private Button  BtnSignIn;

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
         BtnSignIn = (Button)findViewById( R.id.AddBranchActivity_btn_signIn );

         BtnBack.setOnClickListener( this );
         BtnSignIn.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v ==  BtnBack ) {
            // Handle clicks for  BtnBack
        } else if ( v ==  BtnSignIn ) {
            // Handle clicks for  BtnSignIn
        }
    }
}
