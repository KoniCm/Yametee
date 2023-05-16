package com.praticing.yametee.Dashboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.praticing.yametee.Librarian.BookActivity;
import com.praticing.yametee.MainLogin.LoginSystem;
import com.praticing.yametee.BorrowingReturn.Portal;
import com.praticing.yametee.R;
import com.praticing.yametee.Librarian.StudentActivity;

public class DashboardLibrarian extends AppCompatActivity {
    CardView mainAC_btn,student_btn,bookborrowreturn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_librarian);

        findID();

        mainAC_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DashboardLibrarian.this, BookActivity.class);
                startActivity(intent);
            }
        });

        student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DashboardLibrarian.this, StudentActivity.class);
                startActivity(intent);
            }
        });

        bookborrowreturn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DashboardLibrarian.this, Portal.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder build = new AlertDialog.Builder(this);

        build.setMessage("Do you want to sign out your account");
        build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(DashboardLibrarian.this, "Your account has been sign out", Toast.LENGTH_SHORT).show();
                Intent signOut = new Intent(DashboardLibrarian.this, LoginSystem.class);
                startActivity(signOut);
            }
        });
        build.setNegativeButton("No", null);
        build.create().show();
    }

    private void findID() {
        mainAC_btn = findViewById(R.id.mainAc);
        student_btn = findViewById(R.id.student);
        bookborrowreturn_btn = findViewById(R.id.bookborrowreturn);
    }
}