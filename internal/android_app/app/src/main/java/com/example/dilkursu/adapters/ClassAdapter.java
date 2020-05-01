package com.example.dilkursu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dilkursu.R;
import com.example.dilkursu.models.Classroom;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Classroom> classrooms;

    public ClassAdapter(Context context, ArrayList<Classroom> classrooms) {
        this.context = context;
        this.classrooms = classrooms;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_classes_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classroom classroom = classrooms.get(position);

        holder.className.setText(classroom.getName());
        holder.capacity.setText("capacity: " + Integer.toString(classroom.getCapacity()));


    }

    @Override
    public int getItemCount() {
        return classrooms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView className;
        private TextView capacity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            className = (TextView) itemView.findViewById(R.id.ListClasses_Row_TextView_ClassName);
            capacity = (TextView) itemView.findViewById(R.id.ListClasses_Row_TextView_ClassCapacity);

        }
    }
}
