package com.example.dhaivat.foodlogger;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.models.LoginSignUp;
import com.example.dhaivat.foodlogger.models.PrefrencePrefUtils;


/**
 * Created by jaydeep on 13/7/17.
 */

public class SignupActivity extends AppCompatActivity {


    private EditText etMobile,etEmail,etName,etPassword,etRetype;

    private TextView signUp;
    private ImageView ivBack;

    private MyDatabase myDatabase;
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        myDatabase = new MyDatabase(SignupActivity.this);
        sqLiteDatabase = myDatabase.getWritableDatabase();

        initComponent();



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etName.getText().toString().equals("") || etEmail.getText().toString().equals("")
                        || etMobile.getText().toString().equals("") || etPassword.getText().toString().equals("")) {

                    Toast.makeText(SignupActivity.this, "Fil the data", Toast.LENGTH_SHORT).show();
                }else {

                    if (myDatabase.isEmailAvailable(etEmail.getText().toString())){

                        Toast.makeText(SignupActivity.this, "Email id already available", Toast.LENGTH_SHORT).show();
                    }else {
                        LoginSignUp loginSignUp  = new LoginSignUp(etName.getText().toString(),
                                etEmail.getText().toString(), etMobile.getText().toString()
                        , etPassword.getText().toString(), etRetype.getText().toString());

                        myDatabase.insertLoginData(loginSignUp);

                        LoginSignUp loginSignUp1 =  myDatabase.getLoginIdForSignuUp(etEmail.getText().toString(), etPassword.getText().toString());


                        PrefrencePrefUtils.setLLoginId(loginSignUp1.getId() + "", SignupActivity.this);
                        PrefrencePrefUtils.setName(loginSignUp.getName() + "", SignupActivity.this);

                        Intent it = new Intent(SignupActivity.this, AllMenusActivity.class);
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        it.putExtra("EXIT", true);
                        startActivity(it);
                        finish();
                    }


//                    Intent it = new Intent(SignupActivity.this, NavigationActivity.class);
//                    startActivity(it);

//                    sendSignUpData(etName.getText().toString(),etEmail.getText().toString(),etMobile.getText().toString(),
//                            etPassword.getText().toString());
                }





            }
        });




        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
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
        ivBack= (ImageView) findViewById(R.id.ivBack);

    }





}
