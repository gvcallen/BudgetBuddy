package com.budgetbuddy;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;



public class Process
{
    private Process(){}

    public static final int ABSOLUTE_TOTAL = -1;

    // -1 may be passed for periodInDays, in which case the absolute total will be calculated

    public static int calculateTotalSpentPerCategory(Category category, int periodInDays)
    {
        int total = 0;

        for (Transaction transaction: category.getTransactions())
        {
            if (isTransactionInPeriod(transaction,periodInDays))
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

    public static Category mostSaved(ArrayList<Category> categories, int periodInDays)
    {
        Category tempCategory = null;
        double temp = 0;
        double amountSaved = 0;
        for (Category category: categories)
        {
            amountSaved = category.getBudget()-calculateTotalSpentPerCategory(category,periodInDays);
            if (amountSaved > temp)
            {
                temp = amountSaved;
                tempCategory = category;
            }
        }
        return tempCategory;
    }

    public static Category mostSpent(ArrayList<Category> categories, int periodInDays)
    {
        Category tempCategory = null;
        double temp = 0;
        double amountSpent = 0;
        for (Category category: categories)
        {
            amountSpent = calculateTotalSpentPerCategory(category,periodInDays);
            if (amountSpent > temp)
            {
                temp = amountSpent;
                tempCategory = category;
            }
        }
        return tempCategory;
    }

    public static Category mostTransactions(ArrayList<Category> categories, int periodInDays)
    {
        Category tempCategory = null;
        double temp = 0;
        double numTransactions = 0;

        for (Category category: categories)
        {
            numTransactions = numTransactionsInPeriod(category, periodInDays);
            if (numTransactions > temp)
            {
                temp = numTransactions;
                tempCategory = category;
            }
        }
        return tempCategory;
    }

    public static Category categoryMostExpensiveTransaction (ArrayList<Category> categories, int periodInDays)
    {
        Category tempCategory = null;
        double temp = 0;
        double mostExpensiveTransactionAmount = 0;

        for (Category category: categories)
        {
            mostExpensiveTransactionAmount = mostExpensiveTransactionInCategory(category, periodInDays).getAmount();
            if (mostExpensiveTransactionAmount > temp)
            {
                temp = mostExpensiveTransactionAmount;
                tempCategory = category;
            }
        }
        return tempCategory;
    }

    public static Transaction mostExpensiveTransactionInCategory(Category category, int periodInDays)
    {
        Transaction tempTransaction = null;
        double temp = 0;
        double transactionAmount = 0;
        for (Transaction transaction: category.getTransactions())
        {
            transactionAmount = transaction.getAmount();
            if ((transactionAmount > temp) && (isTransactionInPeriod(transaction, periodInDays)))
            {
                temp = transactionAmount;
                tempTransaction = transaction;
            }
        }
        return tempTransaction;
    }

    public static int numTransactionsInPeriod(Category category, int periodInDays)
    {
        int numTransactions=0;
        for (Transaction transaction: category.getTransactions())
        {
            if (isTransactionInPeriod(transaction, periodInDays))
            {
                numTransactions++;
            }
        }

        return numTransactions;
    }

    public static boolean isTransactionInPeriod (Transaction transaction, int periodInDays)
    {
        if (periodInDays == ABSOLUTE_TOTAL)
        {
            return true;
        }
        else
        {
            if (Period.between(LocalDate.now(), transaction.getDate()).getDays() <= periodInDays)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }


}



