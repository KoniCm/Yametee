package com.praticing.yametee.Librarian;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.praticing.yametee.R;

public class AddBookActivity extends AppCompatActivity {

    private final static int PICK_PHOTO_FOR_AVATAR = 99;
    EditText titleInput, authorInput, genreInput, publishInput, pagesInput, descriptionInput;
    Button addButton;
    ImageView bookCover;
    private LibrarianDatabase librarianDatabase;

    private Uri imagePath;
    private Bitmap imageStore;

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
        bookCover = findViewById(R.id.book_cover);
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

    private void addPhoto() {
        bookCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
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
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {

            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imagePath = data.getData();
                imageStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                bookCover.setImageBitmap(imageStore);
            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.clear_item, menu);

        return super.onCreateOptionsMenu(menu);
    }
    //have a functionality to trigger
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                clearTextField();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}