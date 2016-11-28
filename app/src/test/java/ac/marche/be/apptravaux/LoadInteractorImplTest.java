package ac.marche.be.apptravaux;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ac.marche.be.apptravaux.lib.HttpApi;
import ac.marche.be.apptravaux.load.LoadInteractor;
import ac.marche.be.apptravaux.load.LoadInteractorImpl;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by jfsenechal on 28/11/16.
 * J
 */

public class LoadInteractorImplTest {

    private LoadInteractor.OnLoadFinishedListener mMockOnLoadFinishedListener;
    private LoadInteractorImpl loadInteractorImpl;
    @Mock
    HttpApi httpApi;

    @Before
    public void setUp() throws Exception {
        mMockOnLoadFinishedListener = Mockito.mock(LoadInteractor.OnLoadFinishedListener.class);
        loadInteractorImpl = new LoadInteractorImpl();
    }

    @Test
    public void shouldSuccessWithValidCredentials() {

        loadInteractorImpl.loadData("suivis", mMockOnLoadFinishedListener);
        loadInteractorImpl.onResponse(Call<List<Suivi>> call, Response<List<Suivi>> response);

        verify(mMockOnLoadFinishedListener, times(1)).onLoadSuccess();

        verify(mMockOnLoadFinishedListener, never()).onLoadError();
    }

}
