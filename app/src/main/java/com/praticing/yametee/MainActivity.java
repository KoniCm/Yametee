package com.praticing.yametee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    CardView mainAC_btn,student_btn,bookborrowreturn_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainAC_btn = findViewById(R.id.mainAc);
        student_btn = findViewById(R.id.student);
        bookborrowreturn_btn = findViewById(R.id.bookborrowreturn);
        //Going to the bookActivity // Dashboard to BookActivity
        mainAC_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });
        //same, but it goes to the Student  // Dashboard to StudentActivity
        student_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,studentActivity.class);
                startActivity(intent);
            }
        });
        //same, but it goes to the borrow/return // Dashboard to Portal = borrow/return
        bookborrowreturn_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, Portal.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder build = new AlertDialog.Builder(this);

        build.setMessage("Do you want to sign out your account");
        build.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(MainActivity.this, "Your account has been sign out", Toast.LENGTH_SHORT).show();
                Intent signOut = new Intent(MainActivity.this,LoginSystem.class);
                startActivity(signOut);
            }
        });
        build.setNegativeButton("No", null);
        build.create().show();
    }
}