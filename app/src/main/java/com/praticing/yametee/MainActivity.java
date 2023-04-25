package com.praticing.yametee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
                Intent go = new Intent(MainActivity.this, BookActivity.class);
                startActivity(go);
            }
        });
        //same, but going to the Student  // Dashboard to StudentActivity
        student_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,studentActivity.class);
                startActivity(intent);
            }
        });
        //same, but its going to the borrowing book and returning book
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
}