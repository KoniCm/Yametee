package com.praticing.yametee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class StudentDatabase extends SQLiteOpenHelper
{
    private Context context;

    //Creating the final datatype with private access modifier
    private static final String DATABASE_NAME = "studentData.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_Student";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PASS = "student_pass";
    private static final String COLUMN_NAME = "student_name";
    private static final String COLUMN_LEVEl = "student_level";
    private static final String COLUMN_SECTION = "student_section";
    private static final String COLUMN_STRAND = "student_strand";

    StudentDatabase(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    //Creating a table val i guess execute in the SQL
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_LEVEl + " INTEGER, " +
                COLUMN_SECTION + " TEXT, " +
                COLUMN_STRAND + " TEXT, " +
                COLUMN_PASS + " TEXT);";
        db.execSQL(query);
    }
    // If TableName exist the SQL drop TABLE
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //Method of adding student with parameter
    void addStudent(String id, String name, int level,String section,String strand,String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Putting value in the database by col for example , ID | NAME | LEVEL | SECTION | STRAND
        //                                                    012| Koni | 11    | ICTE101A| IT-MAWD
        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_LEVEl, level);
        cv.put(COLUMN_SECTION, section);
        cv.put(COLUMN_STRAND, strand);
        cv.put(COLUMN_PASS, pass);

        //Data is long res = database insert func tableName Col and the content Val
        long result = db.insert(TABLE_NAME,null, cv);

        //if there no val 1 = no val|no input
        if(result == -1)
        {
            // FAILED TO ADD OR PUT THE USER INPUT IN THE DATABASE! , SAD
            Toast.makeText(context, "Failed to add a book", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //SUCCESS TO ADD THE USER INPUT IN THE DATABASE WHICH MEAN U SUCCEEDING TO INSERT A VALUE IN THE DATABASE!
            Toast.makeText(context, "Successfully! New Student added", Toast.LENGTH_SHORT).show();
        }

    }
    // Read All user input int the data base
    Cursor readAllData()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    // Deleted all the user value in the database using this method
    void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
    void updateData(String row_id, String name, String level, String section, String strand,String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, row_id);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_LEVEl, level);
        cv.put(COLUMN_SECTION, section);
        cv.put(COLUMN_STRAND, strand);
        cv.put(COLUMN_PASS, pass);

        //update value if the id exists in the table
        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
        if(result == -1)
        {
            //Failed to update
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Success to update val
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if(result == -1)
        {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}