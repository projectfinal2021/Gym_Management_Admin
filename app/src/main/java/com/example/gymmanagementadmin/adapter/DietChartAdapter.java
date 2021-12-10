package com.example.gymmanagementadmin.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymmanagementadmin.R;

import com.example.gymmanagementadmin.interfaces.OnDietClickListener;
import com.example.gymmanagementadmin.model.DietChartInfo;

import java.util.ArrayList;

public class DietChartAdapter extends RecyclerView.Adapter<DietChartAdapter.DietChartViewHolder> {
    private static final String TAG = "DietChartAdapter";
    private ArrayList<DietChartInfo> dietChartInfos;
    private OnDietClickListener onDietClickListener;
    public DietChartAdapter(ArrayList<DietChartInfo> dietChartInfos, OnDietClickListener onDietClickListener) {
        this.dietChartInfos = dietChartInfos;
        this.onDietClickListener=onDietClickListener;
    }

    @NonNull
    @Override
    public DietChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_diet_chart, parent, false);
        return new DietChartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DietChartViewHolder holder, int position) {
        DietChartInfo dietChartInfo = dietChartInfos.get(position);
        int pos=position;
        Log.d(TAG, "onBindViewHolder: ");
        holder.dietChartName.setText(dietChartInfo.getDietChartName());
        holder.dietChartTime.setText(dietChartInfo.getDietChartTime());
        holder.bodyTypes.setText(dietChartInfo.getBodyType());

        holder.deleteDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDietClickListener.onDeleteClickListener(dietChartInfo, dietChartInfo.getDietKey());
            }
        });

        holder.editDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDietClickListener.onDietEditClickListener(dietChartInfo, dietChartInfo.getDietKey());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dietChartInfos.size();
    }


    public class DietChartViewHolder extends RecyclerView.ViewHolder {
        private TextView dietChartName, dietChartTime, bodyTypes;
        private ImageButton editDiet, deleteDiet;

        public DietChartViewHolder(@NonNull View itemView) {
            super(itemView);
            dietChartName = itemView.findViewById(R.id.textview_dietChartLayout_dietChart);
            dietChartTime = itemView.findViewById(R.id.textview_dietChartLayout_time);
            bodyTypes = itemView.findViewById(R.id.textview_exerciseLayout_bodyTypes);
            editDiet = itemView.findViewById(R.id.imageButton_dietChartLayout_edit);
            deleteDiet = itemView.findViewById(R.id.imageButton_dietChartLayout_delete);
        }
    }
}
