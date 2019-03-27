package com.sulistyo.mobile2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //inisialisasi komponen desain
    Button btLogin, btExit, btSave;
    EditText user, password;
    String usr, pswd;
    DatabaseHelper mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = new DatabaseHelper(this);

        btLogin = (Button) findViewById(R.id.btLogin);
        btSave = (Button) findViewById(R.id.btSave);
        btExit = (Button) findViewById(R.id.btExit);
        user = (EditText) findViewById(R.id.etUser);
        password = (EditText) findViewById(R.id.etPass);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr = user.getText().toString();
                pswd = password.getText().toString();
                if (usr.equals("") || pswd.equals("")) {
                    Toast.makeText(getApplicationContext(), "form tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean login = mDatabase.login(usr, pswd);
                    if (login) {
                        Toast.makeText(getApplicationContext(), "Login berhasil", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);
                        i.putExtra("n", usr);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Username atau password salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr = user.getText().toString();
                pswd = password.getText().toString();
                if (usr.equals("") || pswd.equals("")) {
                    Toast.makeText(getApplicationContext(), "form tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean cekUser = mDatabase.cekUser(usr);
                    if (cekUser) {
                        boolean simpanData = mDatabase.insertData(user.getText().toString().trim(), password.getText().toString().trim());
                        if (simpanData) {
                            Toast.makeText(MainActivity.this, "Data tersimpan", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, Main2Activity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(MainActivity.this, "gagal menyimpan", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User telah ada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
