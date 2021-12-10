package com.example.gymmanagementadmin.interfaces;

import com.example.gymmanagementadmin.model.DietChartInfo;

public interface OnDietClickListener {
    void onDeleteClickListener(DietChartInfo dietChartInfo, String key);

    void onDietEditClickListener(DietChartInfo dietChartInfo, String key);
}
