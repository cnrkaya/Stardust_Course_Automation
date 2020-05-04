package com.example.dilkursu.views.other;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dilkursu.R;

public class PaymentInfoActivity extends AppCompatActivity {
    private ImageButton BtnBack;
    private TextView EdtTxtUnpaidAmount;
    private RadioGroup RadioGrup;
    private Button BtnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
        BtnBack = (ImageButton)findViewById( R.id.PaymentInfoActivity_btn_back );
        EdtTxtUnpaidAmount = (TextView)findViewById( R.id.PaymentInfoActivity_edtTxt_unpaidAmount );
        RadioGrup = (RadioGroup)findViewById( R.id.PaymentInfoActivity_radioGrup );
        BtnPay = (Button)findViewById( R.id.PaymentInfoActivity_btn_pay );
    }
    public void defineListeners(){
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
