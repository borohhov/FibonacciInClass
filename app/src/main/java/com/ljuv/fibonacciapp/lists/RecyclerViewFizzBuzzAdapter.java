package com.ljuv.fibonacciapp.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ljuv.fibonacciapp.R;

import java.util.ArrayList;

public class RecyclerViewFizzBuzzAdapter extends RecyclerView.Adapter<RecyclerViewFizzBuzzAdapter.FizzBuzzViewHolder> {

    private ArrayList<String> list;

    public RecyclerViewFizzBuzzAdapter(ArrayList<String> fizzBuzzList) {
        this.list = fizzBuzzList;
    }

    @NonNull
    @Override
    public RecyclerViewFizzBuzzAdapter.FizzBuzzViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Context context;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View lineView = inflater.inflate(R.layout.recycler_row, parent, false);
        return new RecyclerViewFizzBuzzAdapter.FizzBuzzViewHolder(lineView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewFizzBuzzAdapter.FizzBuzzViewHolder holder, int position) {
        holder.lineText.setText("" + (position + 1) + ". " + list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class FizzBuzzViewHolder extends RecyclerView.ViewHolder {
        TextView lineText;
        public FizzBuzzViewHolder(@NonNull View itemView) {
            super(itemView);
            this.lineText = itemView.findViewById(R.id.tv_line_recycler);
        }
    }

}
