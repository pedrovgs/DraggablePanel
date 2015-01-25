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
        VideoViewModel videoViewModel = new VideoViewModel("teste", R.drawable.ic_launcher);
        videoList.add(videoViewModel);
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
