package com.praticing.yametee;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class StudentDatabase extends SQLiteOpenHelper
{
    private Context context;

    private static final String DATABASE_NAME = "studentData.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_Student";

    private static final String COLUMN_ID = "student_id";
    private static final String COLUMN_NAME = "student_name";
    private static final String COLUMN_LEVEl = "student_level";
    private static final String COLUMN_SECTION = "student_section";
    private static final String COLUMN_STRAND = "student_strand";

    public StudentDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_LEVEl + " INTEGER, " +
                COLUMN_SECTION + " TEXT, " +
                COLUMN_STRAND + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addStudent(String id, String name,int level,String section,String strand){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_LEVEl, level);
        cv.put(COLUMN_SECTION, section);
        cv.put(COLUMN_STRAND, strand);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1)
        {
            Toast.makeText(context, "Failed to add a book", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Successfully! New Book added", Toast.LENGTH_SHORT).show();
        }
    }
}
