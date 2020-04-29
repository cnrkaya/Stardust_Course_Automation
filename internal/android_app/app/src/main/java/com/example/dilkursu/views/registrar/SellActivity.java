package com.example.dilkursu.views.registrar;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Spinner;
        import android.widget.TextView;

        import com.example.dilkursu.R;

public class  SellActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner SpinnerBranches;
    private Spinner SpinnerCourses;
    private Spinner SpinnerKur;
    private TextView TvAmount;
    private Button BtnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        findViews();

    }

    private void findViews() {
        SpinnerBranches = (Spinner)findViewById( R.id.SellActivity_spinner_branches );
        SpinnerCourses = (Spinner)findViewById( R.id.SellActivity_spinner_courses );
        SpinnerKur = (Spinner)findViewById( R.id.SellActivity_spinner_kur );
        TvAmount = (TextView)findViewById( R.id.SellActivity_tv_amount );
        BtnNext = (Button)findViewById( R.id.SellActivity_btn_next );

        //need adapter for spinners
        //TODO get branch list - get course list of branch - get kur list of course

        BtnNext.setOnClickListener( this );
    }
    
    @Override
    public void onClick(View v) {
        if ( v == BtnNext ) {
            // Handle clicks for BtnNext
        }
    }


}
