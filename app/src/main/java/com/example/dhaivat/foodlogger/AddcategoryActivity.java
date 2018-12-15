package com.example.dhaivat.foodlogger;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.models.Categories;

import java.util.ArrayList;

/**
 * Created by jaydeep on 11/9/18.
 */

public class AddcategoryActivity extends AppCompatActivity {


    private AutoCompleteTextView etcategoryName;
    private TextView add,show;

    private MyDatabase myDatabase;
    private SQLiteDatabase sqLiteDatabase;

    private ArrayList<Categories> categoriesArrayList =new ArrayList<>();
    private ArrayList<String> categoriStringArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initComponent();
        setToolbar();
        myDatabase = new MyDatabase(AddcategoryActivity.this);
        sqLiteDatabase = myDatabase.getWritableDatabase();
        categoriesArrayList = myDatabase.getAllcategories();

        for (Categories categories : categoriesArrayList){

            categoriStringArrayList.add(categories.getCatName());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, categoriStringArrayList);

        etcategoryName.setAdapter(adapter);
        etcategoryName.setThreshold(1);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etcategoryName.getText().toString().equalsIgnoreCase("") ){

                    Toast.makeText(AddcategoryActivity.this, "Please enter category name", Toast.LENGTH_SHORT).show();
                }else {

                    Categories categories = new Categories(etcategoryName.getText().toString());

                    myDatabase.insertCategory(categories);
                    etcategoryName.setText("");


                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(AddcategoryActivity.this, DeleteCategoryActivity.class);
                startActivity(it);

            }
        });


    }

    private void initComponent() {

        etcategoryName = (AutoCompleteTextView) findViewById(R.id.etcategoryName);
        add = (TextView) findViewById(R.id.add);
        show= (TextView) findViewById(R.id.show);
        show.setVisibility(View.VISIBLE);
    }


    private void setToolbar(){

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("Add Category");



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
