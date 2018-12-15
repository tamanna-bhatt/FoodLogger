package com.example.dhaivat.foodlogger;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.models.LoginSignUp;
import com.example.dhaivat.foodlogger.models.PrefrencePrefUtils;


public class LoginActivity extends AppCompatActivity {

    private TextView login,tvSignUp,tvForgotPassword;
    private EditText etEmail,etPassword;
    private MyDatabase myDatabase;
    private SQLiteDatabase sqLiteDatabase;

    private Context context = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDatabase = new MyDatabase(LoginActivity.this);
        sqLiteDatabase = myDatabase.getWritableDatabase();
        login=  (TextView)findViewById(R.id.login);
        tvSignUp=  (TextView)findViewById(R.id.tvSignUp);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword= (EditText)findViewById(R.id.etPassword);
        tvForgotPassword= (TextView) findViewById(R.id.tvForgotPassword);


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(LoginActivity.this, SignupActivity.class);
              //  it.putExtra("mobile", etMobile.getText().toString());
                startActivity(it);
                finish();

            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(it);
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (etEmail.getText().toString().equalsIgnoreCase("") || etPassword.getText().toString().equalsIgnoreCase("")){

                    Toast.makeText(LoginActivity.this, "Pleasefill the details", Toast.LENGTH_SHORT).show();
                }else {
                    LoginSignUp loginSignUp =  myDatabase.getLoginId(etEmail.getText().toString(), etPassword.getText().toString());

                    if (loginSignUp.getId() != -1){

                        PrefrencePrefUtils.setLLoginId(loginSignUp.getId() + "", LoginActivity.this);
                        PrefrencePrefUtils.setName(loginSignUp.getName() + "", LoginActivity.this);

                        Intent it = new Intent(LoginActivity.this, AllMenusActivity.class);
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        it.putExtra("EXIT", true);
                        startActivity(it);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "Incorrect email or password! Please try again!", Toast.LENGTH_SHORT).show();

                    }

                }




            }
        });
    }


}
