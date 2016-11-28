
package ac.marche.be.apptravaux.model;

import android.database.Cursor;
import android.net.Uri;

import ac.marche.be.apptravaux.database.BaseBdd;


/**
 * Created by jfsenechal on 12-04-16.
 */
public class Batiment {

    public static final String CONTENT_AUTHORITY = TravauxTag.AUTHORITY;
    public static final String TABLE_NAME = "batiment";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    private int id;
    private int remoteId;
    public String nom;

    public Batiment() {

    }

    public Batiment(int id, String nom) {
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

    public static Batiment cursorToBatiment(Cursor c) {
        Batiment batiment = new Batiment();
        batiment.setNom(c.getString(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_NOM)));
        batiment.setId(c.getInt(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_ID)));
        batiment.setRemoteId(c.getInt(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_REMOTE_ID)));

        return batiment;
    }
}
