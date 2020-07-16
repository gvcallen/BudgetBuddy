package com.budgetbuddy;

import com.google.gson.Gson;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

final public class Data
{
    private User mUser;
    private Formatter mFormatter;
    private Scanner mScanner;
    private String json;


    private Data()
    {

    }

    //returns a User object from the json file
    private User readUser()
    {
        json = "";
        Gson gson = new Gson();
        try
        {
            mScanner = new Scanner(new File("User.json"));
        }
        catch (Exception e){

        }
        while (mScanner.hasNext())
        {
            json += mScanner.next();
        }


        mUser = gson.fromJson(json, User.class);
        return mUser;
    }
    //overwrites the json file and stores a new user object
    private void saveUser(User user){
        mUser = user;

        try
        {
            mFormatter = new Formatter("User.json");
        }
        catch (Exception e){

        }

        Gson gson = new Gson();
        json = gson.toJson(mUser);

        mFormatter.format(json);

        mFormatter.close();
    }

    //checks if the json file exists
    private Boolean fileExists(){
        try
        {
            mScanner = new Scanner(new File("User.json"));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
