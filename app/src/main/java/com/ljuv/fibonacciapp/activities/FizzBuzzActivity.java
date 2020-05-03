package com.ljuv.fibonacciapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.protobuf.StringValue;
import com.ljuv.fibonacciapp.R;
import com.ljuv.fibonacciapp.lists.RecyclerViewFizzBuzzAdapter;
import com.ljuv.fibonacciapp.utils.FibonacciNumbersCalculator;
import com.ljuv.fibonacciapp.utils.FizzBuzzList;
import com.ljuv.fibonacciapp.utils.FizzBuzzObject;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FizzBuzzActivity extends AppCompatActivity {
    RecyclerView recyclerViewFizzBuzz;
    ArrayList<Long> fizzBuzzList;
    ArrayList<Long> fiboList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fizz_buzz);
        fizzBuzzList = FibonacciNumbersCalculator.getNums(getIntent().getIntExtra("nums", 1));
        checkSharedPref();
    }


    public void saveSharedPreferences() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyDB", 0);
        SharedPreferences.Editor editor = pref.edit();
        int nums = getIntent().getIntExtra("nums", 1);
        Gson gson = new Gson();

        List<Long> sharedPrefList = new ArrayList<>();

        sharedPrefList.addAll(fizzBuzzList);

        String jsonText = gson.toJson(sharedPrefList);

        editor.putString(String.valueOf(nums), jsonText);

        editor.commit();

    }

    public void retrieveSharedPref() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyDB", 0);
        SharedPreferences.Editor editor = pref.edit();
        int nums = getIntent().getIntExtra("nums", 1);
        Gson gson = new Gson();

        String jsonText = pref.getString(String.valueOf(nums), null);

        fiboList = gson.fromJson(jsonText, ArrayList.class);
        setupRecyclerView();
    }

    public void checkSharedPref() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyDB", 0);
        SharedPreferences.Editor editor = pref.edit();
        int nums = getIntent().getIntExtra("nums", 1);
        if (pref.contains(String.valueOf(nums))) {
            retrieveSharedPref();
            Toast toast = Toast.makeText(getApplicationContext(), "Number Found", Toast.LENGTH_SHORT);
            toast.setMargin(50, 50);
            toast.show();

        } else {
            saveSharedPreferences();
            retrieveSharedPref();
            Toast toast = Toast.makeText(getApplicationContext(), "Number Saved", Toast.LENGTH_SHORT);
            toast.setMargin(50, 50);
            toast.show();
        }
    }


    private void setupRecyclerView() {
        recyclerViewFizzBuzz = findViewById(R.id.recyclerViewFizzBuzz);
        recyclerViewFizzBuzz.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewFizzBuzzAdapter adapter = new RecyclerViewFizzBuzzAdapter(fiboList);
        recyclerViewFizzBuzz.setAdapter(adapter);
    }

    private void recordNewListToFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FizzBuzzObject fizzBuzzObject = new FizzBuzzObject();
        //fizzBuzzObject.setFizzBuzzList(fizzBuzzList);
        fizzBuzzObject.setCreated(new Date().toString());
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("Date", "Test datas");
        db.collection("/fizzBuzz").document().set(fizzBuzzObject);
    }
}
