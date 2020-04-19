package com.ljuv.fibonacciapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.ljuv.fibonacciapp.utils.FibonacciNumbersCalculator;
import com.ljuv.fibonacciapp.R;
import com.ljuv.fibonacciapp.lists.RecyclerViewFibonacciAdapter;

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
        //int iterations = 100;
        ArrayList<Long> fibList = FibonacciNumbersCalculator.getNums(nums);
        RecyclerViewFibonacciAdapter adapter = new RecyclerViewFibonacciAdapter(fibList);
        recyclerView.setAdapter(adapter);
    }

}
