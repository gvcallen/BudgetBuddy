package com.budgetbuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class SetupBudgetFragment extends Fragment
{
	private View rootView;
	private Button btnFinish;

	public SetupBudgetFragment()
	{

	}
	public static SetupBudgetFragment newInstance()
	{
		//SetupUserFragment fragment = new SetupUserFragment();
		//Bundle args = new Bundle();
		/*args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);*/
		//fragment.setArguments(args);
		return new SetupBudgetFragment();
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
		rootView = inflater.inflate(R.layout.fragment_setup_budget, container, false);

		findViews();

		btnFinish.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view)
			{
				tryFinish();
			}
		});

		return rootView;
	}

	private void findViews()
	{
		btnFinish = rootView.findViewById(R.id.btnFinish);
	}

	private void tryFinish()
	{
		// if validation succeeds...
		((SetupActivity)getActivity()).sendResult(true);
		// else
		Toast.makeText(getActivity(), "Incorrect information entered. Please try again.", Toast.LENGTH_LONG).show();
	}
}
