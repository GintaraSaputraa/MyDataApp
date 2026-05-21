package com.example.mydataapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    TextView tvWelcome;
    Button btnTambah, btnLogout;
    ListView listViewData;

    ArrayList<DataModel> dataList;
    DataAdapter adapter;

    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_dashboard);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnTambah = findViewById(R.id.btnTambah);
        btnLogout = findViewById(R.id.btnLogout);
        listViewData = findViewById(R.id.listViewData);

        tvWelcome.setText("Selamat Datang Admin");

        dataList = new ArrayList<>();
        adapter = new DataAdapter(this, dataList);
        listViewData.setAdapter(adapter);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                        Intent data = result.getData();

                        dataList.add(new DataModel(
                                data.getStringExtra("nim"),
                                data.getStringExtra("nama"),
                                data.getStringExtra("prodi"),
                                data.getStringExtra("kelas"),
                                data.getStringExtra("alamat"),
                                data.getStringExtra("email")
                        ));

                        adapter.notifyDataSetChanged();
                    }
                }
        );

        btnTambah.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddDataActivity.class);
            launcher.launch(intent);
        });

        btnLogout.setOnClickListener(v -> {

            SharedPreferences.Editor editor =
                    getSharedPreferences("login", MODE_PRIVATE).edit();

            editor.clear();
            editor.apply();

            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}