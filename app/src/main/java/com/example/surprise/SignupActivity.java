package com.example.surprise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.surprise.API.ApiClient;
import com.example.surprise.API.ApiResponse;
import com.example.surprise.LoginActivity;
import com.example.surprise.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    private EditText etId, etName, etPhone, etEmail, etPassword;
    private Spinner spinnerRole, spinnerStatus;
    private Button btnSignup;
    private TextView tvLoginRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etId = findViewById(R.id.et_signup_id);
        etName = findViewById(R.id.et_signup_name);
        etPhone = findViewById(R.id.et_signup_phone);
        etEmail = findViewById(R.id.et_signup_email);
        etPassword = findViewById(R.id.et_signup_password);
        spinnerRole = findViewById(R.id.spinner_role);
        spinnerStatus = findViewById(R.id.spinner_status);
        btnSignup = findViewById(R.id.btn_signup);
        tvLoginRedirect = findViewById(R.id.tv_login_redirect);

        ArrayAdapter<CharSequence> roleAdapter = ArrayAdapter.createFromResource(this,
                R.array.roles_array, android.R.layout.simple_spinner_item);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(roleAdapter);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(statusAdapter);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        tvLoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser() {
        String id = etId.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String role = spinnerRole.getSelectedItem().toString();
        String status = spinnerStatus.getSelectedItem().toString();

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("id", id);
            jsonBody.put("nombre", name);
            jsonBody.put("telefono", phone);
            jsonBody.put("correo", email);
            jsonBody.put("contrasena", password);
            jsonBody.put("rol", role);
            jsonBody.put("estado", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "https://surprisebackend.onrender.com/usuario/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ApiResponse apiResponse = gson.fromJson(response.toString(), ApiResponse.class);
                        if (!apiResponse.isError()) {
                            Toast.makeText(SignupActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            Toast.makeText(SignupActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
                    }
                });

        ApiClient.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}