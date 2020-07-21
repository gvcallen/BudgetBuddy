package com.budgetbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransListAdapter extends RecyclerView.Adapter<TransListAdapter.TransListViewHolder>
{
    private ArrayList<Transaction> mTransList;



    public static class TransListViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvAmount;
        public TextView tvLocation;
        public TextView tvDate;

        public TransListViewHolder(@NonNull View itemView)
        {
            super(itemView);

            tvAmount = (TextView) itemView.findViewById(R.id.txtViewAmount);
            tvLocation = (TextView) itemView.findViewById(R.id.txtViewLocation);
            tvDate = (TextView) itemView.findViewById(R.id.txtViewDate);
        }
    }

    public TransListAdapter (ArrayList<Transaction> transList)
    {

        mTransList = transList;
    }

    @NonNull
    @Override
    public TransListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list_item, parent, false);
        TransListViewHolder tlvh = new TransListViewHolder(v);
        return tlvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransListViewHolder holder, int position) {
        Transaction transaction = mTransList.get(position);
        String amount = ""+transaction.getAmount();
        String location = transaction.getLocation();
        String date = transaction.getDate().toString();
        holder.tvDate.setText("Date: "+ date);
        holder.tvLocation.setText("Location:" + location);
        holder.tvAmount.setText("Amount: " + amount);
    }

    @Override
    public int getItemCount() {
        return mTransList.size();
    }
}
