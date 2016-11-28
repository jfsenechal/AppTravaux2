package ac.marche.be.apptravaux.third;

import java.util.List;

/**
 * Created by jfsenechal on 24/11/16.
 * j
 */

public interface GetItemsUtil {

    interface OnFinishedListener {
        void onFinished(List<String> items);
    }

    void findItems(OnFinishedListener listener);
}
