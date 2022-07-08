package com.example.medicore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicore.database.DBHelper;

public class CreateObatActivity extends AppCompatActivity{

    DBHelper db;

    EditText etNama, etDeskripsi;
    Spinner spinJenis, spinQty;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_obat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Create Obat");
        db = new DBHelper(getApplicationContext());

        initWidgets();

        btnAdd.setOnClickListener(view -> {
            String nama = etNama.getText().toString().trim();
            String deskripsi = etDeskripsi.getText().toString().trim();
            String jenis = spinJenis.getSelectedItem().toString();
            int qty = Integer.parseInt(spinQty.getSelectedItem().toString());

            if (!nama.equals("") && !deskripsi.equals("")) {
                db.insertData(nama, deskripsi, jenis, qty);
                etNama.setText("");
                etDeskripsi.setText("");
                spinJenis.setSelection(0);
                spinQty.setSelection(0);
                Intent i = new Intent(CreateObatActivity.this, ViewObatActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(CreateObatActivity.this, "Nama Obat dan Deskripsi harus diisi!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initWidgets() {
        etNama = findViewById(R.id.createObat_et_nama);
        etDeskripsi = findViewById(R.id.createObat_et_deskripsi);
        btnAdd = findViewById(R.id.createObat_btn_add);

        spinJenis = findViewById(R.id.createObat_spinner_jenis);
        ArrayAdapter<CharSequence> jenisAdapter = ArrayAdapter.createFromResource(this, R.array.obat_jenis, android.R.layout.simple_spinner_item);
        jenisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJenis.setAdapter(jenisAdapter);

        spinQty = findViewById(R.id.createObat_spinner_qty);
        ArrayAdapter<CharSequence> qtyAdapter = ArrayAdapter.createFromResource(this, R.array.obat_qty, android.R.layout.simple_spinner_item);
        jenisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinQty.setAdapter(qtyAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int choice = item.getItemId();

        switch (choice) {

            case android.R.id.home:
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}