package com.praticing.yametee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Dashboard extends AppCompatActivity {

    ImageButton mainAC_btn,student_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mainAC_btn = findViewById(R.id.mainAc_btn);
        student_btn = findViewById(R.id.student_btn);

        mainAC_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(Dashboard.this, MainActivity.class);
                startActivity(go);
            }
        });
        student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this,studentActivity.class);
                startActivity(intent);
            }
        });
    }
}