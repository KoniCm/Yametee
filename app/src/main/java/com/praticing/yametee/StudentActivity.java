package com.praticing.yametee;

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

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    StudentDatabase mystDB;
    ArrayList<String> student_id, student_name, student_level, student_section,student_strand,student_pass;
    StudentCustomAdapter customAdapterStudent;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        recyclerView = findViewById(R.id.recyclerView);
        no_data = findViewById(R.id.no_data);

        mystDB = new StudentDatabase(StudentActivity.this);
        student_id = new ArrayList<>();
        student_name = new ArrayList<>();
        student_level = new ArrayList<>();
        student_section = new ArrayList<>();
        student_strand = new ArrayList<>();
        student_pass = new ArrayList<>();

        storeDataInArrays();

        customAdapterStudent = new StudentCustomAdapter(StudentActivity.this,this, student_id, student_name, student_level,
                student_section, student_strand,student_pass);
        recyclerView.setAdapter(customAdapterStudent);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentActivity.this));
    }
    //read Data and Store in a database
    void storeDataInArrays() {
        Cursor cursor = mystDB.readAllData();
        if(cursor.getCount() == 0) {
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                student_id.add(cursor.getString(0));
                student_name.add(cursor.getString(1));
                student_level.add(cursor.getString(2));
                student_section.add(cursor.getString(3));
                student_strand.add(cursor.getString(4));
                student_pass.add(cursor.getString(5));
            }
            no_data.setVisibility(View.GONE);
        }
    }
    // If the user back press , going to the Dashboard activity
    @Override
    public void onBackPressed() {
        Intent goBack = new Intent(StudentActivity.this, DashboardLibrarian.class);
        startActivity(goBack);
    }
    //implementing menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);

        return super.onCreateOptionsMenu(menu);
    }
    //have a functionality
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(StudentActivity.this, DashboardLibrarian.class);
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
    void deleteAll() {
        //basic dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setIcon(R.drawable.black_delete);
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StudentDatabase myDB = new StudentDatabase(StudentActivity.this);
                myDB.deleteAllData();

                //Refresh Activity
                Intent intent = new Intent(StudentActivity.this, StudentActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No",null);
        builder.create().show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }
}