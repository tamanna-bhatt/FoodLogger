package com.example.dhaivat.foodlogger;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by one on 13/4/18.
 */
public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail,etNewPass,etConfirmPass, etCode;
    private LinearLayout llPassword;
    private TextView dialogSubmit;
    private Toolbar toolbar;


    private boolean isEmailSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initComponent();
        setToolbar();

        dialogSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEmailSent == false){
                    if (etEmail.getText().toString().equals("")){
                        Toast.makeText(ForgotPasswordActivity.this, "Enter email id", Toast.LENGTH_SHORT).show();
                    }else {
//                        sendEmail();
                    }
                }else {

                    if (etNewPass.getText().toString().equals("") || etConfirmPass.getText().toString().equals("")
                            ||etCode.getText().toString().equals("") ){
                        Toast.makeText(ForgotPasswordActivity.this, "Fill all details", Toast.LENGTH_SHORT).show();
                    }else if (!(etNewPass.getText().toString()).equals(etConfirmPass.getText().toString())){
                        Toast.makeText(ForgotPasswordActivity.this, "Password not matched", Toast.LENGTH_SHORT).show();
                    }else{

//                        sendForReset();
                    }




                }




            }
        });

    }

    private void initComponent() {

        etEmail = (EditText)findViewById(R.id.etEmail);
        etNewPass = (EditText)findViewById(R.id.etNewPass);
        etConfirmPass = (EditText)findViewById(R.id.etConfirmPass);
        etCode = (EditText)findViewById(R.id.etCode);
        llPassword = (LinearLayout)findViewById(R.id.llPassword);
        dialogSubmit= (TextView) findViewById(R.id.dialogSubmit);


    }

    private void setToolbar(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("Forgot Password");
//        tvTitleName.setTypeface(custom_medium);


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow_6);

        actionBar.setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });



    }


}
