package com.praticing.yametee.Librarian;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.praticing.yametee.R;

public class UpdateBookActivity extends AppCompatActivity {
    EditText title_input, author_input,genre_input,publish_input,pages_input,description_input;
    Button update_button,delete_button;
    String id, title, author, genre, publish, pages,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        findID();
        
        publish_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateBookActivity.this);
                datePickerDialog.show();

                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        publish_input.setText(year +"-"+ month +"-"+ day);
                    }
                });
            }
        });

        //Calling this method
        getAndSetIntentData();

        //Action bar title of a book
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LibrarianDatabase MyDB = new LibrarianDatabase(UpdateBookActivity.this);

                //Changing Value
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                genre = genre_input.getText().toString().trim();
                publish = publish_input.getText().toString().trim();
                pages = pages_input.getText().toString().trim();
                description = description_input.getText().toString().trim();
                //parameter
                MyDB.updateData(id,title,author,genre,publish,pages,description);

                //Going to the next activity
                Intent intent = new Intent(UpdateBookActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling this method
                deleteOneRow();
            }
        });
    }

    private void findID() {
        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        genre_input = findViewById(R.id.genre_input2);
        publish_input = findViewById(R.id.publish_input2);
        pages_input = findViewById(R.id.pages_input2);
        description_input = findViewById(R.id.description_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
    }
    //Viewing The Data!
    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("genre") && getIntent().hasExtra("publish") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages") && getIntent().hasExtra("des")) {
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
        } else {
            Toast.makeText(this, "You Failed, Quit!.", Toast.LENGTH_SHORT).show();
        }
    }
    //RecycleView in bookActivity layout OneRow Deleted depends on the user
    void deleteOneRow() {
        //Simple Dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LibrarianDatabase myDB = new LibrarianDatabase(UpdateBookActivity.this);
                myDB.deleteOneRow(id);
                finish();

                Intent intent = new Intent(UpdateBookActivity.this,BookActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", null);
        builder.create().show();
    }
    //if the user back pressed the app it will go in the dashboard layout
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UpdateBookActivity.this,BookActivity.class);
        startActivity(intent);
    }
}