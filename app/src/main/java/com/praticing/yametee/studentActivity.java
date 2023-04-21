package com.praticing.yametee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class studentActivity extends AppCompatActivity {

    FloatingActionButton add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        add_btn = findViewById(R.id.addStudent_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(studentActivity.this, addStudent.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent goBack = new Intent(studentActivity.this, MainActivity.class);
        startActivity(goBack);
    }
}