package com.github.pedrovgs.sample.renderer.rendererbuilder;

import com.github.pedrovgs.sample.renderer.TvShowRenderer;
import com.github.pedrovgs.sample.viewmodel.TvShowViewModel;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowCollectionRendererBuilder extends RendererBuilder<TvShowViewModel> {

    public TvShowCollectionRendererBuilder(Collection<Renderer<TvShowViewModel>> prototypes) {
        super(prototypes);
    }

    @Override
    protected Class getPrototypeClass(TvShowViewModel tvShowViewModel) {
        return TvShowRenderer.class;
    }
}
