package com.github.pedrovgs.sample.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.viewmodel.TvShowViewModel;
import com.pedrogomez.renderers.Renderer;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowRenderer extends Renderer<TvShowViewModel> {

    private final Context context;

    public TvShowRenderer(Context context) {
        this.context = context;
    }

    @Override
    protected void setUpView(View view) {
        ButterKnife.inject(this, view);
    }

    @Override
    protected void hookListeners(View view) {

    }

    @Override
    protected View inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.place_row, viewGroup, false);
    }

    @Override
    protected void render() {

    }
}
