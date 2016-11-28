package ac.marche.be.apptravaux;

import java.util.List;

import ac.marche.be.apptravaux.lib.HttpApi;
import ac.marche.be.apptravaux.model.Suivi;
import retrofit2.Call;
import retrofit2.http.Path;

/**
 * Created by jfsenechal on 28/11/16.
 * J
 */

public class MockHttpApiTest implements HttpApi {

    private final BehaviorDelegate<HttpApi> delegate;
    MockWebServer mockWebServer = new MockWebServer();

    @Override
    public Call<List<Suivi>> listRepos(@Path("user") String user) {
        return null;
    }

    @Override
    public Call<List<Suivi>> getRepos(@Path("user") String user) {
        return null;
    }

    @Override
    public Call<List<Suivi>> getSuivis(@Path("meta") String user) {
        return null;
    }
}
