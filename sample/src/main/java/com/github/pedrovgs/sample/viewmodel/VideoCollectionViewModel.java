package com.github.pedrovgs.sample.viewmodel;

import com.github.pedrovgs.sample.R;
import com.pedrogomez.renderers.AdapteeCollection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by pedro on 1/25/15.
 */
public class VideoCollectionViewModel implements AdapteeCollection<VideoViewModel> {

    private final List<VideoViewModel> videoList;

    @Inject
    public VideoCollectionViewModel() {
        this.videoList = new LinkedList<VideoViewModel>();
        VideoViewModel places = new VideoViewModel(R.string.main_activity_places_button_title, R.drawable.places_button_image);
        videoList.add(places);
        VideoViewModel tvshow = new VideoViewModel(R.string.main_activity_tv_shows_button_title, R.drawable.tv_show_button_image);
        videoList.add(tvshow);
        VideoViewModel youtube = new VideoViewModel(R.string.youtube_sample_activity_title, R.drawable.youtube_button_image);
        videoList.add(youtube);
        VideoViewModel video = new VideoViewModel(R.string.video_sample_activity_title, R.drawable.video_button_image);
        videoList.add(video);
        VideoViewModel textureViewRtsp = new VideoViewModel(R.string.main_activity_video_texture_view_rtsp, R.drawable.textureview_image);
        videoList.add(textureViewRtsp);
        VideoViewModel textureViewMp4 = new VideoViewModel(R.string.main_activity_video_texture_view_mp4, R.drawable.bigbuckbunny);
        videoList.add(textureViewMp4);
    }

    @Override
    public int size() {
        return videoList.size();
    }

    @Override
    public VideoViewModel get(int i) {
        return videoList.get(i);
    }

    @Override
    public void add(VideoViewModel element) {
        videoList.add(element);
    }

    @Override
    public void remove(VideoViewModel element) {
        videoList.remove(element);
    }

    @Override
    public void addAll(Collection<VideoViewModel> elements) {
        videoList.addAll(elements);
    }

    @Override
    public void removeAll(Collection<VideoViewModel> elements) {
        videoList.removeAll(elements);
    }
}
