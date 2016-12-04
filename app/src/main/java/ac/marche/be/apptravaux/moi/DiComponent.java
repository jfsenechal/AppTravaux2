package ac.marche.be.apptravaux.moi;

import javax.inject.Singleton;

import ac.marche.be.apptravaux.main.MainActivity;
import dagger.Component;

/**
 * Created by jfsenechal on 04-12-16.
 */
@Singleton
@Component(modules = {NetworkApiModule.class})
public interface DiComponent {
    // to update the fields in your activities
    void inject(MainActivity activity);
}
