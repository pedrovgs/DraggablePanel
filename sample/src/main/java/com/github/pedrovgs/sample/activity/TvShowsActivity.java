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
package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggableView;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.renderer.EpisodeRenderer;
import com.github.pedrovgs.sample.renderer.EpisodeRendererAdapter;
import com.github.pedrovgs.sample.renderer.rendererbuilder.EpisodeRendererBuilder;
import com.github.pedrovgs.sample.viewmodel.EpisodeViewModel;
import com.github.pedrovgs.sample.viewmodel.TvShowViewModel;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowsActivity extends DIFragmentActivity {

    @Inject
    RendererAdapter<TvShowViewModel> adapter;

    @InjectView(R.id.gv_tv_shows)
    GridView gv_tv_shows;
    @InjectView(R.id.iv_fan_art)
    ImageView iv_fan_art;
    @InjectView(R.id.lv_episodes)
    ListView lv_episodes;
    @InjectView(R.id.draggable_view)
    DraggableView draggableView;
    TextView header;

    private TvShowViewModel tvShowSelected;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows_sample);
        ButterKnife.inject(this);
        initializeDraggableView();
        initializeGridView();
        hookListeners();
    }

    @OnClick(R.id.iv_fan_art)
    void onFanArtClicked() {
        Toast.makeText(this, tvShowSelected.getTitle(), Toast.LENGTH_LONG).show();
    }

    private void initializeDraggableView() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                draggableView.setVisibility(View.GONE);
                draggableView.closeToRight();
            }
        }, 10);
    }

    private void initializeGridView() {
        gv_tv_shows.setAdapter(adapter);
        gv_tv_shows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TvShowViewModel tvShow = adapter.getItem(position);
                tvShowSelected = tvShow;
                Picasso.with(getBaseContext()).load(tvShow.getFanArt()).placeholder(R.drawable.tv_show_placeholder).into(iv_fan_art);
                renderEpisodesHeader(tvShow);
                renderEpisodes(tvShow);
                draggableView.setVisibility(View.VISIBLE);
                draggableView.maximize();
            }
        });
    }


    private void hookListeners() {
        /*
         * This will work in API Level 8 once this sample project uses ActionBarCompat or ActionBarSherlock project.
         */
        draggableView.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                if (tvShowSelected != null) {
                    getActionBar().setTitle(tvShowSelected.getTitle());
                }
            }

            @Override
            public void onMinimized() {
                if (tvShowSelected != null) {
                    getActionBar().setTitle(tvShowSelected.getTitle());
                }
            }

            @Override
            public void onClosedToLeft() {
                tvShowSelected = null;
                getActionBar().setTitle(getString(R.string.tv_shows_sample_activity_title));
            }

            @Override
            public void onClosedToRight() {
                tvShowSelected = null;
                getActionBar().setTitle(getString(R.string.tv_shows_sample_activity_title));

            }
        });
    }

    private void renderEpisodes(final TvShowViewModel tvShow) {
        List<Renderer<EpisodeViewModel>> episodeRenderers = new LinkedList<Renderer<EpisodeViewModel>>();
        episodeRenderers.add(new EpisodeRenderer(this));
        EpisodeRendererBuilder episodeRendererBuilder = new EpisodeRendererBuilder(episodeRenderers);
        EpisodeRendererAdapter episodesAdapter = new EpisodeRendererAdapter(getLayoutInflater(), episodeRendererBuilder, tvShow.getEpisodes());
        lv_episodes.setAdapter(episodesAdapter);
    }

    private void renderEpisodesHeader(TvShowViewModel tvShow) {
        lv_episodes.removeHeaderView(header);
        header = (TextView) getLayoutInflater().inflate(R.layout.episode_header, null);
        header.setText(tvShow.getTitle().toUpperCase() + " - SEASON 1");
        lv_episodes.setAdapter(null);
        lv_episodes.addHeaderView(header);
        lv_episodes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                EpisodeViewModel episodeViewModel = tvShowSelected.getEpisodes().get(position);
                Toast.makeText(getBaseContext(), tvShowSelected.getTitle() + " - " + episodeViewModel.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

}