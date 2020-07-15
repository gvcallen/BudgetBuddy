package com.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

public class InputActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout2;
    private AutoCompleteTextView drop_Down_Text;
    String[] categories = {"Food", "Rent", "Leisure", "Utilities"};

    @Override   //chnaged theme in manifest to accomodate the new  material for the input page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

                    //code to make drop down menu
                    textInputLayout2 = findViewById(R.id.txt_InputLayout_2);
                    drop_Down_Text = findViewById(R.id.dropdown_text);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>( InputActivity.this, R.layout.dropdown_item, categories);
                    drop_Down_Text.setAdapter(adapter);

    }
}