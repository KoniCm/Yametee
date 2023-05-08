package com.praticing.yametee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    //A Context is a handle to the system
    private Context context;

    //Creating the final datatype with private access modifier
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_GENRE = "book_genre";
    private static final String COLUMN_PUBLISH = "book_publish";
    private static final String COLUMN_PAGES = "book_pages";
    private static final String COLUMN_DESCRIPTION = "book_description";

    MyDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    //Creating a table in a database using query method with String concatination var execute in the SQL
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_GENRE + " TEXT, " +
                COLUMN_PUBLISH + " INTEGER, " +
                COLUMN_PAGES + " INTEGER, " +
                COLUMN_DESCRIPTION + " TEXT);";
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
    void addBook(String title, String author, String genre , String publish, int pages, String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // Putting value in the database by col for example , ID|TITLE | AUTHOR | GENRE | PUBLISH | PAGES |DES
        //                                   the id incrementing|THE CODE| Koni | ACTION| 2023    | 100   |something
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_GENRE, genre);
        cv.put(COLUMN_PUBLISH, publish);
        cv.put(COLUMN_PAGES, pages);
        cv.put(COLUMN_DESCRIPTION, description);

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
            Toast.makeText(context, "Successfully! New Book added", Toast.LENGTH_SHORT).show();
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
    //Method of updating a value in the database with parameter
    void updateData(String row_id, String title, String author,String genre, String publish, String pages, String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_GENRE, genre);
        cv.put(COLUMN_PUBLISH, publish);
        cv.put(COLUMN_PAGES, pages);
        cv.put(COLUMN_DESCRIPTION, description);

        //update value if the id exists in the table
        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
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
    //Deleting one row where the id is located
    void deleteOneRow(String row_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1)
        {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    // Deleted all the user value in the database using this method
    void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}