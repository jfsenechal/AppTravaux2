package ac.marche.be.apptravaux.load;

import android.util.Log;

import java.util.List;

import ac.marche.be.apptravaux.lib.HttpClient;
import ac.marche.be.apptravaux.lib.InsertDb;
import ac.marche.be.apptravaux.lib.InsertDbImpl;
import ac.marche.be.apptravaux.model.Suivi;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by jfsenechal on 25/11/16.
 * J
 */

public class LoadPresenterImpl implements LoadPresenter, LoadInteractor.OnLoadFinishedListener, InsertDb.OnInsertFinishedListener {

    LoadView loadView;
    HttpClient httpClient;
    LoadInteractor loadInteractor;

    public LoadPresenterImpl(LoadView loadView) {
        this.loadView = loadView;
        loadInteractor = new LoadInteractorImpl();
    }

    /**
     * LoadPresenter
     */
    @Override
    public void onResume() {
        if (loadView != null)
            loadView.showProgress();

        loadInteractor.loadData("suivis", this);
    }

    /**
     * LoadPresenter
     */
    @Override
    public void onDestroy() {
        loadView = null;
    }

    /**
     * Override OnLoadFinishedListener
     */
    @Override
    public void onLoadError() {
        if (loadView != null) {
            loadView.hideProgress();
            loadView.showMessage("Rate");
        }
    }

    /**
     * Override OnLoadFinishedListener
     */
    @Override
    public void onLoadSuccess(Response<List<Suivi>> response) {
        int code = response.code();
        if (response.isSuccessful()) {

            if (loadView != null) {
                loadView.hideProgress();
                loadView.showMessage("Load ok");
            }

            Log.d(TAG, "load ok" + response.body());
            InsertDbImpl insertDb = new InsertDbImpl();
            insertDb.insertSuivis(response, this);
        } else {
            Log.d(TAG, "load ko " + code + response.body());
            if (loadView != null) {
                loadView.hideProgress();
                loadView.showMessage("Load ko");
            }
        }
    }

    @Override
    public void onInsertError() {

    }

    @Override
    public void onInsertSuccess() {
        if (loadView != null) {
            loadView.hideProgress();
            loadView.showMessage("Insert Reussi");
        }
    }
}
