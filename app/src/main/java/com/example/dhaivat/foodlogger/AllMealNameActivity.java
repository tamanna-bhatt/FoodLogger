package com.example.dhaivat.foodlogger;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dhaivat.foodlogger.adapters.SearchResultAdapter;
import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.models.Meals;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by jaydeep on 11/9/18.
 */

public class AllMealNameActivity extends AppCompatActivity {


    private MyDatabase myDatabase;
    private SQLiteDatabase sqLiteDatabase;

    private RecyclerView rvAllMeals;

    private ArrayList<Meals> mealsArrayList = new ArrayList<>();
    private ArrayList<Meals> mealsFilterArrayList = new ArrayList<>();
    String currntDate = "", currentType = "";
    private SearchResultAdapter searchResultAdapter;
    private Timer timer = new Timer();
    private final long DELAY = 700; // in ms
    private EditText etSearch;
    private TextView ok;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_meals_name);
        initComponent();
        setToolbar();
        myDatabase = new MyDatabase(AllMealNameActivity.this);
        sqLiteDatabase = myDatabase.getWritableDatabase();

        mealsArrayList = myDatabase.getAllMeals();

        boolean isAvailable = false;

        for (int i=0; i<mealsArrayList.size() ; i++){

            isAvailable = false;

            for (int z=0;z<mealsFilterArrayList.size() ; z++){

                if (mealsArrayList.get(i).getMealName().equalsIgnoreCase(mealsFilterArrayList.get(z).getMealCategory())){
                    isAvailable = true;

                }
            }

            if (isAvailable == false){

                mealsFilterArrayList.add(mealsArrayList.get(i));
            }

        }



        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (timer != null)
                    timer.cancel();

            }

            @Override
            public void afterTextChanged(final Editable editable) {

                try {
                    if (editable.length() >= 2) {

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                // Stuff that updates the UI

                                searchResultAdapter =new SearchResultAdapter(AllMealNameActivity.this,
                                        mealsArrayList,editable.toString());
                                rvAllMeals.setAdapter(searchResultAdapter);

                            }
                        });


//                        timer = new Timer();
//                        timer.schedule(new TimerTask() {
//                            @Override
//                            public void run() {
//                                // TODO: do what you need here (refresh list)
//                                // you will probably need to use
//                                // runOnUiThread(Runnable action) for some specific
//                                // actions
//
//                                searchResultAdapter =new SearchResultAdapter(AllMealNameActivity.this,
//                                        mealsArrayList,editable.toString());
//                                rvAllMeals.setAdapter(searchResultAdapter);
//
//
//                            }
//
//                        }, DELAY);
                    } else {
                        rvAllMeals.setAdapter(null);
                        //  listSearchProducts.setVisibility(View.GONE);

//                        llCancel.setVisibility(View.GONE);

                    }

                } catch (Exception e) {
                }

            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(AllMealNameActivity.this, AddMealsActivity.class);
//                it.putExtra("name",etSearch.getText().toString());
//                it.putExtra("calory",searchResponseArrayList.get(position).getCalories());
//                it.putExtra("protien",searchResponseArrayList.get(position).getProtien());
//                it.putExtra("fat",searchResponseArrayList.get(position).getFat());
//                it.putExtra("carbo",searchResponseArrayList.get(position).getCarbohydrate());
//                context.startActivity(it);

            }
        });



    }

    private void initComponent() {

        ok= (TextView) findViewById(R.id.ok);
        rvAllMeals = (RecyclerView) findViewById(R.id.rvAllMeals);
        final LinearLayoutManager mLayoutManagerHappyHours1 = new LinearLayoutManager(AllMealNameActivity.this,
                LinearLayoutManager.VERTICAL, false);
        rvAllMeals.setLayoutManager(mLayoutManagerHappyHours1);
        rvAllMeals.setNestedScrollingEnabled(false);
        rvAllMeals.setItemAnimator(new DefaultItemAnimator());
        rvAllMeals.setHasFixedSize(true);

    }


    private void setToolbar(){

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


         etSearch = (EditText) toolbar.findViewById(R.id.etSearch);

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
