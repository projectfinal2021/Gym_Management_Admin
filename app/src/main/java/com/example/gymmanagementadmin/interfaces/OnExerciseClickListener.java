package com.example.gymmanagementadmin.interfaces;

import com.example.gymmanagementadmin.model.DietChartInfo;
import com.example.gymmanagementadmin.model.ExerciseInfo;

public interface OnExerciseClickListener {
    void onExerciseDeleteClickListener(ExerciseInfo exerciseInfo, String key);

    void onExerciseDietEditClickListener(ExerciseInfo exerciseInfo, String key);
}
