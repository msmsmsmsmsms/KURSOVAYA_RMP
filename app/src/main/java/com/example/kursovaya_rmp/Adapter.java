package com.example.kursovaya_rmp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public final class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final ArrayList<Student> students;

    public Adapter(ArrayList<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView name = holder.itemView.findViewById(R.id.textView_name);
        name.setText(this.students.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.students.size();
    }
}

