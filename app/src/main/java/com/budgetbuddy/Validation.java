package com.budgetbuddy;

import android.content.Context;

public final class Validation
{
	private Validation(){};

	public static boolean isAlpha(String s)
	{
		return s.matches("[a-zA-Z]+");
	}

	public static boolean longerThan(String s, int n)
	{
		return s.length() > n;
	}
}
