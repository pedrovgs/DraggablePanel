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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.viewmodel.VideoViewModel;
import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Renderer implementation used to render video inside ListView or GridViews. More info in
 * this link: {@link https://github.com/pedrovgs/Renderers}
 *
 * @author Pedro Paulo de Amorim.
 */
public class VideoRenderer extends Renderer<VideoViewModel> {

    private Context context;

    @InjectView(R.id.frame_image) ImageView frameImage;
    @InjectView(R.id.frame_title) TextView frameTitle;

    private int position;

    public VideoRenderer(Context context) {
        this.context = context;
    }

    /**
     * Configure the position associated to this renderer.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Apply ButterKnife inject method to support view injections.
     */
    @Override
    protected void setUpView(View view) {
        ButterKnife.inject(this, view);
    }

    @Override
    protected void hookListeners(View rootView) {
        //Empty
    }

    /**
     * Inflate the layout associated to this renderer
     */
    @Override
    protected View inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
      return layoutInflater.inflate(R.layout.video_row, viewGroup, false);
    }

    /**
     * Render the VideoViewModel information.
     */
    @Override
    protected void render() {
        VideoViewModel item = getContent();
        frameTitle.setText(context.getResources().getString(item.getTitle()));
        Picasso.with(context)
                .load(item.getImage())
                .placeholder(R.color.soft_white)
                .into(frameImage);
    }
}
