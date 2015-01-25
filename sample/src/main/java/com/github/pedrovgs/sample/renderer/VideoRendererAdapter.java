package com.github.pedrovgs.sample.renderer;

import android.view.LayoutInflater;

import com.github.pedrovgs.sample.viewmodel.EpisodeViewModel;
import com.github.pedrovgs.sample.viewmodel.VideoViewModel;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

/**
 * Created by pedro on 1/24/15.
 */
public class VideoRendererAdapter extends RendererAdapter<VideoViewModel> {

    public VideoRendererAdapter(LayoutInflater layoutInflater, RendererBuilder rendererBuilder, AdapteeCollection<VideoViewModel> collection) {
        super(layoutInflater, rendererBuilder, collection);
    }

    /**
     * Override method used to update the EpisodeRenderer position.
     */
    @Override protected void updateRendererExtraValues(VideoViewModel content,
          Renderer<VideoViewModel> renderer, int position) {
        super.updateRendererExtraValues(content, renderer, position);
        VideoRenderer episodeRenderer = (VideoRenderer) renderer;
        episodeRenderer.setPosition(position);
    }

}
