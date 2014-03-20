package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.VideoView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.DraggableView;
import com.github.pedrovgs.sample.R;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class VideoSampleActivity extends FragmentActivity {

    @InjectView(R.id.lv_videos)
    ListView lv_videos;
    @InjectView(R.id.draggable_view)
    DraggableView draggable_view;
    @InjectView(R.id.video_view)
    VideoView video_view;
    @InjectView(R.id.iv_thumbnail)
    ImageView iv_thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_sample);
        ButterKnife.inject(this);
    }


}
