package com.budgetbuddy;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;



public class Process
{
    private Process(){}

    public static int calculateTotalSpentPerCategory(Category category, int periodInDays)
    {
        int total = 0;

        for (Transaction transaction: category.getTransactions())
        {
            if (Period.between(LocalDate.now(), transaction.getDate()).getDays() <= periodInDays)
            {
                total += transaction.getAmount();
            }
        }
        return total;
    }


    public static int calculateTotalSpentOverall(ArrayList<Category> categories, int periodInDays)
    {
        int total = 0;

        for(Category category: categories)
        {
            total += calculateTotalSpentPerCategory(category, periodInDays);
        }
        return total;
    }

    public static ArrayList<Double> calculateDistribution(ArrayList<Category> categories, int periodInDays)
    {
        double overallTotal = 0;
        double temp = 0;
        ArrayList<Double> categoryTotals = new ArrayList<Double>();
        ArrayList<Double> fractionDistribution = new ArrayList<Double>();

        for(Category category: categories)
        {
            temp = calculateTotalSpentPerCategory(category, periodInDays);
            categoryTotals.add(temp);
            overallTotal += temp;
        }

        for(double i: categoryTotals)
        {
            fractionDistribution.add(i / overallTotal);
        }

        return fractionDistribution;
    }


}



