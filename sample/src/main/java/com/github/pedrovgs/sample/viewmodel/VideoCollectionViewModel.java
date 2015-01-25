/*
 * Copyright (C) 2015 Pedro Paulo de Amorim.
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
package com.github.pedrovgs.sample.viewmodel;

import com.github.pedrovgs.sample.R;
import com.pedrogomez.renderers.AdapteeCollection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * VideoCollectionViewModel implementation used to contains all the video items information. This
 * implementation is based on a LinkedList with hardcoded data.
 *
 * @author Pedro Paulo de Amorim.
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

  /**
   * Return the number of videos inside the collection.
   */
  @Override
  public int size() {
    return videoList.size();
  }

  /**
   * Return a VideoViewModel obtained by position.
   */
  @Override
  public VideoViewModel get(int i) {
    return videoList.get(i);
  }

  /**
   * Add a VideoViewModel to the collection.
   */
  @Override
  public void add(VideoViewModel element) {
    videoList.add(element);
  }

  /**
   * Remove a VideoViewModel from the collection.
   */
  @Override
  public void remove(VideoViewModel element) {
    videoList.remove(element);
  }

  /**
   * Add a collection of VideoViewModel to the collection.
   */
  @Override
  public void addAll(Collection<VideoViewModel> elements) {
    videoList.addAll(elements);
  }

  /**
   * Remove a collection of VideoViewModel to the collection.
   */
  @Override
  public void removeAll(Collection<VideoViewModel> elements) {
    videoList.removeAll(elements);
  }
}
