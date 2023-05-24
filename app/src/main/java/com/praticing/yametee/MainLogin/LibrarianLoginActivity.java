package com.praticing.yametee.MainLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.praticing.yametee.Dashboard.DashboardLibrarian;
import com.praticing.yametee.R;

public class LibrarianLoginActivity extends AppCompatActivity {

    private final String librarianSampleUser = "123";
    private final String librarianSamplePass = "admin123";

    Button btn_loginLibrarian;
    TextInputEditText input_user,input_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);

        findID();

        LoginActivity();


    }

    private void LoginActivity() {
        btn_loginLibrarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userX = input_user.getText().toString();
                String passX = input_pass.getText().toString();

                if(userX.isEmpty() || passX.isEmpty()){
                    Toast.makeText(LibrarianLoginActivity.this, "Please fill the empty field", Toast.LENGTH_SHORT).show();
                } else if(userX.equals(librarianSampleUser) || passX.equals(librarianSamplePass)) {
                    Toast.makeText(LibrarianLoginActivity.this, "Successfully login as a librarian", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LibrarianLoginActivity.this, DashboardLibrarian.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LibrarianLoginActivity.this, "Wrong username and password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findID() {
        input_user = findViewById(R.id.input_usernameLibrarian);
        input_pass = findViewById(R.id.input_passwordLibrarian);
        btn_loginLibrarian  = findViewById(R.id.btn_loginLibrarian);
    }
}