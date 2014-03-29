package com.github.pedrovgs.sample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.github.pedrovgs.sample.R;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.bt_places_sample)
    void openSimpleSampleActivity() {
        Intent intent = new Intent(this, PlacesSampleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_tv_shows_sample)
    void openTvShowsSampleActivity() {
        Intent intent = new Intent(this, TvShowsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_youtube_sample)
    void openYoutubeSampleActivity() {
        Intent intent = new Intent(this, YoutubeSampleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_video_sample)
    void openVideoSampleActivity() {
        Intent intent = new Intent(this, VideoSampleActivity.class);
        startActivity(intent);
    }
}
