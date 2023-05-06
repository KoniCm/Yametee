package com.praticing.yametee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class adminPanel extends AppCompatActivity
{
    TextView helpUserPass;
    EditText input_user,input_pass;

    //Here is the user and pass of the admin in this application
    final String permanentAdminUser = "admin";
    final String permanentAdminPass = "admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        helpUserPass = findViewById(R.id.forgot_btn);
        input_user = findViewById(R.id.input_username);
        input_pass = findViewById(R.id.input_password);
        Button admin = findViewById(R.id.btn_loginAdmin);

        admin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //were creating a == methods.

                String user = input_user.getText().toString();
                String pass = input_pass.getText().toString();

                if(user.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(adminPanel.this, "Fill the blank, Thank you",Toast.LENGTH_SHORT).show();
                }
                else if(user.equals(permanentAdminUser) && pass.equals(permanentAdminPass))
                {
                    Toast.makeText(adminPanel.this, "Successfully login as admin!", Toast.LENGTH_SHORT).show();
                    Intent bypass = new Intent(adminPanel.this,MainActivity.class);
                    startActivity(bypass);
                }
                else
                {
                    Toast.makeText(adminPanel.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        helpUserPass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Calling this method!
                helper();
            }
        });
    }
    void helper()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Username and Password: "+"\n");
        builder.setMessage("Username: "+permanentAdminUser+"" +
               "\n" + "Password: "+permanentAdminPass);

        builder.create().show();
    }
}