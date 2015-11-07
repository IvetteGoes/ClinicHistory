package com.example.saraivette.clinichistory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SaraIvette on 07/11/2015.
 */
public class BDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "HistorialClinico.sqlite";
    private static final int DB_SCHEME_VERSION = 1;


    public BDHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DataBaseManager.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
