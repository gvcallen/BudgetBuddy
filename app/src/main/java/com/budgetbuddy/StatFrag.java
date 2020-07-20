package com.budgetbuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatFrag extends Fragment {
    private TextView moneySaved;
    private TextView moneySpent;
    private TextView mostTransactions;
    private TextView expensiveTransaction;
    private EditText timePeriod;
    private String text;
    private View rootView;

    public StatFrag()
    {
        // Required empty public constructor
    }

    public static StatFrag newInstance()
    {
        StatFrag fragment = new StatFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_stat, container, false);

        moneySaved = (TextView) rootView.findViewById(R.id.txtViewMoneySaved);
        moneySpent = (TextView) rootView.findViewById(R.id.txtViewMoneySpent);
        mostTransactions = (TextView) rootView.findViewById(R.id.txtViewMostTransactions);
        expensiveTransaction = (TextView) rootView.findViewById(R.id.txtViewExpensiveTransaction);
        timePeriod = (EditText) rootView.findViewById(R.id.edtTxtTimePeriod);
        timePeriod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String tempString=timePeriod.getText().toString();
                moneySaved.setText(moneySavedString(Integer.parseInt(tempString)));
                moneySpent.setText(moneySpentString(Integer.parseInt(tempString)));
                mostTransactions.setText(mostTransactionsString(Integer.parseInt(tempString)));
                expensiveTransaction.setText(expensiveTransactionString(Integer.parseInt(tempString)));
            }
        });
        return rootView;
    }

    private String moneySavedString(int periodInDays)
    {
        Category category = Process.mostSaved(MainActivity.mUser.getCategories(),periodInDays);
        int amount = category.getBudget()-Process.calculateTotalSpentPerCategory(category,periodInDays);
        String string = this.getString(R.string.moneySaved);
        string += "\nCategory: " + category.getType();
        string += "\nMoney saved: " + amount;
        return string;
    }

    private String moneySpentString(int periodInDays)
    {
        Category category = Process.mostSpent(MainActivity.mUser.getCategories(),periodInDays);
        int amount = Process.calculateTotalSpentPerCategory(category,periodInDays);
        String string = this.getString(R.string.moneySpent);
        string += "\nCategory: " + category.getType();
        string += "\nMoney spent: " + amount;
        return string;
    }

    private String mostTransactionsString(int periodInDays)
    {
        Category category = Process.mostTransactions(MainActivity.mUser.getCategories(),periodInDays);
        int amount = category.getTransactions().size();
        String string = this.getString(R.string.mostTransactions);
        string += "\nCategory: " + category.getType();
        string += "\nNumber of transactions: " + amount;
        return string;
    }

    private String expensiveTransactionString(int periodInDays)
    {
        Category category = Process.categoryMostExpensiveTransaction(MainActivity.mUser.getCategories(),periodInDays);
        Transaction transaction = Process.mostExpensiveTransactionInCategory(category, periodInDays);
        int amount = transaction.getAmount();
        String place = transaction.getLocation();
        String date = transaction.getDate().toString();
        String string = this.getString(R.string.expensiveTransaction);
        string += "\nCategory: " + category.getType();
        string += "\nLocation: " + place;
        string += "\nDate: " + date;
        string += "\nAmount: " + amount;
        return string;
    }
}