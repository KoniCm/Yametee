package com.praticing.yametee.MainLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.praticing.yametee.Dashboard.DashboardLibrarian;
import com.praticing.yametee.Dashboard.DashboardStudent;
import com.praticing.yametee.R;
import com.praticing.yametee.Student.AddStudentActivity;
import com.praticing.yametee.Student.StudentDatabase;

public class LoginActivity extends AppCompatActivity {

    final String permanentUser = "123";
    final String permanentPass = "admin123";

    Button btn_loginStudent;
    TextInputEditText input_username,input_password;
    TextView register;
    private StudentDatabase studentDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        studentDatabase = new StudentDatabase(this);

        findID();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });

        btn_loginStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = input_username.getText().toString();
                String pass = input_password.getText().toString();

                boolean checkAccount = studentDatabase.checkIdPassword(id,pass);

                if(id.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill the empty field", Toast.LENGTH_SHORT).show();
                } else if(id.equals(permanentUser) || pass.equals(permanentPass)){
                    Toast.makeText(LoginActivity.this, "Successfully login as a Librarian!", Toast.LENGTH_SHORT).show();
                    Intent bypass = new Intent(LoginActivity.this, DashboardLibrarian.class);
                    startActivity(bypass);
                }else if(checkAccount == true) {
                    Toast.makeText(LoginActivity.this, "Successfully login as a Student!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, DashboardStudent.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong username and password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findID() {
        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);
        btn_loginStudent = findViewById(R.id.btn_loginStudent);
        register = findViewById(R.id.register);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}