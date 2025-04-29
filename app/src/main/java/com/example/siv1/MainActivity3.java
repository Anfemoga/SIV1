package com.example.siv1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;



import com.android.volley.Request;
import com.android.volley.RequestQueue;



import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;


public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    Button btnDelete, btnEdit;
    EditText etCard, etId, etName, etPhone, etPassword;
    String id;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        requestQueue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            id = extras.getString("id");

        }

        //UI
        initUI();

        readUser();

        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void initUI() {
        //EditText
        etCard = findViewById(R.id.etCard);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etId = findViewById(R.id.etId);


        //Buttons
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);

    }

    private void readUser(){
        String URL = "http://192.168.20.26/android/fetch.php?id=" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                response -> {
                    String card, name, phone, password;
                    try {
                        card = response.getString("card");
                        name = response.getString("name");
                        phone = response.getString("phone");
                        password = response.getString("password");

                        etCard.setText(card);
                        etName.setText(name);
                        etPhone.setText(phone);
                        etPassword.setText(password);


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                },
                error -> {

                }

        );

        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onClick (View v){
        int id = v.getId();

        if (id == R.id.btnEdit){

            String card = etCard.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            updateUser (card, name, phone, password);

        }else if (id == R.id.btnDelete){
            String idUser = etId.getText().toString().trim();

            removeUser (idUser);

        }

    }

    private void updateUser(final String card, final String name, final String phone, final String password){

        String URL = "http://192.168.20.26/android/edit.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                response -> Toast.makeText(MainActivity3.this, "Updated Successfully!!!", Toast.LENGTH_LONG).show(),
                error -> {

                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("card", card);
                params.put("name", name);
                params.put("phone", phone);
                params.put("password", password);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void removeUser(final String idUser) {
        String URL = "http://192.168.20.26/android/delete.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                response -> finish(),
                error -> {

                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", idUser);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}