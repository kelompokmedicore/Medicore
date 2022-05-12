package com.example.mad_utsnomor3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mad_utsnomor3.database.DBHelper;
import com.example.mad_utsnomor3.object.User;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    EditText etNama, etEmail, etPassword, etUmur;
    RadioButton rbMale, rbFemale;
    CheckBox cbTerms;
    Button btnRegister;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initWidgets();

        btnRegister.setOnClickListener(view -> {
            String nama = etNama.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String gender = getSelectedGender();
            int umur = 0;

            try {
                umur = Integer.parseInt(etUmur.getText().toString().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }


            if(nama.equals("") || email.equals("") || password.equals("") || etUmur.getText().toString().trim().equals("")) {
                Toast.makeText(SignUpActivity.this, "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
            } else {
                if(namaValid() && emailValid() && passwordValid() && umurValid() && genderValid() && termsValid() && emailUnique()) {
                    db.insertUser(nama, email, password, gender, umur);
                    Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(i);
                    Toast.makeText(SignUpActivity.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                    etNama.setText("");
                    etEmail.setText("");
                    etPassword.setText("");
                    rbMale.setChecked(false);
                    rbFemale.setChecked(false);
                    etUmur.setText("");
                    cbTerms.setChecked(false);
                } else {
                    Toast.makeText(SignUpActivity.this, initErrors(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initWidgets() {
        etNama = findViewById(R.id.register_et_nama);
        etEmail = findViewById(R.id.register_et_email);
        etPassword = findViewById(R.id.register_et_password);
        etUmur = findViewById(R.id.register_et_umur);
        rbMale = findViewById(R.id.register_rb_male);
        rbFemale = findViewById(R.id.register_rb_female);
        cbTerms = findViewById(R.id.register_checkBox_agreement);
        btnRegister = findViewById(R.id.register_btn_register);
        db = new DBHelper(SignUpActivity.this);
    }

    private boolean namaValid() {
        String nama = etNama.getText().toString().trim();
        return nama.length()>=8;
    }

    private boolean emailValid() {
        // [email]@[provider].[domain]

        int count1 = 0; // Count '@'
        int count2 = 0; // Count '.'
        boolean a = false; // Validation for Email must contain exactly one '@'
        boolean b = false; // Validation for Email must contain exactly one '.'
        boolean c = false; // Validation for Character '@' must not be next to '.'
        String email = etEmail.getText().toString().trim();

        // Character '@' must not be next to '.'
        try {
            if (email.charAt(email.indexOf('.') - 1) != '@' && email.charAt(email.indexOf('.') + 1) != '@') {
                c = true;
            }
        } catch (Exception e) {
            return false;
        }

        // Must contain exactly one ‘@’.
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@')
                count1++;
        }

        if (count1 == 1) {
            a = true;
        }

        // Must contain exactly one '.' after '@'
        for (int i = email.indexOf("@") + 2; i < email.length(); i++) {
            if (email.charAt(i) == '.')
                count2++;
        }

        if (count2 == 1) {
            b = true;
        }

        try {
            if (!email.startsWith("@") && !email.startsWith(".") && !email.endsWith("@") && !email.endsWith(".")
                    && a == true && b == true && c == true) {
                return true;
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean emailUnique() {
        String email = etEmail.getText().toString().trim();
        ArrayList<User> userList = db.getUserData();

        for(User u: userList) {
            if(email.equals(u.getEmail())) {
                return false;
            }
        }
        return true;
    }

    private boolean passwordValid() {
        String password = etPassword.getText().toString().trim();
        return password.length()>=8;
    }

    private boolean umurValid() {
        String tempUmur = etUmur.getText().toString().trim();
        int umur = Integer.parseInt(tempUmur);
        return umur>=13;
    }

    private boolean genderValid() {
        return rbMale.isChecked() || rbFemale.isChecked();
    }

    private String getSelectedGender() {
        if(rbMale.isChecked()) {
            return "Male";
        } else {
            return "Female";
        }
    }

    private boolean termsValid() {
        return cbTerms.isChecked();
    }

    private String initErrors() {
        String error1 = "";
        String error2 = "";
        String error3 = "";
        String error4 = "";
        String error5 = "";
        String error6 = "";
        String error7 = "";

        if(!namaValid()) {
            error1 = "* Invalid Nama, minimal 8 huruf\n";
        }
        if(!emailValid()) {
            error2 = "* Invalid Email, gunakan format [example@mail.com]\n";
        }
        if(!passwordValid()) {
            error3 = "* Invalid Password, minimal 8 huruf\n";
        }
        if(!umurValid()) {
            error4 = "* Umur harus minimal 13 (tahun)\n";
        }
        if (!genderValid()) {
            error5 = "* Harus memilih salah satu gender!\n";
        }
        if (!termsValid()) {
            error6 = "* Checkbox 'Terms' harus dicheck\n";
        } if (!emailUnique()) {
            error7 = "* Email sudah diambil\n";
        }

        String errors = "Error:\n\n" + error1 + error2 + error3 +error4 +error5 +error6 + error7;
        return errors.substring(0, errors.length()-1)   ;
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