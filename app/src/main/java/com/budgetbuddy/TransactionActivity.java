package com.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout2;
    private AutoCompleteTextView mCategory;
    private CalendarView mcv_Date;
    private TextInputEditText mAmountSpent;
    private TextInputEditText mLocation;
    private int mYear, mMonth, mDayOfMonth;

    @Override   // changed theme in manifest to accommodate the new  material for the input page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);


        findViewById();

        List<String> dataEntries = new ArrayList<>();

        for (Category category : MainActivity.mUser.getCategories())
        {
            dataEntries.add(new String(category.getType()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>( TransactionActivity.this, R.layout.dropdown_item,dataEntries );
        mCategory.setAdapter(adapter);

        LocalDate localDate = LocalDate.now();
        mYear = localDate.getYear();
        mMonth = localDate.getMonthValue();
        mDayOfMonth = localDate.getDayOfMonth();

        final Button BtnAdd = (Button) findViewById(R.id.btnAddInput);

        mcv_Date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                mYear =year;
                mMonth = month;
                mDayOfMonth= dayOfMonth;

            }
        }
        );

        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             LocalDate localDate =LocalDate.now().withDayOfMonth(mDayOfMonth).withMonth(mMonth).withYear(mYear);
             Transaction transaction = new Transaction(localDate,Integer.parseInt(mAmountSpent.getText().toString()),mLocation.getText().toString());
             
             String type = mCategory.getText().toString();

             for (Category category: MainActivity.mUser.getCategories())
             {
                 if (category.getType().equals(type)){
                     category.addTransaction(transaction);
                 }
             }
             sendResult(true);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        sendResult(false);
    }

    private void sendResult(boolean success)
    {
        Intent resultIntent = new Intent();
        if (success)
        {
            setResult(RESULT_OK, resultIntent);
        }
        else
        {
            setResult(RESULT_CANCELED, resultIntent);
        }
        finish();
    }


    private void findViewById()
    {
        textInputLayout2 = findViewById(R.id.txtInputLayout2);
        mCategory = findViewById(R.id.cmbCategory);
        mcv_Date = findViewById(R.id.cvDate);
        mAmountSpent = findViewById(R.id.edtAmountSpent);
        mLocation = findViewById(R.id.edtLocation);
    }
}