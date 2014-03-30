package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.DraggableView;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.viewmodel.TvShowViewModel;
import com.pedrogomez.renderers.RendererAdapter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowsActivity extends DIFragmentActivity {

    @Inject
    RendererAdapter<TvShowViewModel> adapter;

    @InjectView(R.id.gv_tv_shows)
    GridView gv_tv_shows;
    @InjectView(R.id.iv_poster)
    ImageView iv_poster;
    @InjectView(R.id.draggable_view)
    DraggableView draggableView;

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
                draggableView.minimize();
            }
        }, 10);
    }

    private void initializeGridView() {
        gv_tv_shows.setAdapter(adapter);
        gv_tv_shows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TvShowViewModel tvShow = adapter.getItem(position);
                Picasso.with(getBaseContext()).load(tvShow.getFanArt()).placeholder(R.drawable.tv_show_placeholder).into(iv_poster);
                draggableView.setVisibility(View.VISIBLE);
                draggableView.maximize();
            }
        });
    }

}