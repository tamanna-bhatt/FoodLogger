package com.example.dhaivat.foodlogger.models;

/**
 * Created by jaydeep on 10/9/18.
 */

public class Meals {

    public static final String TABLE_NAME_MEAL = "meals";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MEAL_NAME = "meal_name";
    public static final String COLUMN_MEAL_CATEGORY = "category";
    public static final String COLUMN_CARBOHYDRATE = "carbohydrate";
    public static final String COLUMN_FAT= "fat";
    public static final String COLUMN_PROTIEN = "protien";
    public static final String COLUMN_CALORIES = "calories";
    public static final String COLUMN_DATE = "date1";
    public static final String COLUMN_TIME = "time1";



    private String id,mealName,mealCategory,carbohydrate,fat,protien,calories,date1,time1;
    private boolean isShowDate = false;



    // Create table SQL query
    public static final String CREATE_TABLE_MEALS =
            "CREATE TABLE " + TABLE_NAME_MEAL + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_MEAL_NAME + " TEXT,"
                    + COLUMN_MEAL_CATEGORY + " TEXT,"
                    + COLUMN_CARBOHYDRATE + " TEXT,"
                    + COLUMN_FAT + " TEXT,"
                    + COLUMN_PROTIEN + " TEXT,"
                    + COLUMN_CALORIES + " TEXT,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_TIME + " TEXT"
                    + ")";

    public Meals(String mealName, String mealCategory, String carbohydrate,
                 String fat, String protien, String calories, String date1, String time1) {
        this.mealName = mealName;
        this.mealCategory = mealCategory;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.protien = protien;
        this.calories = calories;
        this.date1 = date1;
        this.time1 = time1;
    }

    public Meals() {
    }



    public boolean isShowDate() {
        return isShowDate;
    }

    public void setShowDate(boolean showDate) {
        isShowDate = showDate;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtien() {
        return protien;
    }

    public void setProtien(String protien) {
        this.protien = protien;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
