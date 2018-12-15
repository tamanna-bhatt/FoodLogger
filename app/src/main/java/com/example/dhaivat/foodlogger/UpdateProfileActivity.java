package com.example.dhaivat.foodlogger;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.models.LoginSignUp;
import com.example.dhaivat.foodlogger.models.PrefrencePrefUtils;


/**
 * Created by jaydeep on 13/7/17.
 */

public class UpdateProfileActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    private EditText etMobile,etEmail,etName,etPassword,etRetype,etDob,etHeight,etWeight;
    private RadioButton rbMale,rbFemale;
    private String gender = "1";


    private TextView signUp;


    private MyDatabase myDatabase;
    private SQLiteDatabase sqLiteDatabase;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initComponent();
        setToolbar();
        myDatabase = new MyDatabase(UpdateProfileActivity.this);
        sqLiteDatabase = myDatabase.getWritableDatabase();

        id =  PrefrencePrefUtils.getLoginId(UpdateProfileActivity.this);

        LoginSignUp loginSignUp = myDatabase.getLoginData(id);

        rbMale.setChecked(true);


        etName.setText(loginSignUp.getName());
        etEmail.setText(loginSignUp.getEmail());
        etPassword.setText(loginSignUp.getPassword());
        etMobile.setText(loginSignUp.getPhoneNo());
        etHeight.setText(loginSignUp.getHeight());
        etWeight.setText(loginSignUp.getWeight());
        etDob.setText(loginSignUp.getDob());

        if (loginSignUp.getGender() != null){
            if (loginSignUp.getGender().equalsIgnoreCase("1")){

                rbMale.setChecked(true);
            }else {
                rbFemale.setChecked(false);
            }
        }


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etName.getText().toString().equals("") || etEmail.getText().toString().equals("")
                        || etMobile.getText().toString().equals("") || etPassword.getText().toString().equals("")) {

                    Toast.makeText(UpdateProfileActivity.this, "Fil the data", Toast.LENGTH_SHORT).show();
                }else {
                        LoginSignUp loginSignUp  = new LoginSignUp(etName.getText().toString(),
                                etEmail.getText().toString(), etMobile.getText().toString()
                        , etPassword.getText().toString(), etRetype.getText().toString());

                    loginSignUp.setId(Integer.parseInt(id));
                    loginSignUp.setDob(etDob.getText().toString());
                    loginSignUp.setHeight(etHeight.getText().toString());
                    loginSignUp.setWeight(etWeight.getText().toString());
                    loginSignUp.setGender(gender);

                        myDatabase.updateLoginData(loginSignUp);
                        PrefrencePrefUtils.setLLoginId(loginSignUp.getId() + "", UpdateProfileActivity.this);
                        PrefrencePrefUtils.setName(loginSignUp.getName() + "", UpdateProfileActivity.this);

                        Intent it = new Intent(UpdateProfileActivity.this, AllMenusActivity.class);
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        it.putExtra("EXIT", true);
                        startActivity(it);
                        finish();



//                    Intent it = new Intent(SignupActivity.this, NavigationActivity.class);
//                    startActivity(it);

//                    sendSignUpData(etName.getText().toString(),etEmail.getText().toString(),etMobile.getText().toString(),
//                            etPassword.getText().toString());
                }





            }
        });






    }

    private void initComponent() {

        etMobile = (EditText)findViewById(R.id.etMobile);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etName = (EditText)findViewById(R.id.etName);
        etPassword = (EditText)findViewById(R.id.etPassword);
        signUp=  (TextView)findViewById(R.id.signUp);
        etRetype = (EditText)findViewById(R.id.etRetype);
        etDob = (EditText)findViewById(R.id.etDob);
        etHeight = (EditText)findViewById(R.id.etHeight);
        etWeight = (EditText)findViewById(R.id.etWeight);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton)findViewById(R.id.rbFemale);

        rbMale.setOnCheckedChangeListener(this);
        rbFemale.setOnCheckedChangeListener(this);


    }


    private void setToolbar(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("Update Profile");



        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow_6);

        actionBar.setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (b) {
            if (compoundButton.getId() == R.id.rbMale) {
                rbFemale.setChecked(false);
                gender = "1";

            }

            if (compoundButton.getId() == R.id.rbFemale) {
                rbMale.setChecked(false);
                gender = "2";

            }
        }


    }
}
