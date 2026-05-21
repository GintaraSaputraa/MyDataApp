package com.example.mydataapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    String USER = "admin";
    String PASS = "admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            String u = etUsername.getText().toString().trim();
            String p = etPassword.getText().toString().trim();

            if (u.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Isi semua data!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (u.equals(USER) && p.equals(PASS)) {

                SharedPreferences.Editor editor =
                        getSharedPreferences("login", MODE_PRIVATE).edit();

                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                startActivity(new Intent(this, DashboardActivity.class));
                finish();

            } else {
                Toast.makeText(this, "Login gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}