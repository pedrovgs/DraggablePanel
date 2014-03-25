package com.github.pedrovgs.sample.renderer.rendererbuilder;

import com.github.pedrovgs.sample.renderer.PlaceRenderer;
import com.github.pedrovgs.sample.viewmodel.PlaceViewModel;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class PlacesCollectionRendererBuilder extends RendererBuilder<PlaceViewModel> {

    public PlacesCollectionRendererBuilder(Collection<Renderer<PlaceViewModel>> prototypes) {
        super(prototypes);
    }

    @Override
    protected Class getPrototypeClass(PlaceViewModel placeViewModel) {
        return PlaceRenderer.class;
    }
}
