package ac.marche.be.apptravaux.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import ac.marche.be.apptravaux.database.BaseBdd;
import ac.marche.be.apptravaux.lib.AcLog;
import ac.marche.be.apptravaux.model.Batiment;
import ac.marche.be.apptravaux.model.Document;
import ac.marche.be.apptravaux.model.Domaine;
import ac.marche.be.apptravaux.model.Intervention;
import ac.marche.be.apptravaux.model.Service;
import ac.marche.be.apptravaux.model.Suivi;

/**
 * Created by jfsenechal on 16/09/16.
 * J
 */
public class ProviderUtils extends TravauxProvider {
    ContentResolver contentResolver;

    public ProviderUtils(ContentResolver contentResolverParam) {

        contentResolver = contentResolverParam;
    }

    public Batiment getBatiment(int batimentId) {
        String where = BaseBdd.COLUMN_NAME_REMOTE_ID + " = " + batimentId;
        Cursor cursor = contentResolver.query(Batiment.CONTENT_URI, null, where, null, null);
        if (cursor == null) return null;

        if (cursor.moveToFirst()) {
            return Batiment.cursorToBatiment(cursor);
        }
        cursor.close();
        return null;
    }

    public Domaine getDomaine(int domaineId) {
        String where = BaseBdd.COLUMN_NAME_REMOTE_ID + " = " + domaineId;
        Cursor cursor = contentResolver.query(Domaine.CONTENT_URI, null, where, null, null);
        if (cursor == null) return null;

        if (cursor.moveToFirst()) {
            return Domaine.cursorToDomaine(cursor);
        }
        cursor.close();
        return null;
    }

    public Service getService(int serviceId) {
        String where = BaseBdd.COLUMN_NAME_REMOTE_ID + " = " + serviceId;
        //Uri contentUri = ContentUris.withAppendedId(Uri.parse(String.valueOf(Service.CONTENT_URI)), serviceId);
        Cursor cursor = contentResolver.query(Service.CONTENT_URI, null, where, null, null);
        if (cursor == null) return null;

        if (cursor.moveToFirst()) {
            return Service.cursorToService(cursor);
        }
        cursor.close();
        return null;
    }

    public Intervention getIntervention(int interventionId) {
        Uri contentUri = ContentUris.withAppendedId(Uri.parse(String.valueOf(Intervention.CONTENT_URI)), interventionId);
        Cursor cursor = contentResolver.query(contentUri, null, null, null, null);
        AcLog.d("uri", String.valueOf(Intervention.CONTENT_URI));
        if (cursor == null) return null;

        if (cursor.moveToFirst()) {
            return Intervention.cursorToIntervention(cursor);
        }
        cursor.close();
        return null;
    }

    /**
     * Lors de la synchro
     *
     * @param uri
     * @param remoteId
     * @return
     */
    public Boolean checkExist(Uri uri, int remoteId) {

        String where = BaseBdd.COLUMN_NAME_REMOTE_ID + " = " + remoteId;
        Cursor cursor = null;

        cursor = contentResolver.query(uri, null, where, null, null);

        if (cursor == null)
            return false;

        cursor.close();

        if (cursor.getCount() == 1)
            return true;

        return false;
    }

    public Uri insertRecords(ContentValues contentValues, Uri uri) {
        Uri result = contentResolver.insert(uri, contentValues);
        return result;
    }

    public long updateRecord(ContentValues contentValues, Uri uri, long remoteId) {
        String where = BaseBdd.COLUMN_NAME_REMOTE_ID + " = " + remoteId;
        long result = 0;

        result = contentResolver.update(uri, contentValues, where, null);

        return result;
    }

    public Cursor getDocumentByIntervention(int interventionId) {

        String where = Document.COLUMN_NAME_TRAVAUX_ID + " = " + interventionId;

        Uri contentUri = Document.CONTENT_URI;
        Cursor cursor = contentResolver.query(contentUri, null, where, null, null);

        return cursor;
    }

    /**
     * Retourne les suivis d'une intervention
     *
     * @param interventionId
     * @return
     */
    public Cursor getSuivisByIntervention(int interventionId) {

        String where = Suivi.COLUMN_NAME_TRAVAUX_ID + " = " + interventionId;

        Uri contentUri = Suivi.CONTENT_URI;

        return contentResolver.query(contentUri, null, where, null, null);

    }
}
