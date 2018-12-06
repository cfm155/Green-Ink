package com.example.cartermccall.greenink;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class UpgradesAdapter extends RecyclerView.Adapter<UpgradesAdapter.UpgradesViewHolder> {
    private Context context;
    private Building[] buildings;
    private RecyclerViewClickListener mListener;

    public UpgradesAdapter(Context context, Building[] data, RecyclerViewClickListener clickListener){
        this.context = context;
        this.buildings = data;
        this.mListener = clickListener;
    }

    public static class UpgradesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameView;
        public TextView costView;
        public TextView incomeView;
        public TextView pollutionView;
        private RecyclerViewClickListener mListener;
        public UpgradesViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            nameView = v.findViewById(R.id.upgrade_name);
            costView = v.findViewById(R.id.upgrade_cost);
            incomeView = v.findViewById(R.id.upgrade_income);
            pollutionView = v.findViewById(R.id.upgrade_pollution);
            mListener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            mListener.onClick(view, getAdapterPosition());
        }
    }

    @Override
    public int getItemCount() {
        return buildings.length;
    }

    @Override
    public UpgradesAdapter.UpgradesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upgrade_cell, parent, false);
        UpgradesViewHolder vh = new UpgradesViewHolder(v, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(UpgradesViewHolder holder, int position) {
        holder.nameView.setText(buildings[position].getName());
        holder.costView.setText("Cost: $" + buildings[position].getCost());
        holder.pollutionView.setText("Pollution: " + buildings[position].getPollutionOutput());
        holder.incomeView.setText("Income: $" + buildings[position].getIncomeOutput());
    }




}
