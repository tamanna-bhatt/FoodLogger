package com.example.dhaivat.foodlogger;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.models.Categories;
import com.example.dhaivat.foodlogger.models.Meals;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by jaydeep on 11/9/18.
 */

public class UpdateMealsActivity extends AppCompatActivity {

    private Spinner spCategory;
    private EditText etcarbohydrate,etProtien,etFat,etcalories;
    private AutoCompleteTextView etMealName;
    private TextView add,show;

    private MyDatabase myDatabase;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<Categories> categoriesArrayList = new ArrayList<>();
    private ArrayList<Meals> mealsArrayList = new ArrayList<>();
    private ArrayAdapter<String> categoryStringArrayAdapter ;
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private ArrayList<String> stringMealsArrayList = new ArrayList<>();
    private Calendar c;
    int mYear;
    int mMonth;
    int mDay;
    int mHour;
    int mMin;
    String current_date;
    String current_time, selectedcategory;

    private String id;
    private Meals myMeal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        initComponent();
        setToolbar();

        id = getIntent().getStringExtra("id");

        myDatabase = new MyDatabase(UpdateMealsActivity.this);
        sqLiteDatabase = myDatabase.getWritableDatabase();
        categoriesArrayList = myDatabase.getAllcategories();
        myMeal = myDatabase.getMealById(id);

        etMealName.setText(myMeal.getMealName());
        etcalories.setText(myMeal.getCalories());
        etcarbohydrate.setText(myMeal.getCarbohydrate());
        etProtien.setText(myMeal.getProtien());
        etFat.setText(myMeal.getFat());

        for (int i=0; i<categoriesArrayList.size() ; i++){

            if (categoriesArrayList.get(i).getCatName().equalsIgnoreCase(myMeal.getMealCategory())){
                spCategory.setSelection(i);
            }
        }


        mealsArrayList = myDatabase.getAllMeals();

        for (Categories categories : categoriesArrayList){

            stringArrayList.add(categories.getCatName());

        }

        for (Meals meals : mealsArrayList){

            stringMealsArrayList.add(meals.getMealName());

        }


        categoryStringArrayAdapter = new ArrayAdapter<String>(UpdateMealsActivity.this,
                android.R.layout.simple_list_item_1,stringArrayList);
        spCategory.setAdapter(categoryStringArrayAdapter);

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedcategory = categoriesArrayList.get(i).getCatName();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etMealName.getText().toString().equalsIgnoreCase("") || etcarbohydrate.getText().toString().equalsIgnoreCase("")
                        || etProtien.getText().toString().equalsIgnoreCase("") || etFat.getText().toString().equalsIgnoreCase("")|| etcalories.getText().toString().equalsIgnoreCase("")){

                    Toast.makeText(UpdateMealsActivity.this, "Please fill the details", Toast.LENGTH_SHORT).show();
                }else {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UpdateMealsActivity.this);
                    alertDialogBuilder.setMessage("Are you sure want to update ?");
                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                    Meals meals = new Meals(etMealName.getText().toString(), selectedcategory,etcarbohydrate.getText().toString(),
                                            etProtien.getText().toString(),etFat.getText().toString(),etcalories.getText().toString(),myMeal.getDate1(),myMeal.getTime1());
                                    meals.setId(id);
                                    myDatabase.updateMeals(meals);

                                  finish();

                                }
                            });

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();





                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(UpdateMealsActivity.this, AllMealsActivity.class);
                startActivity(it);

            }
        });


    }

    private void initComponent() {

        spCategory = (Spinner)findViewById(R.id.spCategory);
        etMealName = (AutoCompleteTextView) findViewById(R.id.etMealName);
        etcarbohydrate = (EditText) findViewById(R.id.etcarbohydrate);
        etProtien = (EditText) findViewById(R.id.etProtien);
        etFat = (EditText) findViewById(R.id.etFat);
        etcalories= (EditText) findViewById(R.id.etcalories);
        add = (TextView) findViewById(R.id.add);
        show= (TextView) findViewById(R.id.show);
        add.setText("Update");

    }


    private void setToolbar(){

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("Update Meal");



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


    public void getCurrentDateTime() {

        // Get current date and and time when submit the each answer
        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMin = c.get(Calendar.MINUTE);


        // Converted into 12 Hrs format
        int hour;
        String am_pm;
        if (mHour > 12) {
            hour = mHour - 12;
            am_pm = "PM";
        } else {
            hour = mHour;
            am_pm = "AM";
        }

        current_date = mDay + "-" + mMonth + "-" + mYear;
        current_time = hour + ":" + mMin + " " + am_pm;

        Log.e("currentDate",current_date);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
