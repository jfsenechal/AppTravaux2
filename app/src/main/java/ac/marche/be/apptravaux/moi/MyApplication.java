package ac.marche.be.apptravaux.moi;

import android.app.Application;


/**
 * Created by jfsenechal on 04-12-16.
 */
public class MyApplication extends Application {
    DiComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerDiComponent.builder().build();
    }

    public DiComponent getComponent() {
        return component;
    }
}
