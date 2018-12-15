package com.example.dhaivat.foodlogger;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.models.Categories;

/**
 * Created by jaydeep on 11/9/18.
 */

public class UpdatecategoryActivity extends AppCompatActivity {


    private AutoCompleteTextView etcategoryName;
    private TextView add;

    private MyDatabase myDatabase;
    private SQLiteDatabase sqLiteDatabase;

  Categories categories;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initComponent();
        setToolbar();

        id = getIntent().getStringExtra("id");

        add.setText("UPDATE");
        myDatabase = new MyDatabase(UpdatecategoryActivity.this);
        sqLiteDatabase = myDatabase.getWritableDatabase();
        categories = myDatabase.getCategory(id);

        etcategoryName.setText(categories.getCatName());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etcategoryName.getText().toString().equalsIgnoreCase("") ){

                    Toast.makeText(UpdatecategoryActivity.this, "Please enter category name", Toast.LENGTH_SHORT).show();
                }else {

                    Categories categories = new Categories(etcategoryName.getText().toString());
                    categories.setId(id);

                    myDatabase.updateCategory(categories);
                    etcategoryName.setText("");

               finish();

                }
            }
        });


    }

    private void initComponent() {


        etcategoryName = (AutoCompleteTextView) findViewById(R.id.etcategoryName);
        add = (TextView) findViewById(R.id.add);

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
