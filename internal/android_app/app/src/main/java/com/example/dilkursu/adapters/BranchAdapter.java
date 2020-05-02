package com.example.dilkursu.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dilkursu.R;
import com.example.dilkursu.models.Branch;
import com.example.dilkursu.views.registrar.BranchInfoActivity;

import java.util.ArrayList;

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Branch> branches;

    public BranchAdapter(Context context, ArrayList<Branch> branches) {
        this.context = context;
        this.branches = branches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_branches_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Branch branch = branches.get(position);
        holder.textView.setText(branch.getName());
        holder.branch = branch;
    }

    @Override
    public int getItemCount() {
        return branches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;
        private CardView cardView;
        private Branch branch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.ListBranches_Row_TextView_BranchName);
            cardView = (CardView) itemView.findViewById(R.id.ListBranches_Row_CradView);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, BranchInfoActivity.class);
            intent.putExtra("name", branch.getName());
            intent.putExtra("transport", branch.getPublicTransports().get(0));
            intent.putExtra("address", branch.getAddress());
            intent.putExtra("facilities", branch.getFacilities().get(0));
            context.startActivity(intent);

        }
    }
}
