package com.ljuv.fibonacciapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ljuv.fibonacciapp.utils.FibonacciNumbersCalculator;
import com.ljuv.fibonacciapp.R;
import com.ljuv.fibonacciapp.lists.RecyclerViewFibonacciAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FibonacciListActivity extends AppCompatActivity {
    TextView loadingView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_list);

        final Database database = new Database(this);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FibonacciAdapter(this));

        try {
            int count = getIntent().getIntExtra("nums", 1);
            List<Long> fibonacci = database.get(count);
            if(fibonacci == null) {
                Log.d("Fibonacci", "new Calc.");
                fibonacci = FibonacciNumbersCalculator.getNums(count);
                database.put(count, fibonacci);
            } else {
                Log.d("Fibonacci", "DB Found.");
            }
            recyclerView.setAdapter(new FibonacciAdapter(FibonacciListActivity.this, fibonacci));
        } catch(NumberFormatException e) {
            Toast.makeText(FibonacciListActivity.this, e.getMessage(), Toast.LENGTH_LONG);
        }


        //int iterations = 100;
        /*
        ArrayList<Long> fibList = FibonacciNumbersCalculator.getNums(nums);
        RecyclerViewFibonacciAdapter adapter = new RecyclerViewFibonacciAdapter(fibList);
        recyclerView.setAdapter(adapter);
         */
    }

    static class Database {
        private static String KEY = "fibonacciMap";
        private SharedPreferences sharedPreferences;
        private Map<Integer,List<Long>> map = new HashMap<>();
        Database(Context context) {
            sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
            String json = sharedPreferences.getString(KEY, null);
            if(json != null) {
                Gson gson = new Gson();
                map = gson.fromJson(json, new TypeToken<HashMap<Integer,List<Long>>>(){}.getType());
            }
        }
        void put(int count, List<Long> fibonacci) {
            map.put(count, fibonacci);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            Gson gson = new Gson();
            edit.putString(KEY, gson.toJson(map));
            edit.commit();
        }
        List<Long> get(int count) {
            return map.get(count);
        }
    }

    static class FibonacciAdapter extends RecyclerView.Adapter {
        private static class FibonacciViewHolder extends RecyclerView.ViewHolder {
            private TextView text1;
            public FibonacciViewHolder(@NonNull View itemView) {
                super(itemView);
                text1 = itemView.findViewById(android.R.id.text1);
            }
        }
        private Context context;
        private List<Long> list = Collections.emptyList();

        FibonacciAdapter(Context context) {
            this.context = context;
        }
        FibonacciAdapter(Context context, List<Long> fibonacci) {
            this.context = context;
            list = fibonacci;
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new FibonacciViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((FibonacciViewHolder)holder).text1.setText((position+1)+". "+list.get(position));
        }
        @Override
        public int getItemCount() {
            return list.size();
        }
    }

}
