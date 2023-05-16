package com.praticing.yametee.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.praticing.yametee.Dashboard.DashboardStudent;
import com.praticing.yametee.Librarian.LibrarianDatabase;
import com.praticing.yametee.R;
import java.util.ArrayList;

public class BookListStudent extends AppCompatActivity {
    private LibrarianDatabase lbDatabase;
    RecyclerView recyclerView;
    TextView no_data;

    //Array list
    ArrayList<String> book_id, book_title, book_author,book_genre,book_publish,book_pages,book_description;

    CustomAdapterBookListForStudent customAdapterBS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_student);
        lbDatabase = new LibrarianDatabase(this);

        findID();

        //creating arraylist

        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_genre = new ArrayList<>();
        book_publish = new ArrayList<>();
        book_pages = new ArrayList<>();
        book_description = new ArrayList<>();

        storeDataInArrays();

        customAdapterBS = new CustomAdapterBookListForStudent(BookListStudent.this,this, book_id, book_title, book_author,book_genre,
                book_publish,book_pages, book_description);
        recyclerView.setAdapter(customAdapterBS);
        recyclerView.setLayoutManager(new LinearLayoutManager(BookListStudent.this));
    }

    private void findID() {
        recyclerView = findViewById(R.id.recyclerView);
        no_data = findViewById(R.id.no_data);
    }

    //read Data and Store in a database
    private void storeDataInArrays() {
        Cursor cursor = lbDatabase.readAllData();
        if(cursor.getCount() == 0) { no_data.setVisibility(View.VISIBLE); }
        else {
            while (cursor.moveToNext()) {
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_genre.add(cursor.getString(3));
                book_publish.add(cursor.getString(4));
                book_pages.add(cursor.getString(5));
                book_description.add(cursor.getString(6));
            }
            no_data.setVisibility(View.GONE);
        }
    }
    //implementing menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_men, menu);

        return super.onCreateOptionsMenu(menu);
    }
    //have a functionality
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(BookListStudent.this, DashboardStudent.class);
                startActivity(intent);
                return true;
            case R.id.favList:
                Intent favListBook = new Intent(BookListStudent.this, FavouriteBookList.class);
                startActivity(favListBook);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BookListStudent.this,DashboardStudent.class);
        startActivity(intent);
    }
}