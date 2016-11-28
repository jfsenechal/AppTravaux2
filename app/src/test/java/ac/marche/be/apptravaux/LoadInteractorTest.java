package ac.marche.be.apptravaux;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import ac.marche.be.apptravaux.load.LoadInteractor;
import ac.marche.be.apptravaux.main.FindItemsInteractor;
import ac.marche.be.apptravaux.main.MainPresenterImpl;
import ac.marche.be.apptravaux.main.MainView;
import ac.marche.be.apptravaux.model.Suivi;
import retrofit2.Response;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by jfsenechal on 28-11-16.
 * J
 */


@RunWith(MockitoJUnitRunner.class)
public class LoadInteractorTest {
    @Mock
    MainView view;
    @Mock
    LoadInteractor interactor;
    @Mock
    LoadInteractor.OnLoadFinishedListener onLoadFinishedListener;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void checkIfShowsProgressOnResume() {

        Suivi suivi = new Suivi();
        suivi.setId(1);
        suivi.setDescriptif("Blbalba");
        List<Suivi> suivis = new ArrayList<Suivi>();
        suivis.add(suivi);



        Response<List<Suivi>> response = new Response(suivis);
        response.body().add(suivis);

        interactor.loadData("suivis",onLoadFinishedListener);
        verify(onLoadFinishedListener, times(1)).onLoadSuccess(suivis);
        verify(onLoadFinishedListener, never()).onLoadError();
    }
}
