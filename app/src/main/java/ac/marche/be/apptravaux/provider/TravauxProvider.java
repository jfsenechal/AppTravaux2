
package ac.marche.be.apptravaux.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import ac.marche.be.apptravaux.database.BaseBdd;
import ac.marche.be.apptravaux.database.DatabaseHelper;
import ac.marche.be.apptravaux.model.Batiment;
import ac.marche.be.apptravaux.model.Categorie;
import ac.marche.be.apptravaux.model.Document;
import ac.marche.be.apptravaux.model.Domaine;
import ac.marche.be.apptravaux.model.Intervention;
import ac.marche.be.apptravaux.model.Service;
import ac.marche.be.apptravaux.model.Suivi;
import ac.marche.be.apptravaux.model.TravauxTag;
import ac.marche.be.apptravaux.sync.DataObserver;

/**
 * Created by jfsenechal on 13-09-16.
 *
 */
/*
 * Define an implementation of ContentProvider that stubs out
 * all methods
 */
public class TravauxProvider extends ContentProvider {

    public static final String AUTHORITY = TravauxTag.AUTHORITY;
    public static final String URL = "content://" + AUTHORITY;
    public static final Uri CONTENT_URI = Uri.parse(URL);

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public DatabaseHelper dbHelper;
    public String tableName = null;
    public Uri contentUri = null;

    private static final int BATIMENT_LIST = 1;
    private static final int BATIMENT_ID = 2;
    private static final int CATEGORIE_LIST = 3;
    private static final int CATEGORIE_ID = 4;
    private static final int DOCUMENT_LIST = 5;
    private static final int DOCUMENT_ID = 6;
    private static final int DOMAINE_LIST = 7;
    private static final int DOMAINE_ID = 8;
    private static final int INTERVENTON_LIST = 9;
    private static final int INTERVENTION_ID = 10;
    private static final int SERVICE_LIST = 11;
    private static final int SERVICE_ID = 12;
    private static final int SUIVIS_LIST = 13;
    private static final int SUIVIS_ID = 14;

    static {
        uriMatcher.addURI(AUTHORITY, "batiment", BATIMENT_LIST);
        uriMatcher.addURI(AUTHORITY, "batiment/#", BATIMENT_ID);
        uriMatcher.addURI(AUTHORITY, "categorie", CATEGORIE_LIST);
        uriMatcher.addURI(AUTHORITY, "categorie/#", CATEGORIE_ID);
        uriMatcher.addURI(AUTHORITY, "document", DOCUMENT_LIST);
        uriMatcher.addURI(AUTHORITY, "document/#", DOCUMENT_ID);
        uriMatcher.addURI(AUTHORITY, "domaine", DOMAINE_LIST);
        uriMatcher.addURI(AUTHORITY, "domaine/#", DOMAINE_ID);
        uriMatcher.addURI(AUTHORITY, "intervention", INTERVENTON_LIST);
        uriMatcher.addURI(AUTHORITY, "intervention/#", INTERVENTION_ID);
        uriMatcher.addURI(AUTHORITY, "service", SERVICE_LIST);
        uriMatcher.addURI(AUTHORITY, "service/#", SERVICE_ID);
        uriMatcher.addURI(AUTHORITY, "suivis", SUIVIS_LIST);
        uriMatcher.addURI(AUTHORITY, "suivis/#", SUIVIS_ID);
    }

    /**
     * Always return true, indicating that the
     * provider loaded correctly.
     */
    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return true;
    }

    /**
     * Return MIME type
     * vnd.android.cursor.dir = plusieurs rows
     * vnd.android.cursor.item = une seule row
     * Ensuite le nom de l'auhtorite
     * Puis type de contenu
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * query()
     */
    @Override
    public Cursor query(
            Uri uri,
            String[] projection,
            String selection,
            String[] selectionArgs,
            String sortOrder) {

        getTableName(uri);
        long id = getId(uri);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        builder.setTables(tableName);

        if (id > 0) {
            selection = BaseBdd.COLUMN_NAME_ID + "=" + id;
        }

        return builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);

    }

    /**
     * insert() always returns null (no URI)
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = 0;
        getTableName(uri);

        result = db.insert(tableName, null, values);

        if (result > -1) {
            Uri uriResult = ContentUris.withAppendedId(contentUri, result);
            DataObserver dataObserver = new DataObserver(null, getContext());
            getContext().getContentResolver().notifyChange(uriResult, dataObserver);
            return uriResult;
        }
        return null;
    }

    /**
     * delete()
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        getTableName(uri);

        int rowsDeleted = db.delete(tableName, selection, selectionArgs);
        if (rowsDeleted > 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    /**
     * update() always returns "no rows affected" (0)
     */
    public int update(
            Uri uri,
            ContentValues values,
            String selection,
            String[] selectionArgs) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateCount = 0;
        getTableName(uri);

        updateCount = db.update(tableName, values, selection, selectionArgs);

        if (updateCount > 0) {
            DataObserver dataObserver = new DataObserver(null, getContext());
            getContext().getContentResolver().notifyChange(uri, dataObserver);
        }

        return updateCount;
    }

    /**
     * Pour extraire l'id a la fin du l'url
     *
     * @param uri
     * @return
     */
    private long getId(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment != null) {
            try {
                return Long.parseLong(lastPathSegment);
            } catch (NumberFormatException e) {
                // Log.e("llTrllavauxProvider", "Number Format Exception : " + e);

            }
        }
        return -1;
    }

    protected void getTableName(Uri contentUri) {

        switch (uriMatcher.match(contentUri)) {
            case BATIMENT_LIST:
                this.tableName = Batiment.TABLE_NAME;
                this.contentUri = Batiment.CONTENT_URI;
                break;
            case BATIMENT_ID:
                this.tableName = Batiment.TABLE_NAME;
                this.contentUri = Batiment.CONTENT_URI;
                break;
            case CATEGORIE_LIST:
                this.tableName = Categorie.TABLE_NAME;
                this.contentUri = Categorie.CONTENT_URI;
                break;
            case CATEGORIE_ID:
                this.tableName = Categorie.TABLE_NAME;
                this.contentUri = Categorie.CONTENT_URI;
                break;
            case DOCUMENT_LIST:
                this.tableName = Document.TABLE_NAME;
                this.contentUri = Document.CONTENT_URI;
                break;
            case DOCUMENT_ID:
                this.tableName = Document.TABLE_NAME;
                this.contentUri = Document.CONTENT_URI;
                break;
            case DOMAINE_LIST:
                this.tableName = Domaine.TABLE_NAME;
                this.contentUri = Domaine.CONTENT_URI;
                break;
            case DOMAINE_ID:
                this.tableName = Domaine.TABLE_NAME;
                this.contentUri = Domaine.CONTENT_URI;
                break;
            case INTERVENTON_LIST:
                this.tableName = Intervention.TABLE_NAME;
                this.contentUri = Intervention.CONTENT_URI;
                break;
            case INTERVENTION_ID:
                this.tableName = Intervention.TABLE_NAME;
                this.contentUri = Intervention.CONTENT_URI;
                break;
            case SERVICE_LIST:
                this.tableName = Service.TABLE_NAME;
                this.contentUri = Service.CONTENT_URI;
                break;
            case SERVICE_ID:
                this.tableName = Service.TABLE_NAME;
                this.contentUri = Service.CONTENT_URI;
                break;
            case SUIVIS_LIST:
                this.tableName = Suivi.TABLE_NAME;
                this.contentUri = Suivi.CONTENT_URI;
                break;
            case SUIVIS_ID:
                this.tableName = Suivi.TABLE_NAME;
                this.contentUri = Suivi.CONTENT_URI;
                break;
            default:
                break;
        }

        //  AcLog.d("table get", uriMatcher.match(contentUri) + this.tableName);

    }

     public Boolean checkExist(Uri uri, int remoteId) {

        String where = BaseBdd.COLUMN_NAME_REMOTE_ID + " = " + remoteId;
        Cursor cursor = null;

        cursor = this.query(uri, null, where, null, null);

        if (cursor == null)
            return false;

        cursor.close();

        if (cursor.getCount() == 1)
            return true;

        return false;
    }

}
