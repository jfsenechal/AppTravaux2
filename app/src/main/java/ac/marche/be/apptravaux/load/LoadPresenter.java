package ac.marche.be.apptravaux.load;

/**
 * Created by jfsenechal on 25/11/16.
 * J
 */

public interface LoadPresenter {

    /**
     * Cette methode sera appele lors de l'activite resume
     * et chargera les donnees
     */
    void onResume();

    /**
     *
     */
    void onDestroy();


}
