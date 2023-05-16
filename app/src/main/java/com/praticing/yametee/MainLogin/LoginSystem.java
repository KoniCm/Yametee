package com.praticing.yametee.MainLogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.praticing.yametee.R;

public class LoginSystem extends AppCompatActivity {
    CardView student,admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_system);

        findID();
        
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LoginSystem.this, LibrarianLoginActivity.class);
                startActivity(intent);
            }
        });
        
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent student = new Intent(LoginSystem.this, StudentLoginActivity.class);
                startActivity(student);
            }
        });
    }

    private void findID() {
        student = findViewById(R.id.student);
        admin = findViewById(R.id.admin);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to exit the app");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(LoginSystem.this,"Thank you for using the program!", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", null);
        builder.create().show();
    }
}