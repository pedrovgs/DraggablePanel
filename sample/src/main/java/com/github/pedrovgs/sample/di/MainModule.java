package com.github.pedrovgs.sample.di;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import com.github.pedrovgs.sample.DraggablePanelApplication;
import com.github.pedrovgs.sample.activity.PlacesSampleActivity;
import com.github.pedrovgs.sample.activity.TvShowsActivity;
import com.github.pedrovgs.sample.renderer.PlaceRenderer;
import com.github.pedrovgs.sample.renderer.TvShowRenderer;
import com.github.pedrovgs.sample.renderer.rendererbuilder.PlacesCollectionRendererBuilder;
import com.github.pedrovgs.sample.renderer.rendererbuilder.TvShowCollectionRendererBuilder;
import com.github.pedrovgs.sample.viewmodel.PlaceCollectionViewModel;
import com.github.pedrovgs.sample.viewmodel.PlaceViewModel;
import com.github.pedrovgs.sample.viewmodel.TvShowCollectionViewModel;
import com.github.pedrovgs.sample.viewmodel.TvShowViewModel;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;
import dagger.Module;
import dagger.Provides;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
@Module(injects = {
        PlacesSampleActivity.class,
        TvShowsActivity.class,
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
    PlacesCollectionRendererBuilder providePlaceCollectionRendererBuilder(Context context) {
        List<Renderer<PlaceViewModel>> prototypes = new LinkedList<Renderer<PlaceViewModel>>();
        prototypes.add(new PlaceRenderer(context));
        return new PlacesCollectionRendererBuilder(prototypes);
    }

    @Provides
    RendererAdapter<PlaceViewModel> providePlacesRendererAdapter(LayoutInflater layoutInflater, PlacesCollectionRendererBuilder placesCollectionRendererBuilder, PlaceCollectionViewModel placeCollectionViewModel) {
        return new RendererAdapter<PlaceViewModel>(layoutInflater, placesCollectionRendererBuilder, placeCollectionViewModel);
    }

    @Provides
    TvShowCollectionRendererBuilder provideTvShowCollectionRendererBuilder(Context context) {
        List<Renderer<TvShowViewModel>> prototypes = new LinkedList<Renderer<TvShowViewModel>>();
        prototypes.add(new TvShowRenderer(context));
        return new TvShowCollectionRendererBuilder(prototypes);
    }

    @Provides
    RendererAdapter<TvShowViewModel> provideTvShowRendererAdapter(LayoutInflater layoutInflater, TvShowCollectionRendererBuilder tvShowCollectionRendererBuilder, TvShowCollectionViewModel tvShowCollectionViewModel) {
        return new RendererAdapter<TvShowViewModel>(layoutInflater, tvShowCollectionRendererBuilder, tvShowCollectionViewModel);
    }
}
