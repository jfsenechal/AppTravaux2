
package ac.marche.be.apptravaux.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import ac.marche.be.apptravaux.database.BaseBdd;


/**
 * Created by jfsenechal on 07-03-16.
 */
public class Document {

    public static final String CONTENT_AUTHORITY = TravauxTag.AUTHORITY;
    public static final String TABLE_NAME = "document";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static final String COLUMN_NAME_FILENAME = "filename";
    public static final String COLUMN_NAME_TRAVAUX_ID = "travauxid";
    public static final String COLUMN_NAME_USER = "user";
    public static final String COLUMN_NAME_MIME = "mime";
    public static final String COLUMN_NAME_THUMBNAIL = "thumbnail";
    public static final String COLUMN_NAME_ZOOM = "zoom";
    public static final String COLUMN_NAME_CREATED = "updated";

    private int id;
    private int remoteId;
    public String filename;
    public String user;
    public String created;
    public String mime;
    public String thumbnail;
    public String zoom;
    public int travauxId;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(filename + "\n" + " le  " + created);
        return sb.toString();
    }

    public Document() {
    }

    public Document(int travauxId, String filename) {
        this.travauxId = travauxId;
        this.filename = filename;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public int getTravauxId() {
        return travauxId;
    }

    public void setTravauxId(int travauxId) {
        this.travauxId = travauxId;
    }

    public static Document cursorToDocument(Cursor cursor) {

        Document document = new Document();
        document.setFilename(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_FILENAME)));
        document.setCreated(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CREATED)));
        document.setMime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_MIME)));
        document.setThumbnail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_THUMBNAIL)));
        document.setZoom(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ZOOM)));
        document.setTravauxId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_TRAVAUX_ID)));
        document.setRemoteId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_REMOTE_ID)));
        document.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_ID)));

        return document;
    }

    public ContentValues createContentValues(JSONObject row) throws JSONException {

        ContentValues values = new ContentValues();
        values.put(Document.COLUMN_NAME_FILENAME, row.getString(COLUMN_NAME_FILENAME));
        values.put(Document.COLUMN_NAME_THUMBNAIL, row.getString(COLUMN_NAME_THUMBNAIL));
        values.put(Document.COLUMN_NAME_ZOOM, row.getString(COLUMN_NAME_ZOOM));
        values.put(Document.COLUMN_NAME_MIME, row.getString(COLUMN_NAME_MIME));
        values.put(Document.COLUMN_NAME_CREATED, row.getString(COLUMN_NAME_CREATED));
        values.put(Document.COLUMN_NAME_TRAVAUX_ID, row.getInt(COLUMN_NAME_TRAVAUX_ID));
        values.put(BaseBdd.COLUMN_NAME_REMOTE_ID, row.getInt(BaseBdd.COLUMN_NAME_REMOTE_ID));
       // values.put(Document.COLUMN_NAME_USER, row.getString(Document.COLUMN_NAME_USER));
        return values;
    }


}
