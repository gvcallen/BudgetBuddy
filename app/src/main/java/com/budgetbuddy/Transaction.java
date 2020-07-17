package com.budgetbuddy;

import java.time.LocalDate;

public class Transaction
{
	private LocalDate mDate;
	private int mAmount;
	private String mLocation, mType;

	Transaction(LocalDate date, int amount, String location, String type)
	{
		mDate = date;
		mAmount = amount;
		mLocation = location;
		mType = type;
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

	public String getType() { return mType; }

	public void setType(String type) { mLocation = type; }
}
