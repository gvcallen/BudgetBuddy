package com.budgetbuddy;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;



public class Process
{

    private Process(){}

    public static int calculateTotalSpentPerCategory(ArrayList<Transaction> transactions, int periodInDays)
    {
        int total = 0;

        for(Transaction transaction: transactions)
        {
            if(Period.between(LocalDate.now(), transaction.getDate()).getDays() <= periodInDays)
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
            total += calculateTotalSpentPerCategory(category.getTransactions(), periodInDays);
        }
        return total;
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



