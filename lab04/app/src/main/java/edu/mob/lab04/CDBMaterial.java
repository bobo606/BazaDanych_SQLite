package edu.mob.lab04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CDBMaterial {
    private CDBHelper dbHelper;
    private SQLiteDatabase dbMaterial;

    public CDBMaterial(Context context) { dbHelper = new CDBHelper(context);}

    public void open() throws SQLException {
        dbMaterial = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbMaterial.close();
    }
    public void addMaterial(String typ, String norma){
        ContentValues cv = new ContentValues();
        cv.put(CDBHelper.TYPE_TYPENAME,typ);
        cv.put(CDBHelper.TYPE_STANDARD, norma);
        try{
            dbMaterial.insert(CDBHelper.TABLE_TYPE, null,cv);
        }catch (SQLException e){
            Log.i("DB write error", e.getMessage());
        }
    }

    public Cursor getMaterials(){
        return dbMaterial.query(CDBHelper.TABLE_TYPE,
                null, null,null,null,null,CDBHelper.TYPE_ID);
    }
}
