package com.github.pedrovgs.sample.renderer.rendererbuilder;

import android.content.Context;

import com.github.pedrovgs.sample.renderer.VideoRenderer;
import com.github.pedrovgs.sample.viewmodel.VideoViewModel;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

import javax.inject.Inject;

/**
 * RendererBuilder implementation created to map VideoViewModel with VideoRenderer
 * implementations. More info in this link: {@link https://github.com/pedrovgs/Renderers}
 *
 * @author Pedro Paulo de Amorim
 */
public class VideoCollectionRendererBuilder extends RendererBuilder<VideoViewModel> {

  public VideoCollectionRendererBuilder(Collection<Renderer<VideoViewModel>> prototypes) {
    super(prototypes);
  }

  @Override protected Class getPrototypeClass(VideoViewModel tvShowViewModel) {
    return VideoRenderer.class;
  }
}
