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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class studentActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    FloatingActionButton add_btn;
    StudentDatabase mystDB;
    ArrayList<String> student_id, student_name, student_level, student_section,student_strand;
    studentCustomAdapter customAdapterStudent;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        recyclerView = findViewById(R.id.recyclerView);
        add_btn = findViewById(R.id.addStudent_btn);
        no_data = findViewById(R.id.no_data);

        //Student to AddStudent activity using floating action button
        add_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(studentActivity.this, addStudentActivity.class);
                startActivity(intent);
            }
        });

        mystDB = new StudentDatabase(studentActivity.this);
        student_id = new ArrayList<>();
        student_name = new ArrayList<>();
        student_level = new ArrayList<>();
        student_section = new ArrayList<>();
        student_strand = new ArrayList<>();

        storeDataInArrays();

        customAdapterStudent = new studentCustomAdapter(studentActivity.this,this, student_id, student_name, student_level,
                student_section, student_strand);
        recyclerView.setAdapter(customAdapterStudent);
        recyclerView.setLayoutManager(new LinearLayoutManager(studentActivity.this));
    }
    //read Data and Store in a database
    void storeDataInArrays()
    {
        Cursor cursor = mystDB.readAllData();
        if(cursor.getCount() == 0)
        {
            no_data.setVisibility(View.VISIBLE);
        }
        else
        {
            while (cursor.moveToNext())
            {
                student_id.add(cursor.getString(0));
                student_name.add(cursor.getString(1));
                student_level.add(cursor.getString(2));
                student_section.add(cursor.getString(3));
                student_strand.add(cursor.getString(4));
            }
            no_data.setVisibility(View.GONE);
        }
    }
    // If the user back press , going to the Dashboard activity
    @Override
    public void onBackPressed()
    {
        Intent goBack = new Intent(studentActivity.this, DashboardLibrarian.class);
        startActivity(goBack);
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
                Intent intent = new Intent(studentActivity.this, DashboardLibrarian.class);
                startActivity(intent);
                return true;
            case R.id.delete_all:
                deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                StudentDatabase myDB = new StudentDatabase(studentActivity.this);
                myDB.deleteAllData();

                //Refresh Activity
                Intent intent = new Intent(studentActivity.this, studentActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                //nothing change you can null it
            }
        });
        builder.create().show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }
}