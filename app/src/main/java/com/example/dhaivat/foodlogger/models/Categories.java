package com.example.dhaivat.foodlogger.models;

/**
 * Created by jaydeep on 10/9/18.
 */

public class Categories {

    public static final String TABLE_NAME_CATEGORY = "category";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CAT_NAME = "category_name";

    private String id, catName;

    // Create table SQL query
    public static final String CREATE_TABLE_CATEGORY =
            "CREATE TABLE " + TABLE_NAME_CATEGORY + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_CAT_NAME + " TEXT"
                    + ")";

    public Categories() {
    }

    public Categories(String catName) {
        this.catName = catName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
