import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dilkursu.R;

public class EditTeacherInfoActivity extends AppCompatActivity implements View.OnClickListener{
        private ImageButton BtnBack;
        private TextView Name;
        private TextView Surname;
        private TextView HomeTelephone;
        private TextView CellPhone;
        private TextView identityNoStd;
        private TextView Languages;
        private Spinner SpinnerBranch;
        private Spinner SpinnerCourse;
        private Button BtnSave;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_edit_teacher_info);

                defineVariables();
        }
        public void defineVariables(){
                BtnBack = (ImageButton)findViewById( R.id.EditTeacherInfoActivity_btn_back);
                Name = (TextView)findViewById( R.id.EditTeacherInfoActivity_name );
                Surname = (TextView)findViewById( R.id.EditTeacherInfoActivity_surname );
                HomeTelephone = (TextView)findViewById( R.id.EditTeacherInfoActivity_homeTelephone );
                CellPhone = (TextView)findViewById( R.id.EditTeacherInfoActivity_cellPhone );
                identityNoStd = (TextView)findViewById( R.id.identityNo_std );
                Languages = (TextView)findViewById( R.id.EditTeacherInfoActivity_languages );
                SpinnerBranch = (Spinner)findViewById( R.id.EditTeacherInfoActivity_spinner_branch );
                SpinnerCourse = (Spinner)findViewById( R.id.EditTeacherInfoActivity_spinner_course );
                BtnSave = (Button)findViewById( R.id.EditTeacherInfoActivity_btn_save );

                BtnBack.setOnClickListener(this);
                BtnSave.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                if (v == BtnBack) {
                        // Handle clicks for TeacherTimeTableActivityBtnBack
                } else if (v == BtnSave) {
                        // Handle clicks for BtnSave
                }
        }
}


