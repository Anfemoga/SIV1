package com.example.siv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    EditText etId, etCard, etName, etPhone, etPassword;
    Button btnCreate, btnFetch;

    RequestQueue requestQueue;

    private static final String URL1 = "http://192.168.20.26/android/save.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        requestQueue = Volley.newRequestQueue(this);
        //UI
        initUI();

        btnCreate.setOnClickListener(this);
        btnFetch.setOnClickListener(this);
    }

    private void initUI(){
        //EditText
        etCard = findViewById(R.id.etCard);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etId = findViewById(R.id.etId);

        //Buttons
        btnCreate = findViewById(R.id.btnCreate);
        btnFetch = findViewById(R.id.btnFetch);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnCreate){
            String card = etCard.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            createUser(card, name, phone, password);

        }else if (id == R.id.btnFetch){

            Intent intent = new Intent(this, MainActivity3.class);
            intent.putExtra("id", etId.getText().toString().trim());
            startActivity(intent);
        }

    }

    private void createUser(final String card, final String name, final String phone, final String password) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1,
                response -> Toast.makeText(MainActivity2.this, "Correct", Toast.LENGTH_SHORT).show(),
                error -> {

                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("card", card);
                params.put("name", name);
                params.put("phone", phone);
                params.put("password", password);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}