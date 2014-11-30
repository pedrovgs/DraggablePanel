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
 * EpisodeViewModel implementation created to contain all the episode information and to keep all
 * the representation state of an episode.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EpisodeViewModel {

  private final String title;
  private final String publishDate;

  public EpisodeViewModel(String title, String publishDate) {
    this.title = title;
    this.publishDate = publishDate;
  }

  /**
   * @return title associated to the EpisodeViewModel.
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return publish date associated to the EpisodeViewModel
   */
  public String getPublishDate() {
    return publishDate;
  }
}
