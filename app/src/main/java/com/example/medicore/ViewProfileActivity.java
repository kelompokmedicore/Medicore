package com.example.medicore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.medicore.object.User;

public class ViewProfileActivity extends AppCompatActivity {

    TextView tvNama, tvEmail, tvGender, tvUmur;
    EditText etPassword;
    Switch swShowPass;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Profile");
        initWidgets();

        //Get Value from SharedPreferences
        SharedPreferences sp = this.getSharedPreferences("LoggedUser", MODE_PRIVATE);
        int id =sp.getInt("Id", 0);
        String nama = sp.getString("Nama", null);
        String email = sp.getString("Email", null);
        String gender = sp.getString("Gender", null);
        int umur= sp.getInt("Umur", 0);
        String password = sp.getString("Password", null);

        user = new User(id, nama, email, password, gender, umur);
        tvNama.setText(user.getNama());
        tvEmail.setText(user.getEmail());
        tvGender.setText(user.getGender());
        tvUmur.setText(user.getUmur()+"");
        etPassword.setText(user.getPassword());

        swShowPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    etPassword.setTransformationMethod(null);
                } else {
                    etPassword.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
    }

    private void initWidgets() {
        tvNama = findViewById(R.id.profile_tv_nama);
        tvEmail = findViewById(R.id.profile_tv_email);
        tvGender = findViewById(R.id.profile_tv_gender);
        tvUmur = findViewById(R.id.profile_tv_umur);
        etPassword = findViewById(R.id.profile_et_password);
        etPassword.setEnabled(false);
        swShowPass = findViewById(R.id.profile_switch_password);
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