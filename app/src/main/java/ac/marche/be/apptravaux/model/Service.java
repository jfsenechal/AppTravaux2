
package ac.marche.be.apptravaux.model;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;

import ac.marche.be.apptravaux.database.BaseBdd;

/**
 * Created by jfsenechal on 12-04-16.
 */
public class Service {

    public static final String CONTENT_AUTHORITY = TravauxTag.AUTHORITY;
    public static final String TABLE_NAME = "service";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    private int id;
    private int remoteId;
    public String nom;

    public Service() {

    }

    public Service(int id, String nom) {
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

    public static Service cursorToService(Cursor c) {
        Service Service = new Service();
        Service.setNom(c.getString(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_NOM)));
        Service.setId(c.getInt(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_REMOTE_ID)));

        return Service;
    }

    public HashMap<Integer, Service> cursorToServices(Cursor c) {
        Log.e("count Service", Integer.toString(c.getCount()));

        HashMap<Integer, Service> Services = new HashMap<Integer, Service>();

        for (int i = 0; i < c.getCount(); i++) {
            c.moveToNext();
            Service Service = new Service();
            Service.setNom(c.getString(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_NOM)));
            Service.setRemoteId(c.getInt(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_REMOTE_ID)));
            Service.setId(c.getInt(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_ID)));

            Services.put(i, Service);
        }

        c.close();
        return Services;
    }

}
