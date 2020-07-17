package com.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class  SetupActivity extends AppCompatActivity
{
	// Variables
	private CustomViewPager mPager;
	private PagerAdapter mPagerAdapter;

	// Constants
	private static final int NUM_PAGES = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);

		mPager = findViewById(R.id.Pager);
		mPagerAdapter = new SetupPagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
	}

	@Override
	public void onBackPressed()
	{
		// If the user presses back on the first page, close the app
		if (mPager.getCurrentItem() == 0)
		{
			sendResult(false);
		}
		// Otherwise go back to the previous page
		else
		{
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
		}
	}

	public void swipeRight()
	{
		if (mPager.getCurrentItem() == NUM_PAGES)
		{
			return;
		}
		else
		{
			mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
		}
	}

	public void swipeLeft()
	{
		if (mPager.getCurrentItem() == 0)
		{
			return;
		}
		else
		{
			mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
		}
	}

	public void sendResult(boolean success)
	{
		Intent resultIntent = new Intent();
		if (success)
		{
			setResult(RESULT_OK, resultIntent);
		}
		else
		{
			setResult(RESULT_CANCELED, resultIntent);
		}
		finish();
	}


	private class SetupPagerAdapter extends FragmentStatePagerAdapter
	{
		public SetupPagerAdapter(FragmentManager fm)
		{
			super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		}

		@Override
		public Fragment getItem(int position)
		{
			Fragment fragment = null;
			switch (position)
			{
				case 0:
					fragment = SetupUserFragment.newInstance();
					break;
				case 1:
					fragment = SetupCategoriesFragment.newInstance();
					break;
				case 2:
					fragment = SetupBudgetFragment.newInstance();
					break;
			}
			return fragment;
		}

		@Override
		public int getCount()
		{
			return NUM_PAGES;
		}
	}
}
