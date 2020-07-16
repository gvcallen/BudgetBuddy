package com.budgetbuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SetupCategoriesFragment extends Fragment
{
	// Variables
	private View rootView;
	private TextView tvCategories;
	private Button btnNext;
	private ArrayList<Category> categories;
	private RecyclerView rvCategories;
	private RecyclerView.Adapter rvAdapter;
	private RecyclerView.LayoutManager rvLayoutManager;

	public SetupCategoriesFragment()
	{

	}
	public static SetupCategoriesFragment newInstance()
	{
		return new SetupCategoriesFragment();
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
		rootView = inflater.inflate(R.layout.fragment_setup_categories, container, false);

		findViews();

		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view)
			{
				trySwipe();
			}
		});
		tvCategories.setText(Html.fromHtml("BudgetBuddy works with <b>categories</b>,<br></br>allowing you to easily sort your transactions.<br></br><br></br>We've set up a few basic ones below.<br></br>Please enter your estimated monthly budget for each.<br></br><br></br>Don't worry - you'll be able to change these later."));

		categories = new ArrayList<>();
		categories.add(new Category("Food", 0, new ArrayList<Transaction>()));
		categories.add(new Category("Rent", 0, new ArrayList<Transaction>()));
		categories.add(new Category("Leisure", 0, new ArrayList<Transaction>()));
		categories.add(new Category("Utilities", 0, new ArrayList<Transaction>()));

		rvCategories.setHasFixedSize(true);
		rvLayoutManager = new LinearLayoutManager(getContext());
		rvAdapter = new CategoryAdapter(categories);

		rvCategories.setLayoutManager(rvLayoutManager);
		rvCategories.setAdapter(rvAdapter);

		return rootView;
	}

	private void findViews()
	{
		tvCategories = rootView.findViewById(R.id.tvCategories);
		btnNext = rootView.findViewById(R.id.btnFinish);
		rvCategories = rootView.findViewById(R.id.rvCategories);
	}

	private void trySwipe()
	{
		for (int i = 0; i < categories.size(); i++)
		{
			String value = ((TextView) rvCategories.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.edtAmount)).getText().toString();
			if (value.isEmpty())
			{
				Toast.makeText(getContext(), "No value may be empty.", Toast.LENGTH_LONG).show();
				return;
			}
			else
			{
				categories.get(i).setBudget(Integer.parseInt(value));
			}
		}
		MainActivity.mUser.setCategories(categories);
		((SetupActivity)getActivity()).swipeRight();
	}
}
