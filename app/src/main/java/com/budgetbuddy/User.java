package com.budgetbuddy;

import java.util.ArrayList;

public class User
{
	private int mId;
	private int mIncome;
	private String mName, mSurname;
	private ArrayList<Category> mCategories;

	User(int id, String name, String surname, int income, ArrayList<Category> categories)
	{
		mId = id;
		mName = name;
		mSurname = surname;
		mCategories = categories;
		mIncome = income;
	}

	public int getId()
	{
		return mId;
	}

	public String getName()
	{
		return mName;
	}

	public void setName(String name)
	{
		mName = name;
	}

	public String getSurname()
	{
		return mSurname;
	}

	public void setSurname(String surname)
	{
		mSurname = surname;
	}

	public void setIncome(int income)
	{
		mIncome = income;
	}

	public int getIncome()
	{
		return mIncome;
	}

	public void setCategories(ArrayList<Category> categories)
	{
		mCategories = categories;
	}

	public ArrayList<Category> getCategories()
	{
		return mCategories;
	}
}
