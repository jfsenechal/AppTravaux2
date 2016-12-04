package ac.marche.be.apptravaux.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jfsenechal on 04-12-16.
 * Ce module sert à fournir le contexte de l'application
 */
@Module
public class ContextModule {

    private final Context context;

    /**
     * Nous ajoutons volontairement un constructeur qui prend un Context en entrée,
     * afin de lui fournir au runtime lors de la création de l'Application
     *
     * @param context l'application créée
     */
    public ContextModule(Context context) {
        this.context = context;
    }

    /**
     * Permet à Dagger2 de récupérer le context
     *
     * @return le context de l'application
     */
    @Provides
    Context provideContext() {
        return context;
    }

}
