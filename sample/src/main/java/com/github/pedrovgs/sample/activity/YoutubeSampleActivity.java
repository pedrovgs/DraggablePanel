package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.DraggablePanel;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.fragment.PlaceFragment;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class YoutubeSampleActivity extends FragmentActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyC1rMU-mkhoyTvBIdTnYU0dss0tU9vtK48";
    private static final String VIDEO_KEY = "PpTGsZTMXlY";

    @InjectView(R.id.lv_videos)
    ListView lv_videos;
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
        draggablePanel.setBottomFragment(new PlaceFragment());
        draggablePanel.initializeView();
    }
}
