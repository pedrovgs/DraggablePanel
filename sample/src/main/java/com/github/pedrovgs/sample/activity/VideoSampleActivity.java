package com.github.pedrovgs.sample.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.github.pedrovgs.DraggableView;
import com.github.pedrovgs.sample.R;
import com.squareup.picasso.Picasso;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class VideoSampleActivity extends FragmentActivity {

    private static final String APPLICATION_RAW_PATH = "android.resource://com.github.pedrovgs.sample/";
    private static final String VIDEO_POSTER = "http://wac.450f.edgecastcdn.net/80450F/screencrush.com/files/2013/11/the-amazing-spider-man-2-poster-rhino.jpg";
    private static final String VIDEO_THUMBNAIL = "http://wac.450f.edgecastcdn.net/80450F/screencrush.com/files/2013/11/the-amazing-spider-man-2-poster-green-goblin.jpg";

    @InjectView(R.id.draggable_view)
    DraggableView draggable_view;
    @InjectView(R.id.video_view)
    VideoView video_view;
    @InjectView(R.id.iv_thumbnail)
    ImageView iv_thumbnail;
    @InjectView(R.id.iv_poster)
    ImageView iv_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_sample);
        ButterKnife.inject(this);
        initializeVideoView();
        initializePoster();
    }

    private void initializeVideoView() {
        Uri path = Uri.parse(APPLICATION_RAW_PATH + R.raw.video);
        MediaController mediaController = new MediaController(this);
        video_view.setMediaController(mediaController);
        video_view.setVideoURI(path);
        video_view.start();
    }

    private void initializePoster() {
        Picasso.with(this).load(VIDEO_POSTER).into(iv_poster);
        Picasso.with(this).load(VIDEO_THUMBNAIL).into(iv_thumbnail);
    }

    @OnClick(R.id.video_view)
    void onVideoViewClicked() {
        Toast.makeText(this, "Video view clicked", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.iv_thumbnail)
    void onThubmnailClicked() {
        Toast.makeText(this, "Thumbnail view clicked", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.iv_poster)
    void onPosterClicked() {
        draggable_view.maximize();
    }
}
