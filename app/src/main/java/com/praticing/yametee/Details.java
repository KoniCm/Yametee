package com.praticing.yametee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity
{
    TextView title_input, author_input,genre_input,publish_input,pages_input,description_input;
    String id, title, author, genre, publish, pages, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title_input = findViewById(R.id.title_details);
        author_input = findViewById(R.id.author_details);
        genre_input = findViewById(R.id.genre_details);
        publish_input = findViewById(R.id.publish_details);
        pages_input = findViewById(R.id.pages_details);
        description_input = findViewById(R.id.description_details);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

    }
    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("genre") && getIntent().hasExtra("publish") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages") && getIntent().hasExtra("des"))
        {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            genre = getIntent().getStringExtra("genre");
            publish = getIntent().getStringExtra("publish");
            pages = getIntent().getStringExtra("pages");
            description = getIntent().getStringExtra("des");

            //Setting Intent Data
            title_input.setText(title);
            author_input.setText(author);
            genre_input.setText(genre);
            publish_input.setText(publish);
            pages_input.setText(pages);
            description_input.setText(description);
        }
        else
        {
            Toast.makeText(this, "You Failed, Quit!.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Details.this,MainActivity.class);
        startActivity(intent);
    }
}