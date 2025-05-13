package com.example.siv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity4 extends AppCompatActivity {
private ConstraintLayout straBtn;

    TextView tvCedula, tvNombre, tvTelefono, tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);

        tvCedula = findViewById(R.id.tvCard);
        tvNombre = findViewById(R.id.tvName);
        tvTelefono = findViewById(R.id.tvPhone);
        tvPassword = findViewById(R.id.tvPassword);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String card = intent.getStringExtra("card");
        String telefono = intent.getStringExtra("phone");
        String password = intent.getStringExtra("password");


        tvCedula.setText(card);
        tvNombre.setText(name);
        tvTelefono.setText(telefono);
        tvPassword.setText(password);

        straBtn=findViewById(R.id.starBtn);
        straBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity4.this, MainActivity5.class));
            }
        });
    }
}