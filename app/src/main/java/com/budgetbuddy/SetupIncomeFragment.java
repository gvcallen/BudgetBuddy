package com.budgetbuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SetupIncomeFragment extends Fragment
{
	private View rootView;
	private TextView tvIncome;
	private EditText edtIncome;
	private Button btnFinish;

	public SetupIncomeFragment()
	{

	}
	public static SetupIncomeFragment newInstance()
	{
		return new SetupIncomeFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		rootView = inflater.inflate(R.layout.fragment_setup_income, container, false);

		findViews();

		// set text
		tvIncome.setText(Html.fromHtml("Almost finished!<br></br><br></br>Simply enter your <b>monthly income</b> and started enjoying BudgetBuddy!"));
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
		tvIncome = rootView.findViewById(R.id.tvIncome);
		edtIncome = rootView.findViewById(R.id.edtIncome);
	}

	private void tryFinish()
	{
		String value = edtIncome.getText().toString();
		if (value.isEmpty())
		{
			Toast.makeText(getActivity(), "Please enter an amount.", Toast.LENGTH_LONG).show();
		}
		else
		{
			MainActivity.mUser.setIncome(Integer.parseInt(value));
			((SetupActivity)getActivity()).sendResult(true);
		}
	}
}
