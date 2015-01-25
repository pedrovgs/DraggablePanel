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
 * PlaceViewModel implementation created to contain all the place information and to keep all the
 * representation state of a place.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class PlaceViewModel {

  private final String name;
  private final String photo;
  private final double latitude;
  private final double longitude;

  public PlaceViewModel(String name, String photo, double latitude, double longitude) {
    this.name = name;
    this.photo = photo;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * @return the place name.
   */
  public String getName() {
    return name;
  }

  /**
   * @return the place photo.
   */
  public String getPhoto() {
    return photo;
  }

  /**
   * @return the place latitude.
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * @return the place longitude.
   */
  public double getLongitude() {
    return longitude;
  }
}
