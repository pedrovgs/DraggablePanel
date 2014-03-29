package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.widget.GridView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.viewmodel.TvShowViewModel;
import com.pedrogomez.renderers.RendererAdapter;

import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowsActivity extends DIFragmentActivity {

    @Inject
    RendererAdapter<TvShowViewModel> adapter;

    @InjectView(R.id.gv_tv_shows)
    GridView gv_tv_shows;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows_sample);
        ButterKnife.inject(this);
        gv_tv_shows.setAdapter(adapter);
    }
}