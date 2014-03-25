package com.github.pedrovgs.sample.di;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import com.github.pedrovgs.sample.DraggablePanelApplication;
import com.github.pedrovgs.sample.activity.CitiesSampleActivity;
import com.github.pedrovgs.sample.renderer.CityRenderer;
import com.github.pedrovgs.sample.renderer.rendererbuilder.CityCollectionRendererBuilder;
import com.github.pedrovgs.sample.viewmodel.CityCollectionViewModel;
import com.github.pedrovgs.sample.viewmodel.CityViewModel;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;
import dagger.Module;
import dagger.Provides;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
@Module(injects = {CitiesSampleActivity.class,
        DraggablePanelApplication.class})
public class MainModule {

    private final Application application;

    public MainModule(Application application) {
        this.application = application;
    }

    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(application.getBaseContext());
    }

    @Provides
    Context provideContext() {
        return this.application.getBaseContext();
    }

    @Provides
    CityCollectionRendererBuilder provideCityCollectionRendererBuilder(Context context) {
        List<Renderer<CityViewModel>> prototypes = new LinkedList<Renderer<CityViewModel>>();
        prototypes.add(new CityRenderer(context));
        return new CityCollectionRendererBuilder(prototypes);
    }

    @Provides
    RendererAdapter<CityViewModel> provideCitiesRendererAdapter(LayoutInflater layoutInflater, CityCollectionRendererBuilder cityCollectionRendererBuilder, CityCollectionViewModel cityCollectionViewModel) {
        return new RendererAdapter<CityViewModel>(layoutInflater, cityCollectionRendererBuilder, cityCollectionViewModel);
    }
}
