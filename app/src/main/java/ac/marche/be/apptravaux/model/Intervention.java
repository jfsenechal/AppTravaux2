
package ac.marche.be.apptravaux.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ac.marche.be.apptravaux.database.BaseBdd;

/**
 * Created by jfsenechal on 17-02-16.
 */
public class Intervention {

    public static final String CONTENT_AUTHORITY = TravauxTag.AUTHORITY;
    public static final String TABLE_NAME = "intervention";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static final String COLUMN_NAME_INTITULE = "intitule";
    public static final String COLUMN_NAME_DESCRIPTIF = "descriptif";
    public static final String COLUMN_NAME_USER = "user";
    public static final String COLUMN_NAME_CREATED = "created";
    public static final String COLUMN_NAME_UPDATED = "updated";
    public static final String COLUMN_NAME_CATEGORIE = "categorie";
    public static final String COLUMN_NAME_BATIMENT = "batiment";
    public static final String COLUMN_NAME_DOMAINE = "domaine";
    public static final String COLUMN_NAME_SERVICE = "service";
    public static final String COLUMN_NAME_AFFECTATION = "affectation";
    public static final String COLUMN_NAME_DATE_SOLUTION = "datesolution";
    public static final String COLUMN_NAME_DATE_VALIDATION = "datevalidation";
    public static final String COLUMN_NAME_SOLUTION = "solution";
    public static final String COLUMN_NAME_TRANSMIS = "transmis";
    public static final String COLUMN_NAME_PRIORITE = "priorite";

    private int id;
    public int remoteId;
    public String intitule;
    public String descriptif;
    public String user;
    public int batiment;
    public int categorie;
    public int domaine;
    public int service;
    public String created;
    public String updated;
    public String affectation;
    public Boolean transmis;
    public String solution;
    public String dateSolution;
    public String priorite;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(intitule + "\n" + "par  " + user);
        return sb.toString();
    }

    public static ArrayList<Intervention> fromJson(JSONArray jsonArray) {
        JSONObject businessJson;
        ArrayList<Intervention> businesses = new ArrayList<Intervention>(jsonArray.length());
        // Process each result in json array, decode and convert to business object
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                businessJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            /**
             * @TODO
             * https://guides.codepath.com/android/Converting-JSON-to-Models
             * */
            // Intervention business = Intervention.fromJson(businessJson);
            Intervention business = null;
            if (business != null) {
                businesses.add(business);
            }
        }

        return businesses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getBatiment() {
        return batiment;
    }

    public void setBatiment(int batiment) {
        this.batiment = batiment;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getDomaine() {
        return domaine;
    }

    public void setDomaine(int domaine) {
        this.domaine = domaine;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getAffectation() {
        return affectation;
    }

    public void setAffectation(String affectation) {
        this.affectation = affectation;
    }

    public Boolean getTransmis() {
        return transmis;
    }

    public void setTransmis(Boolean transmis) {
        this.transmis = transmis;
    }

    public int getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getDateSolution() {
        return dateSolution;
    }

    public void setDateSolution(String dateSolution) {
        this.dateSolution = dateSolution;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public ContentValues createContentValues(JSONObject row) throws JSONException {

        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseBdd.COLUMN_NAME_REMOTE_ID, row.getInt(BaseBdd.COLUMN_NAME_REMOTE_ID));
        contentValues.put(COLUMN_NAME_INTITULE, row.getString(COLUMN_NAME_INTITULE));
        contentValues.put(COLUMN_NAME_DESCRIPTIF, row.getString(COLUMN_NAME_DESCRIPTIF));
        contentValues.put(COLUMN_NAME_USER, row.getString(COLUMN_NAME_USER));
        contentValues.put(COLUMN_NAME_CREATED, row.getString(COLUMN_NAME_CREATED));
        contentValues.put(COLUMN_NAME_UPDATED, row.getString(COLUMN_NAME_UPDATED));

        if (row.has(COLUMN_NAME_CATEGORIE))
            contentValues.put(COLUMN_NAME_CATEGORIE, row.getInt(COLUMN_NAME_CATEGORIE));
        if (row.has(COLUMN_NAME_DOMAINE))
            contentValues.put(COLUMN_NAME_DOMAINE, row.getInt(COLUMN_NAME_DOMAINE));
        if (row.has(COLUMN_NAME_SERVICE))
            contentValues.put(COLUMN_NAME_SERVICE, row.getInt(COLUMN_NAME_SERVICE));
        if (row.has(COLUMN_NAME_BATIMENT))
            contentValues.put(COLUMN_NAME_BATIMENT, row.getInt(COLUMN_NAME_BATIMENT));

        contentValues.put(COLUMN_NAME_PRIORITE, row.getString(COLUMN_NAME_PRIORITE));

        return contentValues;
    }

    public static Intervention cursorToIntervention(Cursor c) {

        Intervention intervention = new Intervention();
        intervention.setIntitule(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_INTITULE)));
        intervention.setDescriptif(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_DESCRIPTIF)));
        intervention.setBatiment(c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_BATIMENT)));
        intervention.setCategorie(c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_CATEGORIE)));
        intervention.setDomaine(c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_DOMAINE)));
        intervention.setService(c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SERVICE)));
        intervention.setCreated(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_CREATED)));
        intervention.setUpdated(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_UPDATED)));
        int transmis = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_TRANSMIS));
        Boolean trans;
        trans = transmis > 0;
        intervention.setTransmis(trans);
        intervention.setAffectation(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_AFFECTATION)));
        intervention.setDateSolution(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_DATE_SOLUTION)));
        intervention.setSolution(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_SOLUTION)));
        intervention.setUser(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_USER)));
        intervention.setRemoteId(c.getInt(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_REMOTE_ID)));
        intervention.setId(c.getInt(c.getColumnIndexOrThrow(BaseBdd.COLUMN_NAME_ID)));

        return intervention;
    }
}
