package com.github.pedrovgs.sample.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.DraggableView;
import com.github.pedrovgs.sample.R;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class VideoSampleActivity extends FragmentActivity {

    public static final String APPLICATION_RAW_PATH = "android.resource://com.github.pedrovgs.sample/";
    
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
        Uri path = Uri.parse(APPLICATION_RAW_PATH + R.raw.video);
        MediaController mediaController = new MediaController(this);
        video_view.setMediaController(mediaController);
        video_view.setVideoURI(path);
        video_view.start();

    }


}
