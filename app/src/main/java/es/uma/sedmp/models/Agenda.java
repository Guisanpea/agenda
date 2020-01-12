package es.uma.sedmp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

import es.uma.sedmp.AgendaApplication;
import es.uma.sedmp.models.AgendaDbHelper.AgendaContract.AgendaEntry;

import static es.uma.sedmp.models.AgendaDbHelper.AgendaContract.AgendaEntry.COLUMN_NAME_PERSONA;
import static es.uma.sedmp.models.AgendaDbHelper.AgendaContract.AgendaEntry.COLUMN_NAME_TELEFONO;
import static es.uma.sedmp.models.AgendaDbHelper.AgendaContract.AgendaEntry.TABLE_NAME;

public enum Agenda {
    INSTANCE;
    private final SQLiteDatabase db;

    Agenda() {
        Context context = AgendaApplication.getAppContext();
        db = new AgendaDbHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public Boolean putValue(String key, String value) {
        ContentValues values = new ContentValues();
        values.put(AgendaEntry.COLUMN_NAME_PERSONA, key);
        values.put(COLUMN_NAME_TELEFONO, value);

        long result = db.insert(TABLE_NAME, null, values);

        if (result == -1) {
            db.update(
                    TABLE_NAME,
                    values,
                    COLUMN_NAME_PERSONA + "= ?",
                    new String[]{key});
        }

        return result != -1;
    }

    public Map<String, String> get(String key) {
        Cursor cursor = getCursor(key);

        Map<String, String> result = new HashMap<>();

        while (cursor.moveToNext()) {
            String telefono = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TELEFONO));
            String persona = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PERSONA));

            result.put(persona, telefono);
        }

        cursor.close();

        return result;
    }

    private Cursor getCursor(String key) {
        String[] projection = {
                COLUMN_NAME_PERSONA,
                COLUMN_NAME_TELEFONO
        };

        String selection = COLUMN_NAME_PERSONA + " LIKE ?";
        String[] selectionArgs = {key + "%"};

        return db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
    }
}
