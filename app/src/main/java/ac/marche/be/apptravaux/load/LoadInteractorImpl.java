package ac.marche.be.apptravaux.load;

import android.util.Log;

import java.util.List;

import ac.marche.be.apptravaux.lib.HttpApi;
import ac.marche.be.apptravaux.lib.HttpClient;
import ac.marche.be.apptravaux.model.Suivi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jfsenechal on 25/11/16.
 * J
 */

public class LoadInteractorImpl implements LoadInteractor, Callback<List<Suivi>> {

    private static final String TAG = "RETRODEMO";
    private HttpApi httpApi;
    OnLoadFinishedListener listener;

    @Override
    public void loadData(String type, OnLoadFinishedListener listener) {
        this.listener = listener;
        HttpApi httpApi =
                HttpClient.createService(HttpApi.class, "cptflux", "425HQANhUsHqCVM5");

        Call<List<Suivi>> call = httpApi.getSuivis(type);
        //asynchronous call
        call.enqueue(this);//this = callBack function
    }

    /**
     * override de call.enqueue callBack
     * listener.onSuccess() => LoadPresenterImpl onSuccess
     */
    @Override
    public void onResponse(Call<List<Suivi>> call, Response<List<Suivi>> response) {

        if (response.isSuccessful()) {
            List<Suivi> suivis = response.body();
            listener.onLoadSuccess(response);
        }
    }

    /**
     * override call.enqueue callBack
     * listener.onSuccess() => LoadPresenterImpl onSuccess
     */
    @Override
    public void onFailure(Call<List<Suivi>> call, Throwable t) {
        listener.onLoadError();
        Log.d(TAG, "oups1 : " + t.getMessage());
        Log.d(TAG, "oups3 : " + call.toString());
    }
}
