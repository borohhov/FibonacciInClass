package com.example.fibonacciapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class FibonacciListActivity extends AppCompatActivity {
    TextView loadingView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_list);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int nums = getIntent().getIntExtra("nums", 1);
        int iterations = 100;
        ArrayList<Long> fibList = FibonacciNumbersCalculator.getNums(nums);
        RecyclerViewFibonacciAdapter adapter = new RecyclerViewFibonacciAdapter(fibList);
        recyclerView.setAdapter(adapter);
    }

}
