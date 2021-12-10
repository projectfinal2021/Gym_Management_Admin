package com.example.gymmanagementadmin.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymmanagementadmin.R;
import com.example.gymmanagementadmin.interfaces.OnExerciseClickListener;
import com.example.gymmanagementadmin.model.ExerciseInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private static final String TAG = "ExerciseAdapter";
    private ArrayList<ExerciseInfo> exerciseInfos;
    private OnExerciseClickListener onExerciseClickListener;

    public ExerciseAdapter(ArrayList<ExerciseInfo> exerciseInfos,OnExerciseClickListener onExerciseClickListener) {
        this.exerciseInfos = exerciseInfos;
        this.onExerciseClickListener=onExerciseClickListener;
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
        holder.reps.setText(exerciseInfo.getBodyType());
        holder.set.setText(exerciseInfo.getSet());
        holder.deleteDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onExerciseClickListener.onExerciseDeleteClickListener(exerciseInfo, exerciseInfo.getExerciseKey());
            }
        });

        holder.editDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onExerciseClickListener.onExerciseDietEditClickListener(exerciseInfo, exerciseInfo.getExerciseKey());
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseInfos.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView exerciseName, reps, set;
        private ImageView imageLink1;
        private ImageButton editDiet, deleteDiet;
        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.textview_exerciseLayout_exerciseName);
            imageLink1 = itemView.findViewById(R.id.imageview_exerciseLayout_exerciseImage);
            reps = itemView.findViewById(R.id.textview_exerciseLayout_bodyTypes);
            set = itemView.findViewById(R.id.textview_exerciseLayout_set);
            editDiet = itemView.findViewById(R.id.imageButton_exerciseLayout_edit);
            deleteDiet = itemView.findViewById(R.id.imageButton_exerciseLayout_delete);
        }
    }
}
