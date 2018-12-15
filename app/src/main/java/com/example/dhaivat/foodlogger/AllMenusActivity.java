package com.example.dhaivat.foodlogger;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.helpers.AppHelper;
import com.example.dhaivat.foodlogger.models.PrefrencePrefUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static android.support.v4.content.FileProvider.getUriForFile;

public class AllMenusActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout btnAddMeals,btnViewLog,btnEditViewLog,btnShare,btnLogout,btnAddCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        AppHelper.verifyStoragePermissions(AllMenusActivity.this);

        setToolbar();
        initComponent();
    }

    private void initComponent() {

        btnAddMeals = (LinearLayout)findViewById(R.id.btnAddMeals);
        btnViewLog = (LinearLayout)findViewById(R.id.btnViewLog);
        btnEditViewLog = (LinearLayout)findViewById(R.id.btnEditViewLog);
        btnShare = (LinearLayout)findViewById(R.id.btnShare);
        btnLogout = (LinearLayout)findViewById(R.id.btnLogout);
        btnAddCategory= (LinearLayout)findViewById(R.id.btnAddCategory);

        btnAddMeals.setOnClickListener(this);
        btnViewLog.setOnClickListener(this);
        btnEditViewLog.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnAddCategory.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnAddMeals :

                Intent it = new Intent(AllMenusActivity.this, AddMealsActivity.class);
                startActivity(it);

                break;

            case R.id.btnAddCategory :
                Intent i5 = new Intent(AllMenusActivity.this, AddcategoryActivity.class);
                startActivity(i5);
                break;

            case R.id.btnViewLog :
                Intent it1 = new Intent(AllMenusActivity.this, AllMealsActivity.class);
                startActivity(it1);

                break;

            case R.id.btnEditViewLog :
                Intent it2 = new Intent(AllMenusActivity.this, DeleteMealsActivity.class);
                startActivity(it2);
                break;

            case R.id.btnShare :
                Intent it41 = new Intent(AllMenusActivity.this, ShareActivity.class);
                startActivity(it41);


                break;

            case R.id.btnLogout :

                PrefrencePrefUtils.setLLoginId("",AllMenusActivity.this);
                Intent it4 = new Intent(AllMenusActivity.this, LoginActivity.class);
                it4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                it4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                it4.putExtra("EXIT", true);
                startActivity(it4);
                finish();
                break;
        }
    }


    private void setToolbar(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("Dashboard");

        ImageView ivUser = (ImageView)toolbar.findViewById(R.id.ivUser);
        ivUser.setVisibility(View.VISIBLE);

        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(AllMenusActivity.this, UpdateProfileActivity.class);
                startActivity(it);

            }
        });


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




    private void exportDB() {

        MyDatabase dbhelper = new MyDatabase(AllMenusActivity.this);
        File exportDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "FOODBLOGER" + "/", "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "foodbloger.csv");
        try
        {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM meals",null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
//Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1),
                        curCSV.getString(2), curCSV.getString(3), curCSV.getString(4)
                        , curCSV.getString(5), curCSV.getString(6), curCSV.getString(7)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx)
        {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
    }



    private void ReferFriend(){

        Intent shareIntent =
                new Intent(Intent.ACTION_SEND);

        //set the type
        shareIntent.setType("application/csv");

        //add a subject
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                "FOOD BLOGER ");


        //build the body of the message to be shared
        String shareMessage = "Food bloger doc file will send u after some time";


        String csv = (Environment.getExternalStorageDirectory() +  "/" + "FOODBLOGER" + "/" + "foodbloger.csv"); // Here csv file name is MyCsvFile.csv

        //add the message
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                shareMessage);

        File file = new File(csv);
        Uri uri = Uri.fromFile(file);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

        //start the chooser for sharing
        startActivity(Intent.createChooser(shareIntent,
                "Food Bloger"));
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                AllMenusActivity.this);
// Setting Dialog Message
        alertDialog2.setMessage("Are you sure you want to exit ?");
// Setting Positive "Yes" Btn
        alertDialog2.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);

                    }
                });
// Setting Negative "NO" Btn
        alertDialog2.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });
// Showing Alert Dialog
        alertDialog2.show();
    }








}
