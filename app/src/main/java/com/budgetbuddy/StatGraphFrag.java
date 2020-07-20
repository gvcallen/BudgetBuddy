package com.budgetbuddy;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;


public class StatGraphFrag extends Fragment
{
    private AnyChartView mPieChartBudget;
    private AnyChartView mPieChartSpending;
    private View rootView;
    private Pie mPieDataBudget;
    private Pie mPieDataSpending;

    public static StatGraphFrag newInstance()
    {
        StatGraphFrag fragment = new StatGraphFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public StatGraphFrag()
    {

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
        rootView =inflater.inflate(R.layout.fragment_stat_graph, container, false);
        mPieChartBudget = rootView.findViewById(R.id.piChartBudget);

        loadPieChart();

        return rootView;
    }


    public void loadPieChart()
    {
        ArrayList<DataEntry> dataEntriesBudget = new ArrayList<>();
        mPieDataBudget = AnyChart.pie();

        for (Category category : MainActivity.mUser.getCategories())
        {
            dataEntriesBudget.add(new ValueDataEntry(category.getType(), category.getBudget()));
        }

        mPieDataBudget.data(dataEntriesBudget);
        mPieDataBudget.title("Monthly Budget");
        mPieChartBudget.setChart(mPieDataBudget);
    }
}