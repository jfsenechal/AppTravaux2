package ac.marche.be.apptravaux.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ac.marche.be.apptravaux.model.Suivi;


/**
 * Created by jfsenechal on 14-09-16.
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "apptravaux.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String BLOB_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createAnnexe("batiment"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Suivi.TABLE_NAME);
        onCreate(db);
    }

    private String createAnnexe(String TABLE_NAME) {
        return
                "CREATE TABLE " + TABLE_NAME + " (" +
                        BaseBdd._ID + " INTEGER PRIMARY KEY," +
                        BaseBdd.COLUMN_NAME_REMOTE_ID + INTEGER_TYPE + COMMA_SEP +
                        BaseBdd.COLUMN_NAME_NOM + TEXT_TYPE +
                        ")";
    }
}
