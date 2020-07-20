package com.budgetbuddy;

import android.content.Context;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

final public class Data
{
    private static String json;
    private static Gson gson;
    private static String fileName = "User.json";


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
    public static User readUser(Context context)
    {
        initGson();
        json = loadTextFromFile(fileName, context);
        return gson.fromJson(json,User.class);
    }
    
    // Overwrites the json file and stores a new user object
    public static void saveUser(User user, Context context)
    {
        initGson();
        json = gson.toJson(user);
        saveTextToFile(json,fileName,context);
    }

    // Checks if a file exists
    public static boolean fileExists(Context context)
    {
        File file = context.getFileStreamPath(fileName);
        if(file == null || !file.exists())
        {
            return false;
        }
        return true;
    }

    //overwrites and saves text to a given file or creates a new file if no file exists with the given name
    private static void saveTextToFile(String text, String fileName, Context context)
    {
        FileOutputStream fos = null;

        try
        {
            fos = context.openFileOutput(fileName,context.MODE_PRIVATE);
            fos.write(text.getBytes());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fos!=null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    //loads text from a given file and returns it
    private static String loadTextFromFile(String fileName, Context context)
    {
        FileInputStream fis = null;

        try
        {
            fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();

            String text;
            while ((text = br.readLine()) != null)
            {
                sb.append(text);
            }
            return sb.toString();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (fis !=null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteFile(Context context){
        context.deleteFile(fileName);
    }


    public static void setFileName(String newFileName)
    {
        fileName = newFileName;
    }

    public static String getFileName()
    {
        return fileName;
    }
}

