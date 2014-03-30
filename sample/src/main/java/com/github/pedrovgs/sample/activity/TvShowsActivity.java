package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows_sample);
        ButterKnife.inject(this);
        initializeDraggableView();
        initializeGridView();
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
                Picasso.with(getBaseContext()).load(tvShow.getFanArt()).placeholder(R.drawable.tv_show_placeholder).into(iv_fan_art);
                renderEpisodesHeader(tvShow);
                renderEpisodes(tvShow);
                draggableView.setVisibility(View.VISIBLE);
                draggableView.maximize();
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
    }

}