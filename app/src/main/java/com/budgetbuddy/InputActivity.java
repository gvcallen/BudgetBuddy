package com.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;

public class InputActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout2;
    private AutoCompleteTextView drop_Down_Text;
    private CalendarView mcv_Date;
    private TextInputEditText mAmount_Spent;
    private TextInputEditText mLocation;
    private int mYear, mMonth, mDayOfMonth;
    String[] categories = {"Food", "Rent", "Leisure", "Utilities"};

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
        ArrayAdapter<String> adapter = new ArrayAdapter<>( InputActivity.this, R.layout.dropdown_item,categories );
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
            public void onClick(View view) {  // if an error comes up, it might be due to the date being a long variable

                LocalDate localDate =LocalDate.now().withDayOfMonth(mDayOfMonth).withMonth(mMonth).withYear(mYear);
             Transaction transaction = new Transaction(localDate,Integer.parseInt(mAmount_Spent.getText().toString()),mLocation.getText().toString());
             String category = drop_Down_Text.getText().toString();
             Int index = drop_Down_Text.
            }
        });

    }
}