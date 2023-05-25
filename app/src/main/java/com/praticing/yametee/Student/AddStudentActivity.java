package com.praticing.yametee.Student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.praticing.yametee.MainLogin.StudentLoginActivity;
import com.praticing.yametee.R;
import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    String[] strandList = {"IT-MAWD", "ABM", "CULINARY", "TOPER", "HUMSS"};
    String[] sectionList = {"ICTE101A", "IT301A", "ACT101", "ABMT101A", "HUMSS101A", "TEM301"};

    String[] gradeLevel = {"11","12"};
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;

    ArrayAdapter<String> adapter3;

    AutoCompleteTextView strand_input,section_input,level_input;

    /***
     * Adding Strand list
     */
    TextInputEditText id_input, name_input,pass_input,input_Confirmpassword;
    Button addStudent_btn;
    private StudentDatabase stDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        stDatabase = new StudentDatabase(this);

        findID();
        addStudent();


        adapter = new ArrayAdapter<String>(this, R.layout.list_dropdown_strand, strandList);
        strand_input.setAdapter(adapter);
        //
        adapter2 = new ArrayAdapter<String>(this, R.layout.list_dropdown_section, sectionList);
        section_input.setAdapter(adapter2);

        adapter3 = new ArrayAdapter<String>(this, R.layout.list_dropdown_gradelevel, gradeLevel);
        level_input.setAdapter(adapter3);


        strand_input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
            }
        });

        section_input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView2, View view, int position, long id) {
                String item2 = adapterView2.getItemAtPosition(position).toString();
            }
        });
        level_input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView3, View view, int position, long id) {
                String items3 = adapterView3.getItemAtPosition(position).toString();
            }
        });
    }

    private void addStudent() {
        addStudent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //-- List Of Strand
                List<String> listStrand = new ArrayList<>();

                listStrand.add("it-mawd");
                listStrand.add("abm");
                listStrand.add("culinary");
                listStrand.add("toper");
                listStrand.add("humss");

                //-- List of Section
                List<String> listSection = new ArrayList<>();

                listSection.add("icte101a");
                listSection.add("it301a");
                listSection.add("act101");
                listSection.add("abmt101a");
                listSection.add("humss101a");
                listSection.add("tem301");

                // -- List of GradeLevel
                List<String> listGradelevel = new ArrayList<>();

                listGradelevel.add("11");
                listGradelevel.add("12");


                String id = id_input.getText().toString();
                String name = name_input.getText().toString();
                String level = level_input.getText().toString();
                String section = section_input.getText().toString();
                String strand = strand_input.getText().toString();
                String pass = pass_input.getText().toString();
                String confirmpass = input_Confirmpassword.getText().toString();

                //if the field empty keyword(isEmpty)
                if(isInputEmpty(id,name,level,section,strand,pass)) {
                    Toast.makeText(AddStudentActivity.this, "Please fill the empty field!", Toast.LENGTH_SHORT).show();
                } else if(id.length() != 11) {
                    Toast.makeText(AddStudentActivity.this, "Enter a valid Student ID", Toast.LENGTH_SHORT).show();
                } else if (!level.equals("11") && !level.equals("12")) {
                    Toast.makeText(AddStudentActivity.this, "Enter a valid grade level", Toast.LENGTH_SHORT).show();
                } else if(!listStrand.contains(strand.toLowerCase())) {
                    Toast.makeText(AddStudentActivity.this, "Enter a valid strand", Toast.LENGTH_SHORT).show();
                } else if(!listSection.contains(section.toLowerCase())){
                    Toast.makeText(AddStudentActivity.this, "Enter a valid section", Toast.LENGTH_SHORT).show();
                } else if(!listGradelevel.contains(level.toLowerCase())){
                    Toast.makeText(AddStudentActivity.this, "Enter a valid grade level", Toast.LENGTH_SHORT).show();
                }else if(pass.length() < 8) {
                    Toast.makeText(AddStudentActivity.this,"Password must be longer than 8 characters!",Toast.LENGTH_SHORT).show();
                } else if(!pass.equals(confirmpass)){
                    Toast.makeText(AddStudentActivity.this,"Password does not matched!",Toast.LENGTH_SHORT).show();
                }else {

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
        addStudent_btn = findViewById(R.id.btn_loginStudent);
        pass_input = findViewById(R.id.input_password);
        input_Confirmpassword = findViewById(R.id.input_Confirmpassword);
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