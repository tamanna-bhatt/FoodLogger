package com.example.dhaivat.foodlogger.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import com.example.dhaivat.foodlogger.models.Categories;
import com.example.dhaivat.foodlogger.models.LoginSignUp;
import com.example.dhaivat.foodlogger.models.Meals;

import java.util.ArrayList;

/**
 * Created by jaydeep.rana on 10-09-2018.
 */

public class MyDatabase extends SQLiteOpenHelper {

    private Context context;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;
    }


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "foodbloger.db";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(LoginSignUp.CREATE_TABLE_LOGIN);
        sqLiteDatabase.execSQL(Categories.CREATE_TABLE_CATEGORY);
        sqLiteDatabase.execSQL(Meals.CREATE_TABLE_MEALS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LoginSignUp.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Categories.TABLE_NAME_CATEGORY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Meals.TABLE_NAME_MEAL);

        // Create tables again
        onCreate(sqLiteDatabase);


    }


    public long insertLoginData(LoginSignUp loginSignUp) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(LoginSignUp.COLUMN_NAME, loginSignUp.getName());
        values.put(LoginSignUp.COLUMN_EMAIL, loginSignUp.getEmail());
        values.put(LoginSignUp.COLUMN_PASSWORD, loginSignUp.getPassword());
        values.put(LoginSignUp.COLUMN_RETYPE, loginSignUp.getRetype());
        values.put(LoginSignUp.COLUMN_PHONE_NUMBER, loginSignUp.getPhoneNo());
        values.put(LoginSignUp.COLUMN_GENDER, loginSignUp.getGender());
        values.put(LoginSignUp.COLUMN_HEIGHT, loginSignUp.getHeight());
        values.put(LoginSignUp.COLUMN_WEIGHT, loginSignUp.getWeight());
        values.put(LoginSignUp.COLUMN_DOB, loginSignUp.getDob());
        Toast.makeText(context, "Successfully login", Toast.LENGTH_SHORT).show();

        // insert row
        long id = db.insert(LoginSignUp.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public long updateLoginData(LoginSignUp loginSignUp) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(LoginSignUp.COLUMN_NAME, loginSignUp.getName());
        values.put(LoginSignUp.COLUMN_EMAIL, loginSignUp.getEmail());
        values.put(LoginSignUp.COLUMN_PASSWORD, loginSignUp.getPassword());
        values.put(LoginSignUp.COLUMN_RETYPE, loginSignUp.getRetype());
        values.put(LoginSignUp.COLUMN_PHONE_NUMBER, loginSignUp.getPhoneNo());
        values.put(LoginSignUp.COLUMN_GENDER, loginSignUp.getGender());
        values.put(LoginSignUp.COLUMN_HEIGHT, loginSignUp.getHeight());
        values.put(LoginSignUp.COLUMN_WEIGHT, loginSignUp.getWeight());
        values.put(LoginSignUp.COLUMN_DOB, loginSignUp.getDob());


        // insert row


        Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();

        long id = db.update(LoginSignUp.TABLE_NAME, values, LoginSignUp.COLUMN_ID + " = ?",
                new String[]{String.valueOf(loginSignUp.getId())});

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public void deleteLogin(LoginSignUp loginSignUp) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(loginSignUp.TABLE_NAME, loginSignUp.COLUMN_ID + " = ?",
                new String[]{String.valueOf(loginSignUp.getId())});
        db.close();
    }



    public void insertMeals(Meals meals) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Meals.COLUMN_MEAL_NAME, meals.getMealName());
        values.put(Meals.COLUMN_MEAL_CATEGORY, meals.getMealCategory());
        values.put(Meals.COLUMN_CARBOHYDRATE, meals.getCarbohydrate());
        values.put(Meals.COLUMN_PROTIEN, meals.getProtien());
        values.put(Meals.COLUMN_CALORIES, meals.getCalories());
        values.put(Meals.COLUMN_FAT, meals.getFat());
        values.put(Meals.COLUMN_DATE, meals.getDate1());
        values.put(Meals.COLUMN_TIME, meals.getTime1());



        // insert row
         db.insert(Meals.TABLE_NAME_MEAL, null, values);

        Toast.makeText(context, "Successfully inserted meal", Toast.LENGTH_SHORT).show();

        // close db connection
        db.close();

        // return newly inserted row id

    }


    public void updateMeals(Meals meals) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Meals.COLUMN_MEAL_NAME, meals.getMealName());
        values.put(Meals.COLUMN_MEAL_CATEGORY, meals.getMealCategory());
        values.put(Meals.COLUMN_CARBOHYDRATE, meals.getCarbohydrate());
        values.put(Meals.COLUMN_PROTIEN, meals.getProtien());
        values.put(Meals.COLUMN_CALORIES, meals.getCalories());
        values.put(Meals.COLUMN_FAT, meals.getFat());
        values.put(Meals.COLUMN_DATE, meals.getDate1());
        values.put(Meals.COLUMN_TIME, meals.getTime1());



        // insert row
      db.update(Meals.TABLE_NAME_MEAL, values, Meals.COLUMN_ID + " = ?",
                new String[]{String.valueOf(meals.getId())});
        Toast.makeText(context, "Successfully updated meal", Toast.LENGTH_SHORT).show();
        // close db connection
        db.close();

        // return newly inserted row id

    }


    public void deleteMeals(Meals meals) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(meals.TABLE_NAME_MEAL, meals.COLUMN_ID + " = ?",
                new String[]{String.valueOf(meals.getId())});
        db.close();
    }

    public void deletecategory(Categories categories) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(categories.TABLE_NAME_CATEGORY, categories.COLUMN_ID + " = ?",
                new String[]{String.valueOf(categories.getId())});
        db.close();
    }



    public void insertCategory(Categories categories) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Categories.COLUMN_CAT_NAME, categories.getCatName());

        // insert row
        db.insert(Categories.TABLE_NAME_CATEGORY, null, values);

        Toast.makeText(context, "Successfully inserted category", Toast.LENGTH_SHORT).show();

        // close db connection
        db.close();

        // return newly inserted row id

    }


    public void updateCategory(Categories categories) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Categories.COLUMN_CAT_NAME, categories.getCatName());

        // insert row
        db.update(Categories.TABLE_NAME_CATEGORY, values, Categories.COLUMN_ID + " = ?",
                new String[]{categories.getId()});

        Toast.makeText(context, "Successfully updated category", Toast.LENGTH_SHORT).show();
        // close db connection
        db.close();

        // return newly inserted row id

    }


    public LoginSignUp getLoginIdForSignuUp(String email,String password) {

        LoginSignUp loginSignUp = new LoginSignUp();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + LoginSignUp.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                if (cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_EMAIL)).equalsIgnoreCase(email)){

                    if (cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_PASSWORD)).equalsIgnoreCase(password)){
                        loginSignUp.setId(cursor.getInt(cursor.getColumnIndex(LoginSignUp.COLUMN_ID)));
                        loginSignUp.setName(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_NAME)));
                        loginSignUp.setEmail(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_EMAIL)));
                        loginSignUp.setDob(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_DOB)));
                        loginSignUp.setGender(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_GENDER)));
                        loginSignUp.setHeight(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_HEIGHT)));
                        loginSignUp.setWeight(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_WEIGHT)));
                        loginSignUp.setPassword(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_PASSWORD)));
                        loginSignUp.setPhoneNo(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_PHONE_NUMBER)));
                        loginSignUp.setRetype(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_RETYPE)));


                    }else {


                    }

                }

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return loginSignUp;
    }


    public boolean isEmailAvailable(String email) {

        boolean isAvailable = false;

        // Select All Query
        String selectQuery = "SELECT  * FROM " + LoginSignUp.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                if (cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_EMAIL)).equalsIgnoreCase(email)){
                    isAvailable = true;
                }

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return isAvailable;
    }

    public LoginSignUp getLoginId(String email,String password) {

        LoginSignUp loginSignUp = new LoginSignUp();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + LoginSignUp.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                if (cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_EMAIL)).equalsIgnoreCase(email)){

                    if (cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_PASSWORD)).equalsIgnoreCase(password)){
                        loginSignUp.setId(cursor.getInt(cursor.getColumnIndex(LoginSignUp.COLUMN_ID)));
                        loginSignUp.setName(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_NAME)));
                        loginSignUp.setEmail(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_EMAIL)));
                        loginSignUp.setDob(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_DOB)));
                        loginSignUp.setGender(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_GENDER)));
                        loginSignUp.setHeight(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_HEIGHT)));
                        loginSignUp.setWeight(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_WEIGHT)));
                        loginSignUp.setPassword(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_PASSWORD)));
                        loginSignUp.setPhoneNo(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_PHONE_NUMBER)));
                        loginSignUp.setRetype(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_RETYPE)));

                        Toast.makeText(context, "Successfully login", Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(context, "Password not match", Toast.LENGTH_SHORT).show();
                    }

                }

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return loginSignUp;
    }

    public LoginSignUp getLoginData(String id) {

        LoginSignUp loginSignUp = new LoginSignUp();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + LoginSignUp.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                if (cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_ID)).equalsIgnoreCase(id)){

                        loginSignUp.setId(cursor.getInt(cursor.getColumnIndex(LoginSignUp.COLUMN_ID)));
                        loginSignUp.setName(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_NAME)));
                        loginSignUp.setEmail(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_EMAIL)));
                        loginSignUp.setDob(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_DOB)));
                        loginSignUp.setGender(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_GENDER)));
                        loginSignUp.setHeight(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_HEIGHT)));
                        loginSignUp.setWeight(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_WEIGHT)));
                        loginSignUp.setPassword(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_PASSWORD)));
                        loginSignUp.setPhoneNo(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_PHONE_NUMBER)));
                        loginSignUp.setRetype(cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_RETYPE)));

                        Toast.makeText(context, "Successfully login", Toast.LENGTH_SHORT).show();


                }

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return loginSignUp;
    }

    public Categories getCategory(String id) {

        Categories categories = new Categories();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Categories.TABLE_NAME_CATEGORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                if (cursor.getString(cursor.getColumnIndex(LoginSignUp.COLUMN_ID)).equalsIgnoreCase(id)){

                    categories.setId(cursor.getString(cursor.getColumnIndex(categories.COLUMN_ID)));
                    categories.setCatName(cursor.getString(cursor.getColumnIndex(categories.COLUMN_CAT_NAME)));


                }

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return categories;
    }



    public ArrayList<Categories> getAllcategories() {

        ArrayList<Categories> categoriesArrayList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Categories.TABLE_NAME_CATEGORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Categories categories = new Categories();
                categories.setId(cursor.getString(cursor.getColumnIndex(Categories.COLUMN_ID)));
                categories.setCatName(cursor.getString(cursor.getColumnIndex(Categories.COLUMN_CAT_NAME)));

                categoriesArrayList.add(categories);

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return categoriesArrayList;
    }


    public ArrayList<Meals> getAllMeals() {

        ArrayList<Meals> mealsArrayList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Meals.TABLE_NAME_MEAL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Meals meals = new Meals();
                meals.setId(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_ID)));
                meals.setMealName(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_MEAL_NAME)));
                meals.setCalories(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_CALORIES)));
                meals.setCarbohydrate(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_CARBOHYDRATE)));
                meals.setProtien(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_PROTIEN)));
                meals.setFat(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_FAT)));
                meals.setDate1(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_DATE)));
                meals.setTime1(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_TIME)));
                meals.setMealCategory(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_MEAL_CATEGORY)));

                mealsArrayList.add(meals);

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return mealsArrayList;
    }



    public Meals getMealById(String id) {

        Meals meals = new Meals();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Meals.TABLE_NAME_MEAL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(Meals.COLUMN_ID)).equalsIgnoreCase(id)){
                    meals.setId(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_ID)));
                    meals.setMealName(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_MEAL_NAME)));
                    meals.setCalories(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_CALORIES)));
                    meals.setCarbohydrate(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_CARBOHYDRATE)));
                    meals.setProtien(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_PROTIEN)));
                    meals.setFat(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_FAT)));
                    meals.setDate1(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_DATE)));
                    meals.setTime1(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_TIME)));
                    meals.setMealCategory(cursor.getString(cursor.getColumnIndex(Meals.COLUMN_MEAL_CATEGORY)));
                }



            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return meals;
    }


}
