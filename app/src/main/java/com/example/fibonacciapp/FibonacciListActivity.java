package com.example.fibonacciapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FibonacciListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int nums = getIntent().getIntExtra("nums", 1);
        ArrayList<Long> list = FibonacciNumbersCalculator.getNums(nums);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerViewFibonacciAdapter adapter = new RecyclerViewFibonacciAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
