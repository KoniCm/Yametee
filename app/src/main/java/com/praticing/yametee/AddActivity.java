package com.praticing.yametee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {

    EditText title_input, author_input,genre_input,publish_input,pages_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        genre_input = findViewById(R.id.genre_input);
        publish_input = findViewById(R.id.publish_input);
        pages_input = findViewById(R.id.pages_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title_input.getText().toString().isEmpty() || author_input.getText().toString().isEmpty() ||
                        genre_input.getText().toString().isEmpty() || publish_input.getText().toString().isEmpty() || pages_input.getText().toString().isEmpty())
                {
                    Toast.makeText(AddActivity.this, "Fill the blank, Thank you!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addBook(title_input.getText().toString().trim(),
                            author_input.getText().toString().trim(),
                            genre_input.getText().toString().trim(),
                            Integer.valueOf(publish_input.getText().toString().trim()),
                            Integer.valueOf(pages_input.getText().toString().trim()));

                    Intent intent = new Intent(AddActivity.this,MainActivity.class);
                    startActivity(intent);

                    title_input.getText().clear();
                    author_input.getText().clear();
                    genre_input.getText().clear();
                    publish_input.getText().clear();
                    pages_input.getText().clear();
                }
            }
        });
    }
}