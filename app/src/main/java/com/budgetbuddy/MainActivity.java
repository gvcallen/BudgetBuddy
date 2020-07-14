package com.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;



public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		//toolbar
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		//This is the method to add the settings button on the top right it fetches the menu layout in the menu directory
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_homepage,menu);
		return true;
	}
}
