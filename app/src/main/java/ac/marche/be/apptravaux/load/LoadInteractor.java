package ac.marche.be.apptravaux.load;

import java.util.List;

import ac.marche.be.apptravaux.model.Suivi;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jfsenechal on 25/11/16.
 * J
 */

public interface LoadInteractor {

    interface OnLoadFinishedListener {
        void onLoadError();

        void onLoadSuccess(Response<List<Suivi>> response);
    }

    void loadData(String type, OnLoadFinishedListener listener);

}
