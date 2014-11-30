/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.viewmodel.EpisodeViewModel;
import com.pedrogomez.renderers.Renderer;

/**
 * Renderer implementation used to render episodes inside ListView or GridViews. More info in
 * this link: {@link https://github.com/pedrovgs/Renderers}
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EpisodeRenderer extends Renderer<EpisodeViewModel> {

  @InjectView(R.id.tv_episode_number) TextView episodeNumberTextView;
  @InjectView(R.id.tv_episode_title) TextView episodeTitleTextView;
  @InjectView(R.id.tv_episode_publish_date) TextView episodeDateTextView;

  private int position;

  /**
   * Configure the position associated to this renderer.
   */
  public void setPosition(int position) {
    this.position = position;
  }

  /**
   * Apply ButterKnife inject method to support view injections.
   */
  @Override protected void setUpView(View view) {
    ButterKnife.inject(this, view);
  }

  @Override protected void hookListeners(View view) {
    //Empty
  }

  /**
   * Inflate the layout associated to this renderer
   */
  @Override protected View inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
    return layoutInflater.inflate(R.layout.episode_row, viewGroup, false);
  }

  /**
   * Render the EpisodeViewModel information.
   */
  @Override protected void render() {
    EpisodeViewModel episode = getContent();
    episodeNumberTextView.setText(String.format("%02d", position + 1));
    episodeTitleTextView.setText(episode.getTitle());
    episodeDateTextView.setText(episode.getPublishDate());
  }
}
