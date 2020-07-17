package com.budgetbuddy;

import java.time.LocalDate;

public class Transaction
{
	private LocalDate mDate;
	private int mAmount;
	private String mLocation;

	Transaction(LocalDate date, int amount, String location)
	{
		mDate = date;
		mAmount = amount;
		mLocation = location;

	}

	public LocalDate getDate()
	{
		return mDate;
	}

	public void setDate(LocalDate date)
	{
		mDate = date;
	}

	public int getAmount()
	{
		return mAmount;
	}

	public void setAmount(int amount)
	{
		mAmount = amount;
	}

	public String getLocation() { return mLocation; }

	public void setLocation(String location) { mLocation = location; }


}
