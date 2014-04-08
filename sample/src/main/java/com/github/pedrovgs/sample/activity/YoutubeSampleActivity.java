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

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggablePanel;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.fragment.MoviePosterFragment;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.squareup.picasso.Picasso;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class YoutubeSampleActivity extends FragmentActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyC1rMU-mkhoyTvBIdTnYU0dss0tU9vtK48";
    private static final String VIDEO_KEY = "pK2zYHWDZKo";
    private static final String VIDEO_POSTER_THUMBNAIL = "http://4.bp.blogspot.com/-BT6IshdVsoA/UjfnTo_TkBI/AAAAAAAAMWk/JvDCYCoFRlQ/s1600/xmenDOFP.wobbly.1.jpg";
    private static final String SECOND_VIDEO_POSTER_THUMBNAIL = "http://media.comicbook.com/wp-content/uploads/2013/07/x-men-days-of-future-past-wolverine-poster.jpg";

    @InjectView(R.id.iv_thumbnail)
    ImageView iv_thumbnail;
    @InjectView(R.id.draggable_panel)
    DraggablePanel draggablePanel;

    private YouTubePlayer youtubePlayer;

    private YouTubePlayerSupportFragment youtubeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_sample);
        ButterKnife.inject(this);
        initializeYoutubeFragment();
        initializeDraggablePanel();
        hookDraggablePanelListeners();
    }

    private void initializeYoutubeFragment() {
        youtubeFragment = new YouTubePlayerSupportFragment();
        youtubeFragment.initialize(YOUTUBE_API_KEY,
                new YouTubePlayer.OnInitializedListener() {

                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer player, boolean wasRestored) {
                        if (!wasRestored) {
                            youtubePlayer = player;
                            youtubePlayer.loadVideo(VIDEO_KEY);
                            youtubePlayer.setShowFullscreenButton(true);
                        }
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult error) {
                    }

                }
        );
    }

    private void initializeDraggablePanel() {
        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(youtubeFragment);
        MoviePosterFragment moviePosterFragment = new MoviePosterFragment();
        moviePosterFragment.setPoster(VIDEO_POSTER_THUMBNAIL);
        draggablePanel.setBottomFragment(moviePosterFragment);
        draggablePanel.initializeView();
        Picasso.with(this).load(SECOND_VIDEO_POSTER_THUMBNAIL).placeholder(R.drawable.xmen_placeholder).into(iv_thumbnail);
    }

    private void hookDraggablePanelListeners() {
        draggablePanel.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                if (!youtubePlayer.isPlaying()) {
                    youtubePlayer.play();
                }
            }

            @Override
            public void onMinimized() {

            }

            @Override
            public void onClosedToLeft() {
                if (youtubePlayer.isPlaying()) {
                    youtubePlayer.pause();
                }
            }

            @Override
            public void onClosedToRight() {
                if (youtubePlayer.isPlaying()) {
                    youtubePlayer.pause();
                }
            }
        });
    }

    @OnClick(R.id.iv_thumbnail)
    void onContainerClicked() {
        draggablePanel.maximize();
    }

}
