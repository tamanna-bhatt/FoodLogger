package com.example.dhaivat.foodlogger;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.dhaivat.foodlogger.models.PrefrencePrefUtils;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(3 * 1000);

                    Log.e("id111",PrefrencePrefUtils.getLoginId(SplashActivity.this));

                    if (!PrefrencePrefUtils.getLoginId(SplashActivity.this).equalsIgnoreCase("")){
                        Intent it = new Intent(SplashActivity.this, AllMenusActivity.class);
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        it.putExtra("EXIT", true);
                        startActivity(it);
                        finish();
                    }else {
                        Intent it = new Intent(SplashActivity.this, LoginActivity.class);
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        it.putExtra("EXIT", true);
                        startActivity(it);
                        finish();
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        // start thread
        background.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
        finish();

    }


}
