package com.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CatActivity extends AppCompatActivity
{

    private RecyclerView rvCat;
    private RecyclerView.Adapter rvAdapter2;
    private RecyclerView.LayoutManager rvLayoutManager2;
    private ArrayList<Category> mCatList;
    private Button btnHome;





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

        mCatList = MainActivity.mUser.getCategories();

        rvCat = findViewById(R.id.rvCat);
        rvCat.setHasFixedSize(true);
        rvLayoutManager2 = new LinearLayoutManager(this);
        rvAdapter2 = new CatAdapter(mCatList);

        rvCat.setLayoutManager(rvLayoutManager2);
        rvCat.setAdapter(rvAdapter2);


    }
    public void startMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}