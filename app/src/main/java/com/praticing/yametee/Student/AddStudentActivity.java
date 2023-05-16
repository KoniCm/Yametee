package com.praticing.yametee.Student;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.praticing.yametee.R;
import com.praticing.yametee.MainLogin.StudentLoginActivity;
import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {
    EditText id_input, name_input, level_input,section_input,strand_input,pass_input;
    Button addStudent_btn;
    private StudentDatabase stDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        stDatabase = new StudentDatabase(this);

        findID();
        addStudent();
    }

    private void addStudent() {
        addStudent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //-- List Of Strand
                List<String> listStrand = new ArrayList<>();

                listStrand.add("stem");
                listStrand.add("abm");
                listStrand.add("it");
                listStrand.add("culinary");
                listStrand.add("humss");

                String id = id_input.getText().toString();
                String name = name_input.getText().toString();
                String level = level_input.getText().toString();
                String section = section_input.getText().toString();
                String strand = strand_input.getText().toString();
                String pass = pass_input.getText().toString();

                //if the field empty keyword(isEmpty)
                if(isInputEmpty(id,name,level,section,strand,pass)) {
                    Toast.makeText(AddStudentActivity.this, "Fill the blank, Thank you!", Toast.LENGTH_SHORT).show();
                } else if(id.length() != 11) {
                    Toast.makeText(AddStudentActivity.this, "Enter a valid Student ID", Toast.LENGTH_SHORT).show();
                } else if (!level.equals("11") && !level.equals("12")) {
                    Toast.makeText(AddStudentActivity.this, "Enter a valid grade level", Toast.LENGTH_SHORT).show();
                } else if(!listStrand.contains(strand.toLowerCase())) {
                    Toast.makeText(AddStudentActivity.this, "Enter a valid strand", Toast.LENGTH_SHORT).show();
                } else if(pass.length() < 8) {
                    Toast.makeText(AddStudentActivity.this,"Password must be longer than 8 characters!",Toast.LENGTH_SHORT).show();
                } else {

                    stDatabase.addStudent(id.trim(), name.trim(), Integer.valueOf(level.trim()), section.trim(), strand.trim(), pass.trim());

                    //Going to the next activity
                    Intent intent = new Intent(AddStudentActivity.this, StudentLoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(AddStudentActivity.this,"Success! Account Register!",Toast.LENGTH_SHORT).show();

                    //clear all text field
                    clearField();
                }
            }
        });
    }

    private void findID() {
        id_input = findViewById(R.id.id_input);
        name_input = findViewById(R.id.name_input);
        level_input = findViewById(R.id.level_input);
        section_input = findViewById(R.id.section_input);
        strand_input = findViewById(R.id.strand_input);
        addStudent_btn = findViewById(R.id.addStudent_btn);
        pass_input = findViewById(R.id.pass_input);
    }

    private boolean isInputEmpty(String... inputs) {
        for (String input : inputs) {
            if(input.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void clearField() {
        id_input.getText().clear();
        name_input.getText().clear();
        level_input.getText().clear();
        section_input.getText().clear();
        strand_input.getText().clear();
        pass_input.getText().clear();
    }
}