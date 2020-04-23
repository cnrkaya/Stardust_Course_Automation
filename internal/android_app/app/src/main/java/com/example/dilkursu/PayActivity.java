package com.example.dilkursu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity {
    private ImageButton BtnBack;
    private TextView UnpaidAmount;
    private EditText CardNo;
    private EditText CardHolder;
    private EditText ExpDate;
    private EditText Cvc;
    private Spinner SpinnerInstallmentsNum;
    private Button BtnPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        BtnBack = (ImageButton)findViewById( R.id.PayActivity_btn_back );
        UnpaidAmount = (TextView)findViewById( R.id.PayActivity_unpaidAmount );
        CardNo = (EditText)findViewById( R.id.PayActivity_cardNo );
        CardHolder = (EditText)findViewById( R.id.PayActivity_cardHolder );
        ExpDate = (EditText)findViewById( R.id.PayActivity_expDate );
        Cvc = (EditText)findViewById( R.id.PayActivity_cvc );
        SpinnerInstallmentsNum = (Spinner)findViewById( R.id.PayActivity_spinner_installmentsNum );
        BtnPay = (Button)findViewById( R.id.PayActivity_btn_pay );
    }
    public void defineListeners(){
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        BtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
