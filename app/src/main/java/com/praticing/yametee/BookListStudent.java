package com.praticing.yametee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

public class BookListStudent extends AppCompatActivity
{
    RecyclerView recyclerView;
    TextView no_data;

    //Connecting date base
    MyDatabaseHelper myDB;

    //Array list
    ArrayList<String> book_id, book_title, book_author,book_genre,book_publish,book_pages,book_description;

    CustomAdapterBS customAdapterBS;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_student);

        recyclerView = findViewById(R.id.recyclerView);
        no_data = findViewById(R.id.no_data);

        //creating arraylist

        myDB = new MyDatabaseHelper(BookListStudent.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_genre = new ArrayList<>();
        book_publish = new ArrayList<>();
        book_pages = new ArrayList<>();
        book_description = new ArrayList<>();

        storeDataInArrays();

        customAdapterBS = new CustomAdapterBS(BookListStudent.this,this, book_id, book_title, book_author,book_genre,
                book_publish,book_pages, book_description);
        recyclerView.setAdapter(customAdapterBS);
        recyclerView.setLayoutManager(new LinearLayoutManager(BookListStudent.this));

    }
    //read Data and Store in a database
    void storeDataInArrays()
    {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0)
        {
            no_data.setVisibility(View.VISIBLE);
        }
        else
        {
            while (cursor.moveToNext())
            {
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
}