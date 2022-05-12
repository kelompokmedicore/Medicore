package com.example.mad_utsnomor3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mad_utsnomor3.adapter.ObatAdapter;
import com.example.mad_utsnomor3.database.DBHelper;
import com.example.mad_utsnomor3.object.Obat;
import com.example.mad_utsnomor3.object.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewObatActivity extends AppCompatActivity{

    DBHelper db;
    ArrayList<Obat> obatList;
    ListView lvObat;
    ObatAdapter adapter;
    TextView tvNoObat;
    FloatingActionButton fabCreateObat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_obat);
        db = new DBHelper(this);

        obatList = new ArrayList<>();
        initWidgets();
        loadAllData();

        if(!obatList.isEmpty()) {
            tvNoObat.setVisibility(View.INVISIBLE);
        } else {
            tvNoObat.setVisibility(View.VISIBLE);
        }

        fabCreateObat.setOnClickListener(view -> {
            Intent i = new Intent(ViewObatActivity.this, CreateObatActivity.class);
            startActivity(i);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int chosenItem = item.getItemId();

        switch(chosenItem) {

            case R.id.menu_home:
                Intent intent1 = new Intent(ViewObatActivity.this, ViewObatActivity.class);
                startActivity(intent1);
                return true;
            case R.id.menu_viewProfile:
                Intent intent2 = new Intent(ViewObatActivity.this, ViewProfileActivity.class);
                startActivity(intent2);
                return true;
            case R.id.menu_logout:
                Intent intent3 = new Intent(ViewObatActivity.this, LandingActivity.class);
                startActivity(intent3);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initWidgets(){
        tvNoObat = findViewById(R.id.viewObat_tv_noObat);
        lvObat = findViewById(R.id.viewObat_lv_obatList);
        fabCreateObat = findViewById(R.id.viewObat_fab_createObat);
    }

    private void loadAllData() {
        obatList = db.getObatData();
        adapter = new ObatAdapter(this, obatList, db);
        lvObat.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}