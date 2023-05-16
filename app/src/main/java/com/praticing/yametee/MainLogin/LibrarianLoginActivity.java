package com.praticing.yametee.MainLogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.praticing.yametee.Dashboard.DashboardLibrarian;
import com.praticing.yametee.R;

public class LibrarianLoginActivity extends AppCompatActivity {
    final String permanentUser = "admin";
    final String permanentPass = "admin123";
    TextView help_btn;
    TextInputEditText input_user, input_pass;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);

        findID();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_X = input_user.getText().toString();
                String pass_X = input_pass.getText().toString();

                if(user_X.isEmpty() || pass_X.isEmpty()) {
                    Toast.makeText(LibrarianLoginActivity.this, "Fill the blank, Thank you!", Toast.LENGTH_SHORT).show();
                } else if(user_X.equals(permanentUser) && pass_X.equals(permanentPass)) {
                    Toast.makeText(LibrarianLoginActivity.this, "Successfully login as a Librarian!", Toast.LENGTH_SHORT).show();
                    Intent bypass = new Intent(LibrarianLoginActivity.this, DashboardLibrarian.class);
                    startActivity(bypass);
                } else {
                    Toast.makeText(LibrarianLoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        help_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OpenDialogHelper();
            }
        });
    }

    private void findID() {
        help_btn = findViewById(R.id.help_btn);
        input_user = findViewById(R.id.input_username);
        input_pass = findViewById(R.id.input_password);
        login_btn = findViewById(R.id.btn_login);
    }

    private void OpenDialogHelper() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Username and Password: "+"\n");
        builder.setMessage("Username: "+permanentUser+"" +
                "\n" + "Password: "+permanentPass);

        builder.create().show();
    }
}