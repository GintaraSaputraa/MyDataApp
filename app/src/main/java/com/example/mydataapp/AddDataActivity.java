package com.example.mydataapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddDataActivity extends AppCompatActivity {

    EditText etNim, etNama, etProdi, etKelas, etAlamat, etEmail;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etProdi = findViewById(R.id.etProdi);
        etKelas = findViewById(R.id.etKelas);
        etAlamat = findViewById(R.id.etAlamat);
        etEmail = findViewById(R.id.etEmail);

        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(v -> {

            if (etNim.getText().toString().isEmpty() ||
                    etNama.getText().toString().isEmpty() ||
                    etProdi.getText().toString().isEmpty() ||
                    etKelas.getText().toString().isEmpty() ||
                    etAlamat.getText().toString().isEmpty() ||
                    etEmail.getText().toString().isEmpty()) {

                Toast.makeText(this, "Isi semua data!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent();
            intent.putExtra("nim", etNim.getText().toString());
            intent.putExtra("nama", etNama.getText().toString());
            intent.putExtra("prodi", etProdi.getText().toString());
            intent.putExtra("kelas", etKelas.getText().toString());
            intent.putExtra("alamat", etAlamat.getText().toString());
            intent.putExtra("email", etEmail.getText().toString());

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}