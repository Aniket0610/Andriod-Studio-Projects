package com.example.contentprovider94;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class StudentProvider extends ContentProvider {

    public static final String AUTHORITY =
            "com.example.contentprovider94.provider";

    public static final Uri CONTENT_URI =
            Uri.parse(
                    "content://" +
                            AUTHORITY +
                            "/students"
            );

    private StudentDatabase studentDatabase;

    @Override
    public boolean onCreate() {

        studentDatabase =
                new StudentDatabase(getContext());

        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db =
                studentDatabase.getWritableDatabase();

        long id = db.insert(
                StudentDatabase.TABLE_NAME,
                null,
                values
        );

        if (id == -1) {
            return null;
        }

        return Uri.withAppendedPath(
                CONTENT_URI,
                String.valueOf(id)
        );
    }

    @Override
    public Cursor query(
            Uri uri,
            String[] projection,
            String selection,
            String[] selectionArgs,
            String sortOrder) {

        SQLiteDatabase db =
                studentDatabase.getReadableDatabase();

        return db.query(
                StudentDatabase.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    @Override
    public int delete(
            Uri uri,
            String selection,
            String[] selectionArgs) {

        SQLiteDatabase db =
                studentDatabase.getWritableDatabase();

        return db.delete(
                StudentDatabase.TABLE_NAME,
                selection,
                selectionArgs
        );
    }

    @Override
    public int update(
            Uri uri,
            ContentValues values,
            String selection,
            String[] selectionArgs) {

        SQLiteDatabase db =
                studentDatabase.getWritableDatabase();

        return db.update(
                StudentDatabase.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}