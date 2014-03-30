package com.github.pedrovgs.sample.renderer;

import android.view.LayoutInflater;
import com.github.pedrovgs.sample.viewmodel.EpisodeViewModel;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EpisodeRendererAdapter extends RendererAdapter<EpisodeViewModel> {

    public EpisodeRendererAdapter(LayoutInflater layoutInflater, RendererBuilder rendererBuilder, AdapteeCollection<EpisodeViewModel> collection) {
        super(layoutInflater, rendererBuilder, collection);
    }

    @Override
    protected void updateRendererExtraValues(EpisodeViewModel content, Renderer<EpisodeViewModel> renderer, int position) {
        super.updateRendererExtraValues(content, renderer, position);
        EpisodeRenderer episodeRenderer = (EpisodeRenderer) renderer;
        episodeRenderer.setPosition(position);
    }
}
