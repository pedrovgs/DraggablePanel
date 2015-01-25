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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.viewmodel.TvShowViewModel;
import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

/**
 * Renderer implementation used to render tv shows inside ListView or GridViews. More info in
 * this link: {@link https://github.com/pedrovgs/Renderers}
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowRenderer extends Renderer<TvShowViewModel> {

  private final Context context;

  @InjectView(R.id.iv_thumbnail) ImageView thumbnailImageView;
  @InjectView(R.id.tv_title) TextView titleTextView;
  @InjectView(R.id.tv_seasons_counter) TextView seasonsCounterTextView;

  public TvShowRenderer(Context context) {
    this.context = context;
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
    return layoutInflater.inflate(R.layout.tv_show_row, viewGroup, false);
  }

  /**
   * Render the TvShowViewModel information.
   */
  @Override protected void render() {
    TvShowViewModel tvShow = getContent();
    Picasso.with(context)
        .load(tvShow.getPoster())
        .placeholder(R.drawable.tv_show_placeholder)
        .into(thumbnailImageView);

    titleTextView.setText(tvShow.getTitle().toUpperCase());

    seasonsCounterTextView.setText(tvShow.getNumberOfSeasons() + " seasons");
  }
}
