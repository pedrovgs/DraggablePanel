package com.github.pedrovgs.sample.renderer.rendererbuilder;

import com.github.pedrovgs.sample.viewmodel.EpisodeViewModel;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EpisodeRendererBuilder extends RendererBuilder<EpisodeViewModel> {

    public EpisodeRendererBuilder(Collection<Renderer<EpisodeViewModel>> prototypes) {
        super(prototypes);
    }

    @Override
    protected Class getPrototypeClass(EpisodeViewModel episodeViewModel) {
        return null;
    }
}
