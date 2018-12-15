package com.example.dhaivat.foodlogger.models;

/**
 * Created by jaydeep.rana on 10-09-2018.
 */

public class LoginSignUp {

    public static final String TABLE_NAME = "login";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE_NUMBER = "phone_no";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_RETYPE = "retype";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_WEIGHT = "weight";


    private int id = -1;
    private String name,email,phoneNo,password,retype,gender,dob,height,weight;


    // Create table SQL query
    public static final String CREATE_TABLE_LOGIN =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_PHONE_NUMBER + " TEXT,"
                    + COLUMN_PASSWORD + " TEXT,"
                    + COLUMN_RETYPE + " TEXT,"
                    + COLUMN_GENDER + " TEXT,"
                    + COLUMN_DOB + " TEXT,"
                    + COLUMN_HEIGHT + " TEXT,"
                    + COLUMN_WEIGHT + " TEXT"
                    + ")";


    public LoginSignUp( String name, String email, String phoneNo, String password, String retype) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.retype = retype;
    }

    public LoginSignUp() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetype() {
        return retype;
    }

    public void setRetype(String retype) {
        this.retype = retype;
    }
}
