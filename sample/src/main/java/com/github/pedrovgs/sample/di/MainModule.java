package com.github.pedrovgs.sample.di;

import android.content.Context;
import com.github.pedrovgs.sample.DraggablePanelApplication;
import com.github.pedrovgs.sample.activity.CitiesSampleActivity;
import dagger.Module;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
@Module(injects = {CitiesSampleActivity.class,
        DraggablePanelApplication.class})
public class MainModule {

    private final Context context;

    public MainModule(Context context) {
        this.context = context;
    }
}
