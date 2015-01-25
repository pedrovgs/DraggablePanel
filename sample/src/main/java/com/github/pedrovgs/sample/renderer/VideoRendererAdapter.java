/*
 * Copyright (C) 2015 Pedro Paulo de Amorim.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.sample.renderer;

import android.view.LayoutInflater;

import com.github.pedrovgs.sample.viewmodel.VideoViewModel;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

/**
 * RendererAdapter implementation used to render video item inside ListView or GridViews and to
 * update the Renderer information with the position to render. More info in this link: {@link
 * https://github.com/pedrovgs/Renderers}
 *
 * @author Pedro Paulo de Amorim.
 */
public class VideoRendererAdapter extends RendererAdapter<VideoViewModel> {

  public VideoRendererAdapter(LayoutInflater layoutInflater, RendererBuilder rendererBuilder, AdapteeCollection<VideoViewModel> collection) {
    super(layoutInflater, rendererBuilder, collection);
  }

  /**
   * Override method used to update the VideoRenderer position.
   */
  @Override protected void updateRendererExtraValues(VideoViewModel content,
    Renderer<VideoViewModel> renderer, int position) {
    super.updateRendererExtraValues(content, renderer, position);
    VideoRenderer episodeRenderer = (VideoRenderer) renderer;
    episodeRenderer.setPosition(position);
  }

}
