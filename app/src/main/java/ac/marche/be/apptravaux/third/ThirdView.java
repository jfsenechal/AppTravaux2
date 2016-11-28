package ac.marche.be.apptravaux.third;

import java.util.List;

/**
 * Created by jfsenechal on 24/11/16.
 * J
 */

public interface ThirdView {
    void showProgress();

    void hideProgress();

    void setItems(List<String> items);

    void showMessage(String message);
}
