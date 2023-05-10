package com.praticing.yametee;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class addStudentActivity extends AppCompatActivity
{
    EditText id_input, name_input, level_input,section_input,strand_input,pass_input;
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
        pass_input = findViewById(R.id.pass_input);

        addStudent_btn.setOnClickListener(new View.OnClickListener()
        {
            //isEmpty
            @Override
            public void onClick(View view)
            {
                //-- List Of Strand
                List<String> listStrand = new ArrayList<>();

                listStrand.add("stem");
                listStrand.add("abm");
                listStrand.add("ict");
                listStrand.add("culinary");
                listStrand.add("humss");

                if(id_input.getText().toString().isEmpty() || name_input.getText().toString().isEmpty() ||
                        level_input.getText().toString().isEmpty() || section_input.getText().toString().isEmpty() || strand_input.getText().toString().isEmpty() ||
                pass_input.getText().toString().isEmpty())
                {
                    Toast.makeText(addStudentActivity.this, "Fill the blank, Thank you!", Toast.LENGTH_SHORT).show();
                }
                else if(id_input.getText().toString().length() != 11)
                {
                    Toast.makeText(addStudentActivity.this, "Enter a valid Student ID", Toast.LENGTH_SHORT).show();
                }
                else if (!level_input.getText().toString().equals("11") && !level_input.getText().toString().equals("12"))
                {
                    Toast.makeText(addStudentActivity.this, "Enter a valid grade level", Toast.LENGTH_SHORT).show();
                }
                else if(!listStrand.contains(strand_input.getText().toString().toLowerCase()))
                {
                    Toast.makeText(addStudentActivity.this, "Enter a valid strand", Toast.LENGTH_SHORT).show();
                }
                else if(pass_input.getText().toString().length() < 8)
                {
                    Toast.makeText(addStudentActivity.this,"Password must be longer than 8 characters!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Calling database name and placing value in the layout by trim
                    StudentDatabase myDB = new StudentDatabase(addStudentActivity.this);
                    myDB.addStudent(id_input.getText().toString().trim(),
                            name_input.getText().toString().trim(),
                            Integer.valueOf(level_input.getText().toString().trim()),
                            section_input.getText().toString().trim(),
                            pass_input.getText().toString().trim(),
                            strand_input.getText().toString().trim());

                    //Going to the next activity
                    Toast.makeText(addStudentActivity.this,"Success! Account Register!",Toast.LENGTH_SHORT).show();

                    //clear all text field
                    id_input.getText().clear();
                    name_input.getText().clear();
                    level_input.getText().clear();
                    section_input.getText().clear();
                    strand_input.getText().clear();
                    pass_input.getText().clear();
                }
            }
        });
    }
}