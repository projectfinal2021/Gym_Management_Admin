package com.example.gymmanagementadmin.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymmanagementadmin.R;
import com.example.gymmanagementadmin.model.ExerciseInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private static final String TAG = "ExerciseAdapter";
    private ArrayList<ExerciseInfo> exerciseInfos;

    public ExerciseAdapter(ArrayList<ExerciseInfo> exerciseInfos) {
        this.exerciseInfos = exerciseInfos;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_exercise, parent, false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        ExerciseInfo exerciseInfo = exerciseInfos.get(position);
        Log.d(TAG, "onBindViewHolder: ");
        holder.exerciseName.setText(exerciseInfo.getExerciseName());
        Picasso.get().load(exerciseInfo.getImageLink1()).into(holder.imageLink1);
        holder.reps.setText(exerciseInfo.getReps());
        holder.set.setText(exerciseInfo.getSet());

    }

    @Override
    public int getItemCount() {
        return exerciseInfos.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView exerciseName, reps, set;
        private ImageView imageLink1;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.textview_exerciseLayout_exerciseName);
            imageLink1 = itemView.findViewById(R.id.imageview_exerciseLayout_exerciseImage);
            reps = itemView.findViewById(R.id.textview_exerciseLayout_reps);
            set = itemView.findViewById(R.id.textview_exerciseLayout_set);
        }
    }
}
