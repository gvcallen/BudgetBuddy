package com.budgetbuddy;

import java.time.LocalDate;
import java.util.ArrayList;

public class Category
{
	private String mType;
	private int mBudget;
	private ArrayList<Transaction> mTransactions;

	Category(String type, int budget, ArrayList<Transaction> transactions)
	{
		mType = type;
		mBudget = budget;
		mTransactions = transactions;
	}

	public String getType()
	{
		return mType;
	}

	public void setType(String type)
	{
		mType = type;
	}

	public ArrayList<Transaction> getTransactions()
	{
		return mTransactions;
	}

	public void addTransaction(Transaction t)
	{
		int count = mTransactions.size();
		LocalDate compareDate;
		LocalDate newDate = t.getDate();

		for (int i = count - 1; i >= 0; i-- )
		{
			compareDate = mTransactions.get(i).getDate();
			if (compareDate.compareTo(newDate) >= 0)
			{
				mTransactions.add(i, t);
				return;
			}
		}
		mTransactions.add(0, t);
	}

	public void removeTransaction(int n)
	{
		mTransactions.remove(n);
	}

	public void editTransaction(int n, Transaction t)
	{
		LocalDate newDate = t.getDate();
		LocalDate oldDate = t.getDate();
		if (newDate.equals(oldDate))
		{
			mTransactions.set(n, t);
			return;
		}

		mTransactions.remove(n);
		addTransaction(t);
	}

	public Transaction getTransaction(int n)
	{
		return mTransactions.get(n);
	}

	public int getBudget()
	{
		return mBudget;
	}

	public void setBudget(int budget)
	{
		mBudget = budget;
	}
}
