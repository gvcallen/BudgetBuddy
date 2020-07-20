package com.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CatEditActivity extends AppCompatActivity
{
    private ArrayList<Category> mCatList;
    private RecyclerView rvEditCat;
    private CatEditAdapter rvEditAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    private Button btnAddCat;
    private EditText edtAddType;
    private EditText edtAddBud;
    private Button btnBack;
    private Category mCat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_edit2);

        mCatList = MainActivity.mUser.getCategories();

        buildRecyclerView();



        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCatActivity();
            }
        });

        btnAddCat = findViewById(R.id.btnAddCat);
        edtAddType = findViewById(R.id.edtAddType);
        edtAddBud = findViewById(R.id.edtAddBud);
        //mCat = new Category(edtAddType.getText().toString(), Integer.parseInt(edtAddBud.getText().toString()), null);


        btnAddCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mCat = new Category(edtAddType.getText().toString(), Integer.parseInt(edtAddBud.getText().toString()), null);
                addCat(mCat);

            }
        });


    }

    public void addCat(Category c)
    {
        mCatList.add(c);
        rvEditAdapter.notifyItemInserted(mCatList.size()-1);
    }
    public void buildRecyclerView()
    {
        rvEditCat = findViewById(R.id.rvCatEdit);
        rvEditCat.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(this);
        rvEditAdapter = new CatEditAdapter(mCatList);

        rvEditCat.setLayoutManager(rvLayoutManager);
        rvEditCat.setAdapter(rvEditAdapter);

        rvEditAdapter.setOnItemClickListener(new CatEditAdapter.OnItemClickListener()
        {
            @Override
            public void onDeleteClick(int position)
            {
                removeItem(position);
            }
        });
    }

    public void removeItem(int position)
    {
        mCatList.remove(position);
        rvEditAdapter.notifyDataSetChanged();
    }

    public void startCatActivity()
    {
        Intent intent = new Intent(this, CatActivity.class);
        startActivity(intent);
    }


}