package com.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CatActivity extends AppCompatActivity
{
    private RecyclerView rvCat;
    private CatAdapter rvAdapter2;
    private RecyclerView.LayoutManager rvLayoutManager2;
    private ArrayList<Category> mCatList;
    private Button btnHome;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMainActivity();
            }
        });

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startEditActivity();
            }
        });

        mCatList = MainActivity.mUser.getCategories();

        buildRecyclerView();
    }

    public void startMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startEditActivity()
    {
        Intent intent = new Intent(this, CatEditActivity.class);
        startActivity(intent);
    }

    public void startTransListActivity(int position)
    {
        Intent intent = new Intent(this,TransactionListActivity.class);
        intent.putExtra("CATEGORY_POSITION",position);
        startActivity(intent);
    }

    public void buildRecyclerView()
    {
        rvCat = findViewById(R.id.rvCat);
        rvCat.setHasFixedSize(true);
        rvLayoutManager2 = new LinearLayoutManager(this);
        rvAdapter2 = new CatAdapter(mCatList);

        rvCat.setLayoutManager(rvLayoutManager2);
        rvCat.setAdapter(rvAdapter2);
        rvAdapter2.setOnItemClickListener(new CatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startTransListActivity(position);
            }
        });
    }

}