package com.github.pedrovgs.sample;

import android.app.Application;
import com.github.pedrovgs.sample.di.MainModule;
import dagger.ObjectGraph;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DraggablePanelApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        MainModule mainModule = new MainModule(getBaseContext());
        objectGraph = ObjectGraph.create(mainModule);
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }
}
