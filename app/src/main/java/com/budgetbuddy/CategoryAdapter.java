package com.budgetbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>
{
	ArrayList<View> mItems;
	ArrayList<Category> mCategories;

	public static class CategoryViewHolder extends RecyclerView.ViewHolder
	{
		public TextView tvType;
		public EditText edtAmount;

		public CategoryViewHolder(@NonNull View itemView)
		{
			super(itemView);
			tvType = itemView.findViewById(R.id.tvType);
			edtAmount = itemView.findViewById(R.id.edtAmount);
		}

	}

	public CategoryAdapter(ArrayList<Category> categories)
	{
		mCategories = categories;
		mItems = new ArrayList<>();
	}

	@NonNull
	@Override
	public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
		mItems.add(v);
		CategoryViewHolder cvh = new CategoryViewHolder(v);
		return cvh;
	}

	@Override
	public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position)
	{
		Category current = mCategories.get(position);

		holder.tvType.setText(current.getType());
		holder.edtAmount.setText("" + current.getBudget());
	}

	@Override
	public int getItemCount()
	{
		return mCategories.size();
	}
}
