package ac.marche.be.apptravaux.moi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jfsenechal on 04-12-16.
 */
@Module
public class NetworkApiModule {
    @Provides
    @Singleton
    public NetworkApi getNetwork(){
        return new NetworkApi();
    }
}