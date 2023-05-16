package com.praticing.yametee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class UpdateStudentActivity extends AppCompatActivity {
    EditText id_input, name_input, level_input,section_input,strand_input,pass_input;
    Button updateStudent_btn, deleteStudent_btn;

    String id, name, level, section, strand, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        findID();

        //Calling this method
        getAndSetIntentData();

        //Action bar title of a book
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(id);
        }
        updateStudent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentDatabase studentDatabase = new StudentDatabase(UpdateStudentActivity.this);
                id = id_input.getText().toString().trim();
                name = name_input.getText().toString().trim();
                level = level_input.getText().toString().trim();
                section = section_input.getText().toString().trim();
                strand = strand_input.getText().toString().trim();
                pass = pass_input.getText().toString().trim();
                studentDatabase.updateData(id,name,level,section,strand,pass);

                Intent intent = new Intent(UpdateStudentActivity.this, StudentActivity.class);
                startActivity(intent);
            }
        });

        deleteStudent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteOneRow();
            }
        });
    }

    private void findID() {
        id_input = findViewById(R.id.id_input2);
        name_input = findViewById(R.id.name_input2);
        level_input = findViewById(R.id.level_input2);
        section_input = findViewById(R.id.section_input2);
        strand_input = findViewById(R.id.strand_input2);
        pass_input = findViewById(R.id.pass_input2);
        updateStudent_btn = findViewById(R.id.updateStudent_btn);
        deleteStudent_btn = findViewById(R.id.deleteStudent_btn);
    }

    //Viewing The Data!
    void getAndSetIntentData() {
        if(getIntent().hasExtra("row_id") && getIntent().hasExtra("name") && getIntent().hasExtra("level") && getIntent().hasExtra("section") &&
                getIntent().hasExtra("strand") && getIntent().hasExtra("pass")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("row_id");
            name = getIntent().getStringExtra("name");
            level = getIntent().getStringExtra("level");
            section = getIntent().getStringExtra("section");
            strand = getIntent().getStringExtra("strand");
            pass = getIntent().getStringExtra("pass");

            //Setting Intent Data
            id_input.setText(id);
            name_input.setText(name);
            level_input.setText(level);
            section_input.setText(section);
            strand_input.setText(strand);
            pass_input.setText(pass);
        } else {
            Toast.makeText(this, "You Failed, Quit!.", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow() {
        //Simple Dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + id);
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StudentDatabase myDB = new StudentDatabase(UpdateStudentActivity.this);
                myDB.deleteOneRow(id);
                finish();

                Intent intent = new Intent(UpdateStudentActivity.this, StudentActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", null);
        builder.create().show();
    }

    //if the user back pressed the app it will go in the dashboard layout
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UpdateStudentActivity.this, StudentActivity.class);
        startActivity(intent);
    }
}