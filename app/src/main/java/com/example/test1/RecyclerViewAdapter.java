package com.example.test1;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Cursor input;


    public RecyclerViewAdapter(Cursor input) {
        this.input = input;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_component, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        //The int position serves to get the row in which we want to take the information from
        //And input.getInt/getString serve to get the information out from the columns, starting from 0 to 4 (5 columns total)

        input.moveToPosition(position);
        holder.itemView.setTag(input.getInt(0));
        holder.opp_name.setText(input.getString(1));
        holder.draftside.setText(input.getString(2));
        holder.rating.setText(input.getString(3));
        holder.result.setText(input.getString(4));
    }

    @Override
    public int getItemCount() {
        return input.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView opp_name, draftside, rating, result;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            opp_name = itemView.findViewById(R.id.opp_name_list_text);
            draftside = itemView.findViewById(R.id.draftside_list_text);
            rating = itemView.findViewById(R.id.rating_list_text);
            result = itemView.findViewById(R.id.result_list_text);
        }
    }
}
