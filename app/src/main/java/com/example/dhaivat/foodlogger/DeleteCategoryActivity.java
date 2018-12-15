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

import com.example.dhaivat.foodlogger.adapters.AllCategoriesForDeleteAdapter;
import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.models.Categories;

import java.util.ArrayList;

/**
 * Created by jaydeep on 11/9/18.
 */

public class DeleteCategoryActivity extends AppCompatActivity {


    private MyDatabase myDatabase;
    private SQLiteDatabase sqLiteDatabase;

    private RecyclerView rvAllMeals;

    private ArrayList<Categories> categoriesArrayList = new ArrayList<>();
    private AllCategoriesForDeleteAdapter allCategoriesForDeleteAdapter;
    String currntDate = "", currentType = "";

    private boolean isCallPage = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_meals);
        initComponent();
        setToolbar();

       setData();

        allCategoriesForDeleteAdapter = new AllCategoriesForDeleteAdapter(DeleteCategoryActivity.this, categoriesArrayList);
        rvAllMeals.setAdapter(allCategoriesForDeleteAdapter);


    }

    private void setData() {
        myDatabase = new MyDatabase(DeleteCategoryActivity.this);
        sqLiteDatabase = myDatabase.getWritableDatabase();

        categoriesArrayList = myDatabase.getAllcategories();

    }

    private void initComponent() {


        rvAllMeals = (RecyclerView) findViewById(R.id.rvAllMeals);
        final LinearLayoutManager mLayoutManagerHappyHours1 = new LinearLayoutManager(DeleteCategoryActivity.this,
                LinearLayoutManager.VERTICAL, false);
        rvAllMeals.setLayoutManager(mLayoutManagerHappyHours1);
        rvAllMeals.setNestedScrollingEnabled(false);
        rvAllMeals.setItemAnimator(new DefaultItemAnimator());
        rvAllMeals.setHasFixedSize(true);

    }


    private void setToolbar(){

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("All Food Type");

        ImageView ivUser = (ImageView)toolbar.findViewById(R.id.ivUser);
        ivUser.setVisibility(View.VISIBLE);

        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(DeleteCategoryActivity.this, UpdateProfileActivity.class);
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
            allCategoriesForDeleteAdapter = new AllCategoriesForDeleteAdapter(DeleteCategoryActivity.this, categoriesArrayList);
            rvAllMeals.setAdapter(allCategoriesForDeleteAdapter);

        }else {
            isCallPage = true;
        }
    }
}
