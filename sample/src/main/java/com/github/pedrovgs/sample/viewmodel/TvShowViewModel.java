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
package com.github.pedrovgs.sample.viewmodel;

/**
 * TvShowViewModel implementation created to contain all the tv show information and to keep all
 * the representation state of a tv show.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowViewModel {

  private final String title;
  private final String poster;
  private final String fanArt;
  private final int numberOfSeasons;
  private final EpisodeCollection episodes;

  public TvShowViewModel(String title, String poster, String fanArt, int numberOfSeasons) {
    this.title = title;
    this.poster = poster;
    this.fanArt = fanArt;
    this.numberOfSeasons = numberOfSeasons;
    this.episodes = new EpisodeCollection();
  }

  /**
   * Add an episode to the tvShowViewModel.
   */
  public void addEpisode(EpisodeViewModel episodeViewModel) {
    episodes.add(episodeViewModel);
  }

  /**
   * @return the tv show title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return the tv show poster.
   */
  public String getPoster() {
    return poster;
  }

  /**
   * @return the tv show fan art.
   */
  public String getFanArt() {
    return fanArt;
  }

  /**
   * @return the tv show number of seasons.
   */
  public int getNumberOfSeasons() {
    return numberOfSeasons;
  }

  /**
   * @return the tv show EpisodeCollection.
   */
  public EpisodeCollection getEpisodes() {
    return episodes;
  }
}
