package com.praticing.yametee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class studentLoginActivity extends AppCompatActivity {
    Button btn_loginStudent;
    TextInputEditText input_username,input_password;
    TextView register;
    private StudentDatabase studentDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);
        studentDatabase = new StudentDatabase(this);

        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);
        btn_loginStudent = findViewById(R.id.btn_loginStudent);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(studentLoginActivity.this,addStudentActivity.class);
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
                    Toast.makeText(studentLoginActivity.this, "Please fill the empty field", Toast.LENGTH_SHORT).show();
                } else if(checkAccount == true) {
                    Toast.makeText(studentLoginActivity.this, "Successfully login!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(studentLoginActivity.this,DashboardStudent.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(studentLoginActivity.this, "Wrong username and password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}