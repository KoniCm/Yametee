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
public class addBookActivity extends AppCompatActivity {
    EditText title_input, author_input, genre_input, publish_input, pages_input, description_input;
    Button add_button,bookCover_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        publish_input.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                DatePickerDialog datePicker = new DatePickerDialog(addBookActivity.this);
                datePicker.show();

                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        publish_input.setText(year +"-"+ month +"-"+ day);
                    }

                });

            }
        });

        bookCover_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(addBookActivity.this, "You clicked Add photo...", Toast.LENGTH_SHORT).show();
            }

        });

        add_button.setOnClickListener(new View.OnClickListener()
        {
            //if the field empty keyword(isEmpty)
            @Override
            public void onClick(View view) {
                //Declaration of book information
                String title = title_input.getText().toString();
                String author = author_input.getText().toString();
                String genre = genre_input.getText().toString();
                String publish = publish_input.getText().toString();
                String pages = pages_input.getText().toString();
                String description = description_input.getText().toString();

                if(isInputEmpty(title, author, genre, publish, pages, description)) {

                    Toast.makeText(addBookActivity.this, "Fill the empty blanks, Thank you!", Toast.LENGTH_SHORT).show();

                } else {

                    //Parameter in the database
                    LibrarianDatabase myDB = new LibrarianDatabase(addBookActivity.this);

                    myDB.addBook(title.trim(), author.trim(), genre.trim(), publish.trim(), Integer.valueOf(pages_input.getText().toString().trim()), description.trim());

                    //Going to the next activity
                    Intent intent = new Intent(addBookActivity.this,BookActivity.class);
                    startActivity(intent);

                    //Clearing a text field
                    clearTextField();
                }
            }

            private boolean isInputEmpty(String... inputs) {
                for (String input : inputs) {
                    if (input.isEmpty()) {
                        return true;
                    }
                }
                return false;
            }
            private void clearTextField() {
                title_input.getText().clear();
                author_input.getText().clear();
                genre_input.getText().clear();
                publish_input.getText().clear();
                pages_input.getText().clear();
                description_input.getText().clear();
            }
        });
    }
}