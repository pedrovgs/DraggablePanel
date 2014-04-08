/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import com.github.pedrovgs.DraggableListener;
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
    private static final String VIDEO_TITLE = "The Amazing Spider-Man 2: Rise of Electro";

    @InjectView(R.id.draggable_view)
    DraggableView draggableView;
    @InjectView(R.id.video_view)
    VideoView videoView;
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
        hookDraggableViewListener();
    }

    private void hookDraggableViewListener() {
        draggableView.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
            }

            @Override
            public void onMinimized() {

            }

            @Override
            public void onClosedToLeft() {
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
            }

            @Override
            public void onClosedToRight() {
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
            }
        });
    }

    private void initializeVideoView() {
        Uri path = Uri.parse(APPLICATION_RAW_PATH + R.raw.video);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(path);
        videoView.start();
    }

    private void initializePoster() {
        Picasso.with(this).load(VIDEO_POSTER).placeholder(R.drawable.spiderman_placeholder).into(iv_poster);
        Picasso.with(this).load(VIDEO_THUMBNAIL).placeholder(R.drawable.spiderman_placeholder).into(iv_thumbnail);
    }

    @OnClick(R.id.iv_thumbnail)
    void onThubmnailClicked() {
        Toast.makeText(this, VIDEO_TITLE, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.iv_poster)
    void onPosterClicked() {
        draggableView.maximize();
    }
}
