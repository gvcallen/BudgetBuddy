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

public class SetupUserFragment extends Fragment
{
	private View rootView;
	private Button btnNext;
	private TextView tvUser;
	private EditText edtName, edtSurname;

	public SetupUserFragment()
	{

	}
	public static SetupUserFragment newInstance()
	{
		return new SetupUserFragment();
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
		rootView = inflater.inflate(R.layout.fragment_setup_user, container, false);

		findViews();

		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view)
			{
				trySwipe();
			}
		});
		tvUser.setText(Html.fromHtml("We see that its your first time using BudgetBuddy.<br></br><br></br>Please enter your <b>details</b> to get started:"));
		return rootView;
	}

	private void findViews()
	{
		btnNext = rootView.findViewById(R.id.btnFinish);
		tvUser = rootView.findViewById(R.id.tvUser);
		edtName = rootView.findViewById(R.id.edtName);
		edtSurname = rootView.findViewById(R.id.edtSurname);
	}

	public String getName()
	{
		return edtName.getText().toString();
	}

	public String getSurname()
	{
		return edtSurname.getText().toString();
	}

	private void trySwipe()
	{
		String name = edtName.getText().toString();
		String surname = edtSurname.getText().toString();

		if (name.isEmpty() || surname.isEmpty())
		{
			Toast.makeText(getActivity(), "No field may be empty.", Toast.LENGTH_LONG).show();
		}
		else if (!Validation.isAlpha(name) || !Validation.isAlpha(surname))
		{
			Toast.makeText(getActivity(), "Fields may only contain letters.", Toast.LENGTH_LONG).show();
		}
		else if (!Validation.longerThan(name, 1) || !Validation.longerThan(surname, 1))
		{
			Toast.makeText(getActivity(), "Fields must be a minimum of 2 characters.", Toast.LENGTH_LONG).show();
		}
		else
		{
			MainActivity.mUser = new User(0, name, surname, 0, null);
			((SetupActivity)getActivity()).swipeRight();
		}
	}
}
