package com.budgetbuddy;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;



public class Process
{

    private Process(){}

    public static double calculateTotalSpentPerCategory(ArrayList<Transaction> arrayList2, int TIME_PERIOD)
    {
        double total = 0;

        for(Transaction item: arrayList2)
        {
            if(Period.between(LocalDate.now(), item.getDate()).getDays() <= TIME_PERIOD)
            {
                total += item.getAmount();
            }
        }
        return total;
    }


    public static double calculateTotalSpentOverall(ArrayList<Category> arrayList, ArrayList<Transaction> arrayList2, int TIME_PERIOD)
    {
        double overallTotal = 0;

        for(Category i: arrayList)
        {
            overallTotal += calculateTotalSpentPerCategory(arrayList2, TIME_PERIOD);
        }
        return overallTotal;
    }

    public static ArrayList<Double> calculateDistribution(ArrayList<Category> arrayList, ArrayList<Transaction> arrayList2, int TIME_PERIOD)
    {
        double overallTotal = 0;
        double temp = 0;
        ArrayList<Double> categoryTotals = new ArrayList<Double>();
        ArrayList<Double> distribution = new ArrayList<Double>();

        for(Category i: arrayList)
        {
            temp = calculateTotalSpentPerCategory(arrayList2, TIME_PERIOD);
            categoryTotals.add(temp);
            overallTotal += temp;
        }

        for(double j: categoryTotals)
        {
            distribution.add(j/overallTotal);
        }

        return distribution;
    }


}



