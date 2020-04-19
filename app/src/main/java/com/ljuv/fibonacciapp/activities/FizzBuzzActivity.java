package com.ljuv.fibonacciapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ljuv.fibonacciapp.R;
import com.ljuv.fibonacciapp.lists.RecyclerViewFizzBuzzAdapter;
import com.ljuv.fibonacciapp.utils.FizzBuzzList;
import com.ljuv.fibonacciapp.utils.FizzBuzzObject;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FizzBuzzActivity extends AppCompatActivity {
    RecyclerView recyclerViewFizzBuzz;
    ArrayList<String> fizzBuzzList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fizz_buzz);
        fizzBuzzList = FizzBuzzList.getFizzBuzzList(getIntent().getIntExtra("nums", 1));
        setupRecyclerView();
        recordNewListToFirestore();

    }

    private void setupRecyclerView() {
        recyclerViewFizzBuzz = findViewById(R.id.recyclerViewFizzBuzz);
        recyclerViewFizzBuzz.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewFizzBuzzAdapter adapter = new RecyclerViewFizzBuzzAdapter(fizzBuzzList);
        recyclerViewFizzBuzz.setAdapter(adapter);
    }

    private void recordNewListToFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FizzBuzzObject fizzBuzzObject = new FizzBuzzObject();
        fizzBuzzObject.setFizzBuzzList(fizzBuzzList);
        fizzBuzzObject.setCreated(new Date().toString());
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("Date", "Test datas");
        db.collection("/fizzBuzz").document().set(fizzBuzzObject);
       // db.collection("fizzBuzzCollection").document("tests").set(testMap);
    }
}
