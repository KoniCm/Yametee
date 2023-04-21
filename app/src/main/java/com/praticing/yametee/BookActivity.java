package com.praticing.yametee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity
{

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    //Connecting date base
    MyDatabaseHelper myDB;

    //Array list
    ArrayList<String> book_id, book_title, book_author,book_genre,book_publish,book_pages,book_description;

    //Connecting Adapter
    CustomAdapter customAdapter;

    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_activity);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        no_data = findViewById(R.id.no_data);

        add_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(BookActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        //creating arraylist

        myDB = new MyDatabaseHelper(BookActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_genre = new ArrayList<>();
        book_publish = new ArrayList<>();
        book_pages = new ArrayList<>();
        book_description = new ArrayList<>();



        storeDataInArrays();

        customAdapter = new CustomAdapter(BookActivity.this,this, book_id, book_title, book_author,book_genre,
                book_publish,book_pages, book_description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BookActivity.this));
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
    //refresh activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }

    //implementing menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //have a functionality
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home:
                Intent intent = new Intent(BookActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.setting:
                Toast.makeText(this, "Setting selected", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.delete_all:
                deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //if user backPress
    @Override
    public void onBackPressed()
    {
        Intent goBack = new Intent(BookActivity.this, MainActivity.class);
        startActivity(goBack);
    }

    //delete all row in the bookActivity
    void deleteAll()
    {
        //basic dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setIcon(R.drawable.black_delete);
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                MyDatabaseHelper myDB = new MyDatabaseHelper(BookActivity.this);
                myDB.deleteAllData();

                //Refresh Activity
                Intent intent = new Intent(BookActivity.this, BookActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });
        builder.create().show();
    }
}