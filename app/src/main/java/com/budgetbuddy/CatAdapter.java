package com.budgetbuddy;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CatAdapter extends  RecyclerView.Adapter<CatAdapter.CatViewHolder>
{
    private ArrayList<Category> mCatList;
    public static class CatViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvType2;
        public TextView tvBudget2;

        public CatViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvType2 = itemView.findViewById(R.id.tvType2);
            tvBudget2 = itemView.findViewById(R.id.tvBudget2);

        }
    }

    public CatAdapter(ArrayList<Category> catList)
    {
        mCatList = catList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item, parent, false);
        CatViewHolder cvh = new CatViewHolder(v2);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position)
    {
        Category currentCat = mCatList.get(position);

        holder.tvType2.setText(currentCat.getType());
        holder.tvBudget2.setText(currentCat.getBudget());

    }

    @Override
    public int getItemCount()
    {
        return mCatList.size();
    }
}
