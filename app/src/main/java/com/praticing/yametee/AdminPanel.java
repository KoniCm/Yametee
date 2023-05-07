package com.praticing.yametee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AdminPanel extends AppCompatActivity {

    final String permanentUser = "admin";
    final String permanentPass = "admin123";

    TextView help_btn;
    TextInputEditText input_user, input_pass;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        help_btn = findViewById(R.id.help_btn);
        input_user = findViewById(R.id.input_username);
        input_pass = findViewById(R.id.input_password);
        login_btn = findViewById(R.id.btn_login);


        login_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String user_X = input_user.getText().toString();
                String pass_X = input_pass.getText().toString();

                if(user_X.isEmpty() || pass_X.isEmpty())
                {
                    Toast.makeText(AdminPanel.this, "Fill the blank, Thank you!", Toast.LENGTH_SHORT).show();
                }
                else if(user_X.equals(permanentUser) && pass_X.equals(permanentPass))
                {
                    Toast.makeText(AdminPanel.this, "Successfully login as a admin!", Toast.LENGTH_SHORT).show();
                    Intent bypass = new Intent(AdminPanel.this,MainActivity.class);
                    startActivity(bypass);
                }
                else
                {
                    Toast.makeText(AdminPanel.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        help_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OpenDialogHelper();
            }
        });
    }
    void OpenDialogHelper()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Username and Password: "+"\n");
        builder.setMessage("Username: "+permanentUser+"" +
                "\n" + "Password: "+permanentPass);

        builder.create().show();
    }
}