package com.praticing.yametee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class studentPanel extends AppCompatActivity
{
    Button btn_loginStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);

        btn_loginStudent = findViewById(R.id.btn_loginStudent);

        btn_loginStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(studentPanel.this,DashboardStudent.class);
                startActivity(intent);
            }
        });
    }
}