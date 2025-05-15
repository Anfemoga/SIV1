package com.example.siv1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv_registrar;
    EditText etcard, etpassword;
    Button btn_log, btn_SReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tv_registrar = findViewById(R.id.tv_registrar);
        etcard = findViewById(R.id.etCard);
        etpassword = findViewById(R.id.etPassword);

        btn_log = findViewById(R.id.btn_inicio_sesion);
        btn_SReg = findViewById(R.id.btn_sin_registro);


        tv_registrar.setOnClickListener(v -> {
            Intent intentReg = new Intent(MainActivity.this, MainActivity2.class);
            MainActivity.this.startActivity(intentReg);
        });
        btn_SReg.setOnClickListener(v -> {
            Intent intentSReg = new Intent(MainActivity.this, MainActivity5.class);
            MainActivity.this.startActivity(intentSReg);
        });

        btn_log.setOnClickListener(v -> {

            final String card = etcard.getText().toString();
            final String password = etpassword.getText().toString();

            Response.Listener<String> responseListener = response -> {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success){
                        String name = jsonResponse.getString("name");
                        String phone = jsonResponse.getString("phone");

                        Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                        intent.putExtra("name", name);
                        intent.putExtra("card", card);
                        intent.putExtra("phone", phone);
                        intent.putExtra("password", password);

                        MainActivity.this.startActivity(intent);


                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("error de login")
                                .setNegativeButton("Retry", null)
                                .create().show();

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }   ;

            LoginRequest loginRequest = new LoginRequest(card, password, responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(loginRequest);


        });

    }
}