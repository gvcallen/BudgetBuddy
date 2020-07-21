package com.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class TransactionListActivity extends AppCompatActivity {
    private RecyclerView mRVTransList;
    private RecyclerView.Adapter mAdapterTransList;
    private RecyclerView.LayoutManager mLayoutManagerTansLists;
    private static ArrayList<Transaction> mTransList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        int position = getIntent().getIntExtra("CATEGORY_POSITION",0);
        mTransList = MainActivity.mUser.getCategories().get(position).getTransactions();
        buildRecyclerView();
    }

    public void buildRecyclerView()
    {
        mRVTransList = findViewById(R.id.rvTransList);
        mRVTransList.setHasFixedSize(true);
        mLayoutManagerTansLists = new LinearLayoutManager(this);
        mAdapterTransList = new TransListAdapter(mTransList);

        mRVTransList.setLayoutManager(mLayoutManagerTansLists);
        mRVTransList.setAdapter(mAdapterTransList);
    }
}