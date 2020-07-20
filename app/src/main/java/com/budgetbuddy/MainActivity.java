package com.budgetbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
	// Variables
	private AnyChartView mPieChart;
	private Button btnAdd;
	private Button btnCategories;
	private Button btnStats;
	private TextView textView;
	private Pie mPieData = null;
	public static User mUser;


	// Constants
	public static final int REQUEST_SETUP = 0, REQUEST_ADD_TRANSACTION = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if (Data.fileExists(getApplicationContext()))
		{
			mUser = Data.readUser(getApplicationContext());
			init();
		}
		else
		{
			startSetupActivity();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_SETUP)
		{
			if (resultCode == RESULT_OK)
			{
				Data.saveUser(mUser, getApplicationContext());
				init();
			}
			else
			{
				finish();
			}
		}
		else if (requestCode == REQUEST_ADD_TRANSACTION)
		{
			if (resultCode == RESULT_OK)
			{
				setupPieChart();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Add the settings button on the top right, fetching the menu layout resource
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_homepage,menu);
		return true;
	}

	public void init()
	{
		setContentView(R.layout.activity_main);

		findViewById();

		// Implement pie-chart
		setupPieChart();

		// Create toolbar
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// Set buttons on click listener to open transaction activity

		btnAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startTransactionActivity();
			}

		});

		// Set button on click listener to open categories page

		btnCategories.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startCatActivity();
			}
		});

		//Set button on click listener to open statistics page

		btnStats.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startStatActivity();
			}
		});
	}

	public void setupPieChart()
	{


		ArrayList<DataEntry> dataEntries = new ArrayList<>();
		int totalSpent = Process.calculateTotalSpentOverall(mUser.getCategories(), Process.ABSOLUTE_TOTAL);
		if (totalSpent == 0)
		{
			textView.setText("You have no transactions.\nClick the '+' button to add one.");
		}
		else
		{
			textView.setText("Total spent: " + totalSpent);

			if (mPieData == null)
			{
			firstTimeUsingPieChart();
			}
			else
			{
			refreshingPieChart();
			}


		}
	}

	public void startTransactionActivity()
	{
		Intent intent = new Intent(this, TransactionActivity.class);
		startActivityForResult(intent, REQUEST_ADD_TRANSACTION);
	}

	public void startSetupActivity()
	{
		Intent intent = new Intent(this, SetupActivity.class);
		startActivityForResult(intent, REQUEST_SETUP);
	}


	public void startCatActivity()
	{
		Intent intent = new Intent(this, CatActivity.class);
		startActivity(intent);
	}

	public void startStatActivity() {
			Intent intent = new Intent(this, StatisticActivity.class);
			startActivity(intent);
	}

	public void firstTimeUsingPieChart()
	{
		ArrayList<DataEntry> dataEntries = new ArrayList<>();
		mPieData = AnyChart.pie();

		for (Category category : mUser.getCategories())
		{
			dataEntries.add(new ValueDataEntry(category.getType(), Process.calculateTotalSpentPerCategory(category, Process.ABSOLUTE_TOTAL)));
		}

		mPieData.data(dataEntries);
		mPieData.title("Monthly Spending");
		mPieChart.setChart(mPieData);
	}

	public void refreshingPieChart()
	{
		ArrayList<DataEntry> dataEntries = new ArrayList<>();

		for (Category category : mUser.getCategories())
		{
			dataEntries.add(new ValueDataEntry(category.getType(), Process.calculateTotalSpentPerCategory(category, Process.ABSOLUTE_TOTAL)));
		}

		mPieData.data(dataEntries);
		mPieData.title("Monthly Spending");
	}


	public void findViewById()
	{
		btnAdd = (Button)findViewById(R.id.btnAdd);
		textView =  findViewById(R.id.textView);
		btnCategories = (Button) findViewById(R.id.btnCategories);
		mPieChart = findViewById(R.id.piChart);
		btnStats = (Button) findViewById(R.id.btnStatistics);
	}

}
