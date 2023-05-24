package com.praticing.yametee.MainLogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.praticing.yametee.R;

public class MainLoginActivity extends AppCompatActivity {

    CardView librarian_btn,student_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        findID();
        Librarian();
        Student();
    }

    private void Student() {
        student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studentLogin = new Intent(MainLoginActivity.this,StudentLoginActivity.class);
                startActivity(studentLogin);
            }
        });
    }

    private void Librarian() {
        librarian_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent librarianLogin = new Intent(MainLoginActivity.this,LibrarianLoginActivity.class);
                startActivity(librarianLogin);
            }
        });
    }

    private void findID() {
        librarian_btn = findViewById(R.id.librarian_btn);
        student_btn = findViewById(R.id.student_btn);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to exit an application");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainLoginActivity.this, "Thank you for using the program!", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
        });
        builder.setNegativeButton("Cancel",null);

        builder.create().show();
    }
}