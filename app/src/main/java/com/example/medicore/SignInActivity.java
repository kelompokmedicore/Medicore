package com.example.medicore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicore.database.DBHelper;
import com.example.medicore.object.User;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {

    DBHelper db;
    EditText etEmail, etPassword;
    Button btnLogin;
    ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initWidgets();

        btnLogin.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            userList = db.getUserData();

            if(userList.isEmpty()) {
                Toast.makeText(SignInActivity.this, "No user data in record, Please Sign Up first", Toast.LENGTH_SHORT).show();
            } else {
                if(email.equals("") || password.equals("")) {
                    Toast.makeText(SignInActivity.this, "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    int i = 0;
                    for(User u: userList) {
                        if(email.equals(u.getEmail()) && password.equals(u.getPassword())) {
                            i++;
                            Intent intent = new Intent(SignInActivity.this, ViewObatActivity.class);
                            startActivity(intent);

                            SharedPreferences sp = getSharedPreferences("LoggedUser", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putInt("Id", u.getId());
                            editor.putString("Nama", u.getNama());
                            editor.putString("Email", u.getEmail());
                            editor.putString("Password", u.getPassword());
                            editor.putString("Gender", u.getGender());
                            editor.putInt("Umur", u.getUmur());
                            editor.commit();

                            Toast.makeText(SignInActivity.this, "Welcome, " + u.getNama(), Toast.LENGTH_SHORT).show();
                            etEmail.setText("");
                            etPassword.setText("");
                        }
                    }
                    if (i==0) {
                        Toast.makeText(SignInActivity.this, "Wrong Email/Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void initWidgets() {
        etEmail = findViewById(R.id.signIn_et_email);
        etPassword = findViewById(R.id.signIn_et_password);
        btnLogin = findViewById(R.id.signIn_btn_login);
        db = new DBHelper(SignInActivity.this);
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

    //Shared Preferences
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String tempEmail = sp.getString("email", "");
        String tempPassword = sp.getString("password", "");

        etEmail.setText(tempEmail);
        etPassword.setText(tempPassword);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sp = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("email", etEmail.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.apply();
    }
}
