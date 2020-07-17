package com.budgetbuddy;

import com.google.gson.Gson;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

final public class Data
{
    private static Formatter mFormatter;
    private static Scanner mScanner;
    private static String json;
    private static Gson gson;


    private Data()
    {

    }

    private static void initGson()
    {
        if (gson == null)
        {
            gson = new Gson();
        }
    }

    // Returns a User object from the json file
    public static User readUser()
    {
        json = "";
        initGson();

        try
        {
            mScanner = new Scanner(new File("User.json"));
        }
        catch (Exception e) {
            System.out.println("Formatter unsuccessful");
        }

        while (mScanner.hasNext())
        {
            json += mScanner.next();
        }
        return gson.fromJson(json, User.class);
    }
    // Overwrites the json file and stores a new user object
    public static void saveUser(User user)
    {
        initGson();
        try
        {
            mFormatter = new Formatter("User.json");
        }

        catch (Exception e) {
            System.out.println("Formatter unsuccessful");
        }
        json = gson.toJson(user);
        mFormatter.format(json);
        mFormatter.close();
    }

    // Checks if the json file exists
    public static boolean fileExists()
    {
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
        
