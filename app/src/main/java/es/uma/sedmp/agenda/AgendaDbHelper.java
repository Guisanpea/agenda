package es.uma.sedmp.agenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

class AgendaDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Agenda.db";

    static class AgendaContract {
        private AgendaContract() {}
        static abstract class AgendaEntry implements BaseColumns {
            static final String TABLE_NAME = "Agenda";
            static final String COLUMN_NAME_PERSONA = "nombre";
            static final String COLUMN_NAME_TELEFONO = "telefono";
        }
    }

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AgendaContract.AgendaEntry.TABLE_NAME + " (" +
                    AgendaContract.AgendaEntry._ID + " INTEGER PRIMARY KEY," +
                    AgendaContract.AgendaEntry.COLUMN_NAME_PERSONA + " TEXT UNIQUE," +
                    AgendaContract.AgendaEntry.COLUMN_NAME_TELEFONO + " TEXT" +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AgendaContract.AgendaEntry.TABLE_NAME;

    AgendaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
