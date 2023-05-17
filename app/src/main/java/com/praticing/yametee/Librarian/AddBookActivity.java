package com.praticing.yametee.Librarian;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.praticing.yametee.R;

public class AddBookActivity extends AppCompatActivity {

    private final static int PICK_PHOTO_FOR_AVATAR = 100;
    EditText titleInput, authorInput, genreInput, publishInput, pagesInput, descriptionInput;
    Button addButton, bookCoverButton;
    private LibrarianDatabase librarianDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        librarianDatabase = new LibrarianDatabase(this);

        findViewID();
        addBook();
        datePickerDialogListener();
        addPhoto();
    }

    private void findViewID() {
        titleInput = findViewById(R.id.title_input);
        authorInput = findViewById(R.id.author_input);
        genreInput = findViewById(R.id.genre_input);
        publishInput = findViewById(R.id.publish_input);
        pagesInput = findViewById(R.id.pages_input);
        descriptionInput = findViewById(R.id.description_input);
        bookCoverButton = findViewById(R.id.bookCover_button);
        addButton = findViewById(R.id.add_button);
    }

    private void addBook() {
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String title = titleInput.getText().toString();
                String author = authorInput.getText().toString();
                String genre = genreInput.getText().toString();
                String publish = publishInput.getText().toString();
                String pages = pagesInput.getText().toString();
                String description = descriptionInput.getText().toString();

                if(isInputEmpty(title, author, genre, publish, pages, description)) {

                    Toast.makeText(AddBookActivity.this, "Fill the empty blanks, Thank you!", Toast.LENGTH_SHORT).show();

                } else {

                    librarianDatabase.addBook(title.trim(), author.trim(), genre.trim(), publish.trim(), Integer.valueOf(pages.trim()), description.trim());

                    Intent intent = new Intent(AddBookActivity.this, BookActivity.class);
                    startActivity(intent);

                    clearTextField();
                }
            }
        });
    }

    private void datePickerDialogListener() {
        publishInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePicker = new DatePickerDialog(AddBookActivity.this);

                datePicker.show();
                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        publishInput.setText(year +"-"+ month +"-"+ day);

                    }
                });
            }
        });
    }

    private void addPhoto() {
        bookCoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(AddBookActivity.this, "Imaged Picked", Toast.LENGTH_SHORT).show();
                pickImage();
            }
        });
    }

    private void clearTextField() {
        titleInput.getText().clear();
        authorInput.getText().clear();
        genreInput.getText().clear();
        publishInput.getText().clear();
        pagesInput.getText().clear();
        descriptionInput.getText().clear();
    }

    private boolean isInputEmpty(String... inputs) {
        for (String input : inputs) {
            if (input.isEmpty()) { return true; }
        }
        return false;
    }
    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }
}