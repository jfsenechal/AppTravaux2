package ac.marche.be.apptravaux.lib;

import java.util.List;

import ac.marche.be.apptravaux.model.Suivi;
import retrofit2.Response;

/**
 * Created by jfsenechal on 28/11/16.
 * J
 */

public interface InsertDb {

     interface OnInsertFinishedListener {
        void onInsertError();

        void onInsertSuccess();
    }

    public void insertSuivis(Response<List<Suivi>> response, OnInsertFinishedListener listener);
}
