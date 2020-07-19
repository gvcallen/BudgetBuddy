package com.budgetbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CatEditAdapter extends RecyclerView.Adapter<CatEditAdapter.CatEditViewHolder>
{
    private ArrayList<Category> mCatEditList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public static class CatEditViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvEditType;
        public TextView tvEditBud;
        public ImageView icDelete;

        public CatEditViewHolder(View itemView, final OnItemClickListener listener)
        {
            super(itemView);
            tvEditType = itemView.findViewById(R.id.tvEditType);
            tvEditBud = itemView.findViewById(R.id.tvEditBudget);
            icDelete = itemView.findViewById(R.id.icDelete);

            icDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listener.onDeleteClick(position);
                        }
                    }

                }
            });
        }
    }
    public CatEditAdapter(ArrayList<Category> catList) {mCatEditList = catList;}

    @NonNull
    @Override
    public CatEditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View editView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_edit_item, parent, false);
        //CatEditAdapter.CatEditViewHolder cevh = new CatEditAdapter.CatEditViewHolder(editView, mListener);
        CatEditViewHolder cevh = new CatEditViewHolder(editView, mListener);
        return cevh;
    }

    @Override
    public void onBindViewHolder(@NonNull CatEditViewHolder holder, int position)
    {
        Category currentCatEdit = mCatEditList.get(position);

        holder.tvEditType.setText(currentCatEdit.getType());
        holder.tvEditBud.setText("" + currentCatEdit.getBudget());

    }

    @Override
    public int getItemCount() {
        return mCatEditList.size();
    }
}
