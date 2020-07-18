package com.budgetbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
{
	// Variables
	private AnyChartView mPieChart;
	private Button btnAdd;
	private Button btnCategories;
	public static User mUser;


	// Constants
	public static final int REQUEST_SETUP = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		boolean fileExists = true;
		if (fileExists)

			if (Data.fileExists(getApplicationContext())) {

				mUser = Data.readUser(getApplicationContext());
				init();


				Toast.makeText(this, "" + mUser.getIncome(), Toast.LENGTH_LONG).show();

			} else {
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
<<<<<<< HEAD
		});				// Show
=======
		});

		// Set button on click listener to open categories page
		btnCategories = (Button) findViewById(R.id.btnCategories);
		btnCategories.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startCatActivity();
			}
		});



>>>>>>> be3d12f4a523e49946d5fdca8bd8910cd36df997
	}

	public void setupPieChart()
	{

		Pie pie = AnyChart.pie();
		List<DataEntry> dataEntries = new ArrayList<>();
		if (Process.calculateTotalSpentOverall(mUser.getCategories(), Process.ABSOLUTE_TOTAL)== 0)
		{
			//show messgae that there are no transactions made
			TextView textView = (TextView) findViewById(R.id.textView);
			textView.setVisibility(textView.VISIBLE);				//ITS NOT RUNNING



		}else {


			for (Category category : mUser.getCategories())
			{
				dataEntries.add(new ValueDataEntry(category.getType(), Process.calculateTotalSpentPerCategory(category, Process.ABSOLUTE_TOTAL)));

			}

			pie.data(dataEntries);
			pie.title("Monthly Spending");
			mPieChart.setChart(pie);
		}

	}

	public void startTransactionActivity()
	{
		Intent intent = new Intent(this, InputActivity.class);
		startActivity(intent);
	}

	public void startSetupActivity()
<<<<<<< HEAD
{
	Intent intent = new Intent(this, SetupActivity.class);
	startActivityForResult(intent, REQUEST_SETUP);
}
=======
	{
		Intent intent = new Intent(this, SetupActivity.class);
		startActivityForResult(intent, REQUEST_SETUP);
	}

	public void startCatActivity()
	{
		Intent intent = new Intent(this, CatActivity.class);
		startActivity(intent);
	}

>>>>>>> be3d12f4a523e49946d5fdca8bd8910cd36df997
}
