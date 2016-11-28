
package ac.marche.be.apptravaux;

import android.util.Base64;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import ac.marche.be.apptravaux.lib.HttpApi;
import ac.marche.be.apptravaux.lib.HttpClient;
import ac.marche.be.apptravaux.lib.InsertDb;
import ac.marche.be.apptravaux.load.LoadInteractor;
import ac.marche.be.apptravaux.load.LoadInteractorImpl;
import ac.marche.be.apptravaux.load.LoadPresenterImpl;
import ac.marche.be.apptravaux.load.LoadView;
import ac.marche.be.apptravaux.model.Suivi;
import retrofit2.Call;
import retrofit2.Response;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by jfsenechal on 25/11/16.
 * J
 */

@RunWith(MockitoJUnitRunner.class)
public class LoadPresenterTest {
    @Mock
    LoadView view;
    @Mock
    LoadInteractor loadInteractor;
    @Mock
    Base64 Base64;
    @Mock
    HttpClient httpClient;
    @Mock
    InsertDb insertDb;

    private LoadPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new LoadPresenterImpl(view);
        loadInteractor = new LoadInteractorImpl();
    }

    @Test
    public void checkIfShowsProgressOnResume() {
        presenter.onResume();
        verify(view, times(1)).showProgress();
    }

    @Test
    public void checkIfShowsMessageOnSuccess() {
        presenter.onResume();

        Response<List<Suivi>> response;
        HttpApi httpApi =
                HttpClient.createService(HttpApi.class, "cptflux", "425HQANhUsHqCVM5");

        Call<List<Suivi>> call = httpApi.getSuivis("suivis");
        try {
            response = call.execute();
            String result = call.execute().body().toString();
            presenter.onLoadSuccess(response);
            verify(view, times(1)).showMessage("Load ok");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void checkIfShowsMessageOnError() {
        presenter.onLoadError();
        verify(view, times(1)).showMessage(anyString());
    }
}
