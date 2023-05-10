package com.praticing.yametee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class studentLoginActivity extends AppCompatActivity
{
    Button btn_loginStudent;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);

        btn_loginStudent = findViewById(R.id.btn_loginStudent);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(studentLoginActivity.this,addStudentActivity.class);
                startActivity(intent);
            }
        });

        btn_loginStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(studentLoginActivity.this,DashboardStudent.class);
                startActivity(intent);
            }
        });
    }
}