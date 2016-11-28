package ac.marche.be.apptravaux.third;

/**
 * Created by jfsenechal on 24/11/16.
 */

public interface ThirdPresenter {
    /**
     * Cette methode sera appele lors de l'activite resume
     * et chargera les donnees
     */
    void onResume();

    /**
     *
     * @param position quel ligne
     */
    void onItemClicked(int position);

    /**
     *
     */
    void onDestroy();
}
