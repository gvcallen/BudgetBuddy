package com.budgetbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
{
	// Variables
	private AnyChartView mPieChart;
	private String[] mCategories = {"Food", "Rent", "Leisure", "Utilities"} ;
	private int[] mAmounts = {3000, 8000, 1000, 2000 } ;
	private Button btnAdd;
	public static User mUser;

	// Constants
	public static final int REQUEST_SETUP = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// Create a dummy user (NOTE: This will be replaced with the user that gets loaded from file
		ArrayList<Category> categories = new ArrayList<Category>();
		categories.add(new Category("Food", 3000, new ArrayList<Transaction>()));
		categories.add(new Category("Food", 8000, new ArrayList<Transaction>()));
		categories.add(new Category("Food", 1000, new ArrayList<Transaction>()));
		categories.add(new Category("Food", 2000, new ArrayList<Transaction>()));
		mUser = new User(0, "John", "Doe", 25000, categories);

		// Check if user file exists. If yes, initialize this activity, else open the setup activity
		boolean fileExists = true;
		if (fileExists)
		{
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
				init();
			}
			else
			{
				finish();
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

		// Implement pie-chart
		mPieChart = findViewById(R.id.piChart);
		setupPieChart();

		// Create toolbar
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// Set buttons on click listener to open transaction activity
		btnAdd = (Button)findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startTransactionActivity();
			}
		});
	}

	public void setupPieChart()
	{
		Pie pie = AnyChart.pie();
		List<DataEntry> dataEntries = new ArrayList<>();

			for (int i=0; i<mCategories.length;i++)
			{
				dataEntries.add(new ValueDataEntry(mCategories[i], mAmounts[i]));
			}

		pie.data(dataEntries);
		pie.title("Monthly Spending");
		mPieChart.setChart(pie);

	}

	public void startTransactionActivity()
	{
		Intent intent = new Intent(this, InputActivity.class);
		startActivity(intent);
	}

	public void startSetupActivity()
	{
		Intent intent = new Intent(this, SetupActivity.class);
		startActivityForResult(intent, REQUEST_SETUP);
	}
}
