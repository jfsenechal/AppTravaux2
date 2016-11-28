
package ac.marche.be.apptravaux.third;

import java.util.List;

/**
 * Created by jfsenechal on 24/11/16.
 * j
 */

public class ThirdPresenterImpl implements ThirdPresenter, GetItemsUtil.OnFinishedListener {

    private ThirdView thirdView;
    private GetItemsUtil getItemsUtil;

    public ThirdPresenterImpl(ThirdView thirdView, GetItemsUtil getItemsUtil) {
        this.thirdView = thirdView;
        this.getItemsUtil = getItemsUtil;
    }

    /**
     * j'affiche la barre de progression
     * et je vais chercher le data
     */
    @Override
    public void onResume() {
        if (thirdView != null) {
            thirdView.showProgress();
        }
        /**
         * find items a une interface call back OnFinishedListener
         * qui appel onfinished
         */
        getItemsUtil.findItems(this);
    }

    @Override
    public void onItemClicked(int position) {

    }

    /**
     * override de presenter
     */
    @Override
    public void onDestroy() {
        thirdView = null;
    }

    /**
     * override de GetItemsUtil.OnFinishedListener
     * appeler automatiquement apres un findItems
     * Cache la barre de progression
     * et attribue les items a la vue
     *
     * @param items
     */
    @Override
    public void onFinished(List<String> items) {
        if (thirdView != null) {
            thirdView.hideProgress();
            thirdView.setItems(items);
        }
    }

    public ThirdView getThirdView() {
        return this.thirdView;
    }
}
