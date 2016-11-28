
package ac.marche.be.apptravaux.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import ac.marche.be.apptravaux.database.BaseBdd;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by jfsenechal on 07-03-16.
 */
public class Suivi {

    public static final String CONTENT_AUTHORITY = TravauxTag.AUTHORITY;
    public static final String TABLE_NAME = "suivis";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static final String COLUMN_NAME_DESCRIPTIF = "descriptif";
    public static final String COLUMN_NAME_TRAVAUX_ID = "travauxid";
    public static final String COLUMN_NAME_USER = "user";
    public static final String COLUMN_NAME_CREATED = "created";

    private int id;
    private int remoteId;
    private int travauxId;
    private String descriptif;
    public String created;
    public String user;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(descriptif + "\n" + " le  " + created);
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getTravauxId() {
        return travauxId;
    }

    public void setTravauxId(int travauxId) {
        this.travauxId = travauxId;
    }

    public ContentValues createContentValues(Response<List<Suivi>> response) {
        ContentValues values = new ContentValues();

        List<Suivi> body = response.body();
        int i = 0;
        Log.d(TAG, response.body().get(i).getDescriptif());
        Log.d(TAG, String.valueOf(response.body().get(i).getId()));

        values.put(COLUMN_NAME_DESCRIPTIF, body.get(i).getDescriptif());
        values.put(COLUMN_NAME_USER, body.get(i).getUser());
        values.put(COLUMN_NAME_CREATED, body.get(i).getCreated());
        values.put(BaseBdd.COLUMN_NAME_REMOTE_ID, body.get(i).getRemoteId());
        values.put(COLUMN_NAME_TRAVAUX_ID, body.get(i).getTravauxId());

        return values;
    }

    public static Suivi cursorToSuivis(Cursor c) {

        Suivi suivi = new Suivi();
        suivi.setDescriptif(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_DESCRIPTIF)));
        suivi.setCreated(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_CREATED)));
        suivi.setUser(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_USER)));
        suivi.setTravauxId(c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_TRAVAUX_ID)));
        suivi.setRemoteId(c.getInt(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_REMOTE_ID)));
        suivi.setId(c.getInt(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_ID)));

        return suivi;
    }
}
