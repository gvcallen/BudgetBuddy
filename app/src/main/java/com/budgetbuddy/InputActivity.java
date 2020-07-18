package com.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InputActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout2;
    private AutoCompleteTextView drop_Down_Text;
    private CalendarView mcv_Date;
    private TextInputEditText mAmount_Spent;
    private TextInputEditText mLocation;
    private int mYear, mMonth, mDayOfMonth;

    @Override   //chnaged theme in manifest to accomodate the new  material for the input page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Code to make drop down menu
        textInputLayout2 = findViewById(R.id.txt_InputLayout_2);
        drop_Down_Text = findViewById(R.id.dropdown_text);
        mcv_Date = findViewById(R.id.cv_Date);
        mAmount_Spent = findViewById(R.id.edt_Amount_Spent);
        mLocation = findViewById(R.id.edt_Location);
        List<String> dataEntries = new ArrayList<>();
        for (Category category : MainActivity.mUser.getCategories())
        {
            dataEntries.add(new String(category.getType()));

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>( InputActivity.this, R.layout.dropdown_item,dataEntries );
        drop_Down_Text.setAdapter(adapter);


        Button mBtn_Add = (Button) findViewById(R.id.btn_Add_Input);

        mcv_Date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                mYear =year;
                mMonth = month;
                mDayOfMonth= dayOfMonth;

            }
        }
        );

        mBtn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LocalDate localDate =LocalDate.now().withDayOfMonth(mDayOfMonth).withMonth(mMonth).withYear(mYear);
             Transaction transaction = new Transaction(localDate,Integer.parseInt(mAmount_Spent.getText().toString()),mLocation.getText().toString());
             int index = drop_Down_Text.getListSelection();
             String type = drop_Down_Text.getText().toString();
             for (Category category: MainActivity.mUser.getCategories())
             {
                 if (category.getType()==type){
                     category.addTransaction(transaction);
                 }
             }

             startHomepageActivity();
            }
        });

    }
    public void startHomepageActivity()
    {
        Intent intent = new Intent(this, InputActivity.class);
        startActivityForResult(intent,1);
    }
}