package com.github.pedrovgs.sample.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.pedrovgs.sample.viewmodel.EpisodeViewModel;
import com.pedrogomez.renderers.Renderer;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EpisodeRenderer extends Renderer<EpisodeViewModel> {

    private final Context context;

    public EpisodeRenderer(Context context) {
        this.context = context;
    }

    @Override
    protected void setUpView(View view) {

    }

    @Override
    protected void hookListeners(View view) {

    }

    @Override
    protected View inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return null;
    }

    @Override
    protected void render() {

    }
}
