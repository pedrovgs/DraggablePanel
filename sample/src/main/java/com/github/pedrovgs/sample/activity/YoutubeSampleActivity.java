package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.sample.R;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class YoutubeSampleActivity extends FragmentActivity {

    @InjectView(R.id.lv_videos)
    ListView lv_videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_sample);
        ButterKnife.inject(this);
    }
}
