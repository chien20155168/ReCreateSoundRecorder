package com.nvchung.recreatesoundrecorder.DB;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.HashMap;
import java.util.Map;

import static android.provider.BaseColumns._ID;

public class SaveRecordConstract {

       //0. DB

    public static final String DB_NAME_FILE = "record.db";
    public static final int DB_VS = 1;



    //1. TABLE 1
    public static final class SaveTable implements BaseColumns {
        public static final String TABLE_SAVE = "TABLE_SAVE";


        //4 cot
        public static final String CO_NAME = "NAME";
        public static final String CO_PATH = "PATH";
        public static final String CO_DATE = "DATE";
        public static final String CO_LENG = "LENG";


        public static final HashMap<String, String> COLS = new HashMap<String, String>() { {
            put(CO_NAME,     "TEXT");
            put(CO_PATH,    "TEXT");
            put(CO_DATE,  "INTEGER");
            put(CO_LENG,  "INTEGER");                          // Neatly write fields and their types
        }};

    }

    //</editor-fold>


    static public String createTable(String tableName, HashMap<String, String> fields) {
        String command = "CREATE TABLE " + tableName +
                    " (" +_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT";

        for (Map.Entry<String, String> entry : fields.entrySet())
            command = command + ", " + entry.getKey() + " " + entry.getValue();

        command = command + " );";
        return command;
    }

}
