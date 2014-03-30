package com.github.pedrovgs.sample.renderer;

import android.content.Context;
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
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EpisodeRenderer extends Renderer<EpisodeViewModel> {

    private final Context context;

    @InjectView(R.id.tv_episode_number)
    TextView tv_episode_number;
    @InjectView(R.id.tv_episode_title)
    TextView tv_episode_title;
    @InjectView(R.id.tv_episode_publish_date)
    TextView tv_episode_publish_date;

    public EpisodeRenderer(Context context) {
        this.context = context;
    }

    @Override
    protected void setUpView(View view) {
        ButterKnife.inject(this, view);
    }

    @Override
    protected void hookListeners(View view) {
        //Empty
    }

    @Override
    protected View inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.episode_row, viewGroup, false);
    }

    @Override
    protected void render() {
        EpisodeViewModel episode = getContent();
        tv_episode_title.setText(episode.getTitle());
        tv_episode_publish_date.setText(episode.getPublishDate());
    }
}
