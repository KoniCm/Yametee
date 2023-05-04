package com.praticing.yametee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    ImageView mainAC_btn,student_btn,bookborrowreturn_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainAC_btn = findViewById(R.id.mainAc_btn);
        student_btn = findViewById(R.id.student_btn);
        bookborrowreturn_btn = findViewById(R.id.bookborrowreturn_btn);
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
    //This is a back press functionalities with simple dialog message
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to exit");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(MainActivity.this, "Thank you for using our program!", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", null);
        builder.create().show();
    }
}