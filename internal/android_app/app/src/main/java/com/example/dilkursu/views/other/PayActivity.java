package com.example.dilkursu.views.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dilkursu.GlobalConfig;
import com.example.dilkursu.R;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {
    private ImageButton BtnBack;
    private TextView UnpaidAmount;
    private EditText CardNo;
    private EditText CardHolder;
    private EditText ExpDate;
    private EditText Cvc;
    private Spinner SpinnerInstallmentsNum;
    private Button BtnPay;
    private RadioButton cash;
    private RadioButton installment;
    private TextView tv_installmentHeader;
    private ArrayAdapter<String> adapterInstallmentsNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        defineVariables();
        defineListeners();
        initViews();
    }


    public void defineVariables() {
        BtnBack = (ImageButton) findViewById(R.id.PayActivity_btn_back);
        UnpaidAmount = (TextView) findViewById(R.id.PayActivity_unpaidAmount);
        CardNo = (EditText) findViewById(R.id.PayActivity_cardNo);
        CardHolder = (EditText) findViewById(R.id.PayActivity_cardHolder);
        ExpDate = (EditText) findViewById(R.id.PayActivity_expDate);
        Cvc = (EditText) findViewById(R.id.PayActivity_cvc);
        SpinnerInstallmentsNum = (Spinner) findViewById(R.id.PayActivity_spinner_installmentsNum);
        BtnPay = (Button) findViewById(R.id.PayActivity_btn_pay);
        cash = (RadioButton) findViewById(R.id.PayActivity_rBtn_cash);
        installment = (RadioButton) findViewById(R.id.PayActivity_rBtn_installment);
        tv_installmentHeader = (TextView) findViewById(R.id.PayActivity_tv_installmentHeader);

        ArrayList<String> installmentOptions = new ArrayList<>();
        installmentOptions.add("2");
        installmentOptions.add("4");
        installmentOptions.add("6");
        installmentOptions.add("8");
        installmentOptions.add("10");
        //Adapter for Spinner
        adapterInstallmentsNum = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, installmentOptions);
        adapterInstallmentsNum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerInstallmentsNum.setAdapter(adapterInstallmentsNum);
    }


    public void defineListeners() {
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        BtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String payMessage = UnpaidAmount.getText().toString() + "  ";
                if (installment.isChecked()) {
                    payMessage += SpinnerInstallmentsNum.getSelectedItem().toString() + " Taksitle Ödendi";
                } else {
                    payMessage += "Peşin Ödendi";
                }

                Intent intent1 = getIntent();
                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);

                String branchName = intent1.getStringExtra("branchName");
                String courseName = intent1.getStringExtra("courseName");
                int courseNo = intent1.getIntExtra("courseNo", 0);
                String studentId = intent1.getStringExtra("studentId");

                intent.putExtra("branchName", branchName);
                intent.putExtra("courseName", courseName);
                intent.putExtra("courseNo", courseNo);
                intent.putExtra("studentId", studentId);
                intent.putExtra("message", payMessage);
                intent.putExtra("type", true);


                startActivity(intent);
            }
        });

        installment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (installment.isChecked()) {
                    tv_installmentHeader.setVisibility(View.VISIBLE);
                    SpinnerInstallmentsNum.setVisibility(View.VISIBLE);
                }
            }
        });

        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cash.isChecked()) {
                    tv_installmentHeader.setVisibility(View.INVISIBLE);
                    SpinnerInstallmentsNum.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void initViews() {

    }
}
