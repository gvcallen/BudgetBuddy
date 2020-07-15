package com.budgetbuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SetupCategoriesFragment extends Fragment
{
	private View rootView;
	private TextView tvCategories;
	private Button btnNext;

	public SetupCategoriesFragment()
	{

	}
	public static SetupCategoriesFragment newInstance()
	{
		//SetupUserFragment fragment = new SetupUserFragment();
		//Bundle args = new Bundle();
		/*args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);*/
		//fragment.setArguments(args);
		return new SetupCategoriesFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/*if (getArguments() != null)
		{
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		rootView = inflater.inflate(R.layout.fragment_setup_categories, container, false);

		findViews();

		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view)
			{
				trySwipe();
			}
		});

		return rootView;
	}

	private void findViews()
	{
		tvCategories = rootView.findViewById(R.id.tvCategories);
		btnNext = rootView.findViewById(R.id.btnFinish);
	}

	private void trySwipe()
	{
		// if validation succeeds
		((SetupActivity)getActivity()).swipeRight();
		// else
		Toast.makeText(getActivity(), "Incorrect information entered. Please try again.", Toast.LENGTH_LONG).show();
	}
}
