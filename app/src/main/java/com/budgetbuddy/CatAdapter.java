package com.budgetbuddy;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CatAdapter extends  RecyclerView.Adapter<CatAdapter.CatViewHolder>
{
    private int mBudget;
    private int mSpent;
    private ArrayList<Category> mCatList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }


    public static class CatViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvType2;
        public TextView tvBudget2;


        public CatViewHolder(@NonNull View itemView, final OnItemClickListener listener)
        {
            super(itemView);
            tvType2 = itemView.findViewById(R.id.tvType2);
            tvBudget2 = itemView.findViewById(R.id.tvBudget2);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
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
        CatViewHolder cvh = new CatViewHolder(v2,mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position)
    {

        Category currentCat = mCatList.get(position);
        mBudget = currentCat.getBudget();
        mSpent = Process.calculateTotalSpentPerCategory(currentCat,Process.ABSOLUTE_TOTAL);
        if (mBudget > mSpent)
        {
            holder.tvBudget2.setTextColor(Color.GREEN);
        }
        else if (mBudget == mSpent)
        {
            holder.tvBudget2.setTextColor(Color.YELLOW);
        }
        else if (mBudget < mSpent)
        {
            holder.tvBudget2.setTextColor(Color.RED);
        }
        else
        {
            holder.tvBudget2.setTextColor(Color.BLACK);
        }
        holder.tvType2.setText(currentCat.getType());
        holder.tvBudget2.setText(""+ mSpent+ "/" + mBudget);
    }

    @Override
    public int getItemCount()
    {
        return mCatList.size();
    }
}
