package com.example.pilotbuddy.datamodel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class AirportDB extends SQLiteOpenHelper {


    private static final String DB_NAME = "pilotbuddy.db";
    private static final int DB_VERSION = 1;

    private final Context context;

    public AirportDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context.getApplicationContext();
        copyDatabaseIfNeeded();
    }

    private void copyDatabaseIfNeeded() {
        File dbFile = context.getDatabasePath(DB_NAME);
        if (dbFile.exists()) {
                return;
        }
        dbFile.getParentFile().mkdirs();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = context.getAssets().open(DB_NAME);
            out = new FileOutputStream(dbFile);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.flush();
        } catch (IOException e) {
            dbFile.delete();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                // nothing more to do
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // The database is prepackaged, so there is nothing to create here.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Version 1 only; nothing to upgrade.
    }

    /** Returns the airport with the given identifier, or null if there is no match. */
    public Airport findByIdentifier(String identifier) {
        String key = identifier.trim().toUpperCase();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, ident, name FROM airport WHERE ident = ?",
                new String[]{key});
        if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        String id = String.valueOf(cursor.getInt(0));
        String ident = cursor.getString(1);
        String name = cursor.getString(2);
        cursor.close();

        ArrayList<Runway> runways = new ArrayList<Runway>();
        Cursor rc = db.rawQuery(
                "SELECT designation, length_ft, surface FROM runway WHERE airport_id = ? ORDER BY length_ft DESC",
                new String[]{id});
        while (rc.moveToNext()) {
            runways.add(new Runway(rc.getString(0), rc.getInt(1), rc.getString(2)));
        }
        rc.close();

        ArrayList<Frequency> frequencies = new ArrayList<Frequency>();
        Cursor fc = db.rawQuery(
                "SELECT type, value FROM frequency WHERE airport_id = ? ORDER BY _id",
                new String[]{id});
        while (fc.moveToNext()) {
            frequencies.add(new Frequency(fc.getString(0), fc.getString(1)));
        }
        fc.close();

        return new Airport(ident, name, runways, frequencies);
    }

    /** Returns every airport identifier in the database, sorted. */
    public String[] getAllIdentifiers() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ident FROM airport ORDER BY ident", null);
        String[] result = new String[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            result[i] = cursor.getString(0);
            i++;
        }
        cursor.close();
        return result;
    }

}
