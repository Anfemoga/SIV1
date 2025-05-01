package com.example.siv1;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL="http://192.168.20.26/android/login.php";
    private final Map<String, String> params;
    public LoginRequest ( String card, String password, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("card",card);
        params.put("password",password);
    }

    @Nullable
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
