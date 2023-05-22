package com.praticing.yametee.Dashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.praticing.yametee.BorrowingReturn.Portal;
import com.praticing.yametee.MainLogin.LoginActivity;
import com.praticing.yametee.R;
import com.praticing.yametee.Student.BookListStudent;

public class DashboardStudent extends AppCompatActivity {
    CardView booklistforstudent,borrowreturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_student);

        findID();

        booklistforstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DashboardStudent.this, BookListStudent.class);
                startActivity(intent);
            }
        });

        borrowreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DashboardStudent.this, Portal.class);
                startActivity(intent);
            }
        });
    }

    private void findID() {
        booklistforstudent = findViewById(R.id.bookListforStudent);
        borrowreturn = findViewById(R.id.borrowreturn);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder build = new AlertDialog.Builder(this);

        build.setMessage("Do you want to sign out your account");
        build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(DashboardStudent.this, "Your account has been sign out", Toast.LENGTH_SHORT).show();
                Intent signOut = new Intent(DashboardStudent.this, LoginActivity.class);
                startActivity(signOut);
            }
        });
        build.setNegativeButton("No", null);
        build.create().show();
    }
}