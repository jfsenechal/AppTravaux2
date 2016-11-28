
package ac.marche.be.apptravaux.model;
import android.database.Cursor;
import android.net.Uri;

import ac.marche.be.apptravaux.database.BaseBdd;


/**
 * Created by jfsenechal on 30-08-16.
 */
public class Domaine {

    public static final String CONTENT_AUTHORITY = TravauxTag.AUTHORITY;
    public static final String TABLE_NAME = "domaine";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    private int id;
    private int remoteId;
    public String nom;

    public Domaine() {

    }

    public Domaine(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static Domaine cursorToDomaine(Cursor c) {
        Domaine domaine = new Domaine();
        domaine.setNom(c.getString(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_NOM)));
        domaine.setId(c.getInt(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_ID)));
        domaine.setRemoteId(c.getInt(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_REMOTE_ID)));

        return domaine;
    }
}
