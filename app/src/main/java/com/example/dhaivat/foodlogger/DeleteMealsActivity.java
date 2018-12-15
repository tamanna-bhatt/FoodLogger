package com.example.dhaivat.foodlogger;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dhaivat.foodlogger.adapters.AllMealsForDeleteAdapter;
import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.models.Meals;

import java.util.ArrayList;

/**
 * Created by jaydeep on 11/9/18.
 */

public class DeleteMealsActivity extends AppCompatActivity {


    private MyDatabase myDatabase;
    private SQLiteDatabase sqLiteDatabase;

    private RecyclerView rvAllMeals;

    private ArrayList<Meals> mealsArrayList = new ArrayList<>();
    private AllMealsForDeleteAdapter allMenusAdapter;
    String currntDate = "", currentType = "";

    private boolean isCallPage = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_meals);
        initComponent();
        setToolbar();



       setData();

        allMenusAdapter = new AllMealsForDeleteAdapter(DeleteMealsActivity.this, mealsArrayList);
        rvAllMeals.setAdapter(allMenusAdapter);


    }

    private void setData() {
        myDatabase = new MyDatabase(DeleteMealsActivity.this);
        sqLiteDatabase = myDatabase.getWritableDatabase();

        mealsArrayList = myDatabase.getAllMeals();

        for (int i=0; i<mealsArrayList.size() ; i++){

            if (!currntDate.equalsIgnoreCase(mealsArrayList.get(i).getDate1())  ||
                    !currentType.equalsIgnoreCase(mealsArrayList.get(i).getMealCategory())){

                currntDate = mealsArrayList.get(i).getDate1();
                currentType = mealsArrayList.get(i).getMealCategory();

                mealsArrayList.get(i).setShowDate(true);
            }else {

                mealsArrayList.get(i).setShowDate(false);
            }
        }


    }

    private void initComponent() {


        rvAllMeals = (RecyclerView) findViewById(R.id.rvAllMeals);
        final LinearLayoutManager mLayoutManagerHappyHours1 = new LinearLayoutManager(DeleteMealsActivity.this,
                LinearLayoutManager.VERTICAL, false);
        rvAllMeals.setLayoutManager(mLayoutManagerHappyHours1);
        rvAllMeals.setNestedScrollingEnabled(false);
        rvAllMeals.setItemAnimator(new DefaultItemAnimator());
        rvAllMeals.setHasFixedSize(true);

    }


    private void setToolbar(){

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("All Meals");

        ImageView ivUser = (ImageView)toolbar.findViewById(R.id.ivUser);
        ivUser.setVisibility(View.VISIBLE);

        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(DeleteMealsActivity.this, UpdateProfileActivity.class);
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

                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();


        if (isCallPage == true){

            setData();
            allMenusAdapter = new AllMealsForDeleteAdapter(DeleteMealsActivity.this, mealsArrayList);
            rvAllMeals.setAdapter(allMenusAdapter);

        }else {
            isCallPage = true;
        }
    }
}
