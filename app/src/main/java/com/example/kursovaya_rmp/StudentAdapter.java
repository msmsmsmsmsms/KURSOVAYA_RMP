package com.example.kursovaya_rmp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public final class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Day> days;

    public StudentAdapter(){}
    public StudentAdapter(ArrayList<Day> days) {
        this.days = days;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.student_item, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(!days.isEmpty()) {
            TextView date = holder.itemView.findViewById(R.id.student_date);
            date.setText(this.days.get(position).getDate());
            TextView mark = holder.itemView.findViewById(R.id.student_mark);
            mark.setText(this.days.get(position).getMark());
        }
    }

    @Override
    public int getItemCount() {
        return this.days.size();
    }
}