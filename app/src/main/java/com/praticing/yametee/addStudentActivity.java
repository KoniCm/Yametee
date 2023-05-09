package com.praticing.yametee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addStudentActivity extends AppCompatActivity
{

    EditText id_input, name_input, level_input,section_input,strand_input;
    Button addStudent_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        id_input = findViewById(R.id.id_input);
        name_input = findViewById(R.id.name_input);
        level_input = findViewById(R.id.level_input);
        section_input = findViewById(R.id.section_input);
        strand_input = findViewById(R.id.strand_input);
        addStudent_btn = findViewById(R.id.addStudent_btn);

        addStudent_btn.setOnClickListener(new View.OnClickListener()
        {
            //isEmpty
            @Override
            public void onClick(View view)
            {
                if(id_input.getText().toString().isEmpty() || name_input.getText().toString().isEmpty() ||
                        level_input.getText().toString().isEmpty() || section_input.getText().toString().isEmpty() || strand_input.getText().toString().isEmpty())
                {
                    Toast.makeText(addStudentActivity.this, "Fill the blank, Thank you!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Calling database name and placing value in the layout by trim
                    StudentDatabase myDB = new StudentDatabase(addStudentActivity.this);
                    myDB.addStudent(id_input.getText().toString().trim(),
                            name_input.getText().toString().trim(),
                            Integer.valueOf(level_input.getText().toString().trim()),
                            section_input.getText().toString().trim(),
                            strand_input.getText().toString().trim());

                    //Going to the next activity
                    Intent intent = new Intent(addStudentActivity.this,studentActivity.class);
                    startActivity(intent);

                    //clear all text field
                    id_input.getText().clear();
                    name_input.getText().clear();
                    level_input.getText().clear();
                    section_input.getText().clear();
                    strand_input.getText().clear();
                }
            }
        });
    }
}