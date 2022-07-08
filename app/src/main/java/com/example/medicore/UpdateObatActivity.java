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

import com.example.medicore.database.DBHelper;
import com.example.medicore.object.Obat;

public class UpdateObatActivity extends AppCompatActivity {

    EditText etNama, etDeskripsi;
    Spinner spinJenis, spinQty;
    Button btnUpdate;
    DBHelper db;
    Obat obat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_obat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Update Obat");

        initWidgets();

        obat = getIntent().getParcelableExtra("Obat");
        etNama.setText(obat.getNama());
        etDeskripsi.setText(obat.getDeskripsi());
        setSelectedJenis();
        setSelectedQty();


        btnUpdate.setOnClickListener(view -> {
            String nama = etNama.getText().toString().trim();
            String deskripsi = etDeskripsi.getText().toString().trim();
            String jenis = spinJenis.getSelectedItem().toString();
            int qty = Integer.parseInt(spinQty.getSelectedItem().toString());

            db.updateData(obat.getId(), nama, deskripsi, jenis, qty);

            Intent i = new Intent(UpdateObatActivity.this, ViewObatActivity.class);
            startActivity(i);
        });
    }

    private void initWidgets() {
        db = new DBHelper(this);
        etNama = findViewById(R.id.updateObat_et_nama);
        etDeskripsi = findViewById(R.id.updateObat_et_deskripsi);
        btnUpdate = findViewById(R.id.updateObat_btn_update);

        spinJenis = findViewById(R.id.updateObat_spinner_jenis);
        ArrayAdapter<CharSequence> jenisAdapter = ArrayAdapter.createFromResource(this, R.array.obat_jenis, android.R.layout.simple_spinner_item);
        jenisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJenis.setAdapter(jenisAdapter);

        spinQty = findViewById(R.id.updateObat_spinner_qty);
        ArrayAdapter<CharSequence> qtyAdapter = ArrayAdapter.createFromResource(this, R.array.obat_qty, android.R.layout.simple_spinner_item);
        jenisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinQty.setAdapter(qtyAdapter);
    }

    private void setSelectedJenis() {
        String jenis = obat.getJenis();

        if(jenis.equals("Obat Cair")) {
            spinJenis.setSelection(0);
        } else if(jenis.equals("Tablet")) {
            spinJenis.setSelection(1);
        } else if(jenis.equals("Kapsul")) {
            spinJenis.setSelection(2);
        } else if(jenis.equals("Obat Oles")) {
            spinJenis.setSelection(3);
        } else if(jenis.equals("Obat Tetes")) {
            spinJenis.setSelection(4);
        }

    }

    private void setSelectedQty() {
        int qty = obat.getQty();

        if(qty==1) {
            spinQty.setSelection(0);
        } else if(qty==2) {
            spinQty.setSelection(1);
        } else if(qty==3) {
            spinQty.setSelection(2);
        } else if(qty==4) {
            spinQty.setSelection(3);
        } else if(qty==5) {
            spinQty.setSelection(4);
        } else if(qty==6) {
            spinQty.setSelection(5);
        } else if(qty==7) {
            spinQty.setSelection(6);
        } else if(qty==8) {
            spinQty.setSelection(7);
        } else if(qty==9) {
            spinQty.setSelection(8);
        } else if(qty==10) {
            spinQty.setSelection(9);
        }

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