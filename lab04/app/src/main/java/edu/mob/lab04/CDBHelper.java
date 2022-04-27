package edu.mob.lab04;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CDBHelper extends SQLiteOpenHelper {

    public static String TABLE_TYPE = "TYPES";
    public static String TYPE_ID = "_id";
    public static String TYPE_TYPENAME = "typ";
    public static String TYPE_STANDARD = "norma";

    private static final String DB_NAME = "Material.db";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE_TYPE =
            "CREATE TABLE " + TABLE_TYPE + "(" +
                    TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TYPE_TYPENAME + " TEXT," + TYPE_STANDARD + " TEXT" + ")";

    public CDBHelper(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        try{
            db.execSQL(CREATE_TABLE_TYPE);
        }catch(SQLException ex){
            Log.i("SQlite error", ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);
            onCreate(db);
        }catch (SQLException ex){
            Log.i("SQLite error" , ex.getMessage());
        }
    }

}
