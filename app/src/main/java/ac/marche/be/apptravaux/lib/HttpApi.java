package ac.marche.be.apptravaux.lib;

import java.util.List;

import ac.marche.be.apptravaux.model.Suivi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jfsenechal on 25/11/16.
 *
 */

public interface HttpApi {

    @GET("users/{user}/repos")
    Call<List<Suivi>> listRepos(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<Suivi>> getRepos(@Path("user") String user);

    @GET("flux/{meta}")
    Call<List<Suivi>> getSuivis(@Path("meta") String user);
}

