package ac.marche.be.apptravaux.load;

import android.view.View;

/**
 * Created by jfsenechal on 25/11/16.
 * J
 */

public interface LoadView {
    void showProgress();

    void hideProgress();

     void showMessage(String message);
}
