
package ac.marche.be.apptravaux;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ac.marche.be.apptravaux.third.GetItemsUtil;
import ac.marche.be.apptravaux.third.ThirdPresenterImpl;
import ac.marche.be.apptravaux.third.ThirdView;

import static org.junit.Assert.assertNull;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by jfsenechal on 24/11/16.
 * J
 */

@RunWith(MockitoJUnitRunner.class)
public class ThirdPresenterTest {

    @Mock
    ThirdView view;
    @Mock
    GetItemsUtil getItemsUtil;

    ThirdPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ThirdPresenterImpl(view, getItemsUtil);
    }

    @Test
    public void checkIfShowsProgressOnResume() {
        presenter.onResume();
        verify(view, times(1)).showProgress();
    }

     @Test
    public void checkIfViewIsReleasedOnDestroy() {
        presenter.onDestroy();
        assertNull(presenter.getThirdView());
    }
}
