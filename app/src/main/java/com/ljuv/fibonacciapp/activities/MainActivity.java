package com.ljuv.fibonacciapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ljuv.fibonacciapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button okButton = findViewById(R.id.button_ok);
        final EditText fibonacciSeq = findViewById(R.id.tv_fibonacci_sequence);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fibonacciSeq.getText().toString().isEmpty()) {
                    Intent intent = new Intent(context, FizzBuzzActivity.class).putExtra("nums", Integer.parseInt( fibonacciSeq.getText().toString()) );
                    startActivity(intent);
                }
            }
        });

    }
}
