package com.example.zerogluten;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_name = "zerogluten.db" ;
    private static final int DB_version = 1 ;

    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESC= "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_POID = "poid";
    private static final String COLUMN_CATEGORIE = "category";

    public MyDbHelper(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESC + " TEXT, " +
                COLUMN_PRICE + " INTEGER," +
                COLUMN_POID + " INTEGER," +
                COLUMN_CATEGORIE+ " TEXT,"+
                "photo"+ " BLOB);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addProducts(String name, String desc, int price, int poid, String categorie, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESC, desc);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_POID, poid);
        cv.put(COLUMN_CATEGORIE, categorie);
        cv.put("photo", image);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            //Toast.makeText(context, categorie, Toast.LENGTH_LONG).show();
            Log.d("cv", "addProducts: ");

        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, categorie, Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }

    //get products by category pharmacy
    Cursor readAllDataByCategory(String category){
        String[] params = new String[]{ category };
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("SELECT * FROM products WHERE category = ?",
                    params);
        }
        return cursor;
    }


    //get products by category pharmacy
    Cursor searchProduct(String name){

        String[] params = new String[]{ name };
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("SELECT * FROM products WHERE name = ?",
                    params);
        }
        return cursor;
    }
}
