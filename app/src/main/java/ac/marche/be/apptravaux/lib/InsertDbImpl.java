package ac.marche.be.apptravaux.lib;

import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import java.util.List;

import ac.marche.be.apptravaux.database.BaseBdd;
import ac.marche.be.apptravaux.model.Suivi;
import ac.marche.be.apptravaux.provider.TravauxProvider;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by jfsenechal on 28/11/16.
 * J
 */

public class InsertDbImpl implements InsertDb {

    OnInsertFinishedListener listener;

    @Override
    public void insertSuivis(Response<List<Suivi>> response, OnInsertFinishedListener listener) {

        TravauxProvider travauxProvider = new TravauxProvider();
        Uri uri = Suivi.CONTENT_URI;

        for (int i = 0; i < response.body().size(); i++) {
            Suivi suivi = new Suivi();
            ContentValues contentValues = suivi.createContentValues(response);
            int remoteId = response.body().get(i).getRemoteId();

            String where = BaseBdd.COLUMN_NAME_REMOTE_ID + " = " + remoteId;
            travauxProvider.query(uri, null, where, null, null);

            boolean exist = travauxProvider.checkExist(uri, remoteId);

            if (!exist) {
                Uri result = travauxProvider.insert(uri, contentValues);
                Log.d(TAG, "insert" + response.body());
            } else {
                /**
                 * @TODO set update suivis
                 */
            }
        }

       // listener.onInsertError();
        listener.onInsertSuccess();
    }
}
