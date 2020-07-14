package com.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
//import android.widget.Toolbar;
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
	AnyChartView pieChart;
	String[] categories = {"Food", "Rent", "Leisure", "Utilities"} ;
	int[] amounts = {3000, 8000, 1000, 2000 } ;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//implementing piechart
		pieChart = findViewById(R.id.piChart);
		setupPieChart();

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



	public void setupPieChart()
	{
		Pie pie = AnyChart.pie();
		List<DataEntry> dataEntries = new ArrayList<>();

			for (int i=0; i<categories.length;i++)
			{
				dataEntries.add(new ValueDataEntry(categories[i], amounts[i]));
			}

		pie.data(dataEntries);
		pie.title("Monthly Spending");
		pieChart.setChart(pie);

	}
}
