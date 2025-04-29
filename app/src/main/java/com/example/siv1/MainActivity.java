package com.example.siv1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tv_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv_registrar = findViewById(R.id.tv_registrar);

        tv_registrar.setOnClickListener(v -> {
            Intent intentReg = new Intent(MainActivity.this, MainActivity2.class);
            MainActivity.this.startActivity(intentReg);

        });

    }
}