package com.praticing.yametee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
public class AddActivity extends AppCompatActivity
{
    EditText title_input, author_input, genre_input, publish_input, pages_input, description_input;
    Button add_button,bookCover_button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        genre_input = findViewById(R.id.genre_input);
        publish_input = findViewById(R.id.publish_input);
        pages_input = findViewById(R.id.pages_input);
        description_input = findViewById(R.id.description_input);
        bookCover_button = findViewById(R.id.bookCover_button);
        add_button = findViewById(R.id.add_button);

        publish_input.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DatePickerDialog datePicker = new DatePickerDialog(AddActivity.this);
                datePicker.show();
                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day)
                    {
                        publish_input.setText(year +"-"+ month +"-"+ day);
                    }
                });
            }
        });
        bookCover_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(AddActivity.this, "You clicked Add photo...", Toast.LENGTH_SHORT).show();
            }
        });

        add_button.setOnClickListener(new View.OnClickListener()
        {
            //if the field empty keyword(isEmpty)
            @Override
            public void onClick(View view)
            {
                if(title_input.getText().toString().isEmpty() || author_input.getText().toString().isEmpty() ||
                        genre_input.getText().toString().isEmpty() || publish_input.getText().toString().isEmpty() || pages_input.getText().toString().isEmpty() ||
                        description_input.getText().toString().isEmpty())
                {
                    Toast.makeText(AddActivity.this, "Fill the blank, Thank you!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Parameter in the database
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addBook(title_input.getText().toString().trim(),
                            author_input.getText().toString().trim(),
                            genre_input.getText().toString().trim(),
                            publish_input.getText().toString().trim(),
                            Integer.valueOf(pages_input.getText().toString().trim()),
                            description_input.getText().toString().trim());

                    //Going to the next activity
                    Intent intent = new Intent(AddActivity.this,BookActivity.class);
                    startActivity(intent);

                    //Clearing a text field
                    title_input.getText().clear();
                    author_input.getText().clear();
                    genre_input.getText().clear();
                    publish_input.getText().clear();
                    pages_input.getText().clear();
                    description_input.getText().clear();
                }
            }
        });
    }
}