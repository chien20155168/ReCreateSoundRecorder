package com.nvchung.recreatesoundrecorder.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nvchung.recreatesoundrecorder.Model.InforSaveRecord;

import java.util.HashMap;
import java.util.Map;

import static android.provider.BaseColumns._ID;
import static com.nvchung.recreatesoundrecorder.DB.SaveRecordConstract.DB_NAME_FILE;
import static com.nvchung.recreatesoundrecorder.DB.SaveRecordConstract.DB_VS;
import static com.nvchung.recreatesoundrecorder.DB.SaveRecordConstract.SaveTable.CO_DATE;
import static com.nvchung.recreatesoundrecorder.DB.SaveRecordConstract.SaveTable.CO_LENG;
import static com.nvchung.recreatesoundrecorder.DB.SaveRecordConstract.SaveTable.CO_NAME;
import static com.nvchung.recreatesoundrecorder.DB.SaveRecordConstract.SaveTable.CO_PATH;
import static com.nvchung.recreatesoundrecorder.DB.SaveRecordConstract.SaveTable.TABLE_SAVE;
import static com.nvchung.recreatesoundrecorder.DB.SaveRecordConstract.createTable;

public class DBHelper extends SQLiteOpenHelper {


    private Context context;

    //0. init
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME_FILE, null, DB_VS);
        this.context = context;

    }

    //This call  when db created call first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCommantDB = createTable(TABLE_SAVE, SaveRecordConstract.SaveTable.COLS);
        Log.d("TAG", "onCreate: \n" + createCommantDB);
        db.execSQL(createCommantDB);
    }

    @SuppressWarnings("unused")
    private static final String SQL_DELETE_SAVED_DB = "DROP TABLE IF EXISTS " + TABLE_SAVE;


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //null
    }


    //2. CRUD

    //C
    public long addRecord(String name, String path, long legth) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CO_NAME, name);
        values.put(CO_PATH, path);
        values.put(CO_DATE, System.currentTimeMillis());
        values.put(CO_LENG, legth);
        long rowId = db.insert(TABLE_SAVE, null, values);
        return rowId;
    }



    //D
    public void removeItemwithID(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] whereArg = {String.valueOf(id)};
        db.delete(TABLE_SAVE, "_ID=?", whereArg);

    }

    //R
    public InforSaveRecord readDataAt(int position) {
        SQLiteDatabase bd = getReadableDatabase();
        String[] inforColum = {
                _ID,
                CO_NAME,
                CO_PATH,
                CO_DATE,
                CO_LENG,
        };

        Cursor c = bd.query(TABLE_SAVE, inforColum, null, null, null, null, null);
        if (c.moveToPosition(position)) {
            InforSaveRecord inforSaveRecord = new InforSaveRecord();
            inforSaveRecord.setmId(c.getInt(c.getColumnIndex(_ID)));
            inforSaveRecord.setmName(c.getString(c.getColumnIndex(CO_NAME)));
            inforSaveRecord.setmFilePath(c.getString(c.getColumnIndex(CO_PATH)));
            inforSaveRecord.setmTime(c.getInt(c.getColumnIndex(CO_DATE)));
            c.close();
            return inforSaveRecord;
        }
        return null;

    }

    public int getCont() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] colSe = {_ID};

        Cursor c = sqLiteDatabase.query(TABLE_SAVE, colSe, null, null, null, null, null);
        int count = c.getCount();
        c.close();
        return count;
    }

    //U
    public void renameItem(InforSaveRecord item, String name, String path) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CO_NAME, name);
        values.put(CO_PATH, path);
        db.update(TABLE_SAVE, values, _ID + "=" + item.getmId(), null);
    }

}
