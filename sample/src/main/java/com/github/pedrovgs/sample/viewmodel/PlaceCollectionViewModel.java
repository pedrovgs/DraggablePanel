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

import com.pedrogomez.renderers.AdapteeCollection;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * PlaceCollectionViewModel implementation used to contains all the places information. This
 * implementation is based on a LinkedList with hardcoded data.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class PlaceCollectionViewModel implements AdapteeCollection<PlaceViewModel> {

  private final List<PlaceViewModel> places;

  @Inject public PlaceCollectionViewModel() {
    this.places = new LinkedList<PlaceViewModel>();
    this.add(new PlaceViewModel("Sailing Stones, Death Valley",
        "http://static.environmentalgraffiti.com/sites/default/files/images/"
            + "477px-20061205135618-DVNP-RACETRACKjpg", 36.5047681, -117.0744727));
    this.add(new PlaceViewModel("The Wave, Arizona",
        "http://static.environmentalgraffiti.com/sites/default/files/images/"
            + "wave3jpg.img_assist_custom-600x401.jpg", 36.995629, -112.00613));
    this.add(new PlaceViewModel("Antelope Canyon, Arizona",
        "http://static.environmentalgraffiti.com/sites/default/files/images/"
            + "450px-USAAntelope-Canyonjpg", 36.861897, -111.374438));
    this.add(new PlaceViewModel("Haleakala",
        "http://www.ohanafun.net/UserFiles/Image/tours/haleakala_sunrise.jpg", 20.7097222,
        -156.2533333));
    this.add(new PlaceViewModel("Multnomah Falls",
        "http://multnomahfallslodge.com/images/home_main2.jpg", 45.5761597, -122.1157756));
    this.add(new PlaceViewModel("Bungle Bungles",
        "http://static.environmentalgraffiti.com/sites/default/files/images/"
            + "bungle2jpg.img_assist_custom-600x454.jpg", -17.489633, 128.375491));
    this.add(new PlaceViewModel("Wave Rock",
        "http://static.environmentalgraffiti.com/sites/default/files/images/" + "800px-WaveRockjpg",
        -32.441815, 118.896991));
    this.add(new PlaceViewModel("Windjana Gorge National Park",
        "http://travelblog.viator.com/wp-content/uploads/2009/02/wa-winjana-gorge.jpg", -32.441815,
        118.896991));
    this.add(new PlaceViewModel("Ayers Rock",
        "http://static1.absolutaustralia.com/wp-content/uploads/2009/08/ayers.jpg", -25.3487497,
        131.0303833));
    this.add(new PlaceViewModel("Great Barrier Reef",
        "http://www.charterworld.com/news/wp-content/uploads/2012/"
            + "08/Great-Barrier-Reef-one-of-the-most-fabulous-"
            + "yacht-charter-destinations-in-Australia.jpg", -18.242835, 147.451012));
    this.add(new PlaceViewModel("Iguazu Falls",
        "http://travel.lowerhotels.com/wp-content/uploads/2013/06/Iguazu-Falls-Beauty.jpg",
        -25.695259, -54.436666));
    this.add(new PlaceViewModel("Amazon Rain, Brazil",
        "http://1.bp.blogspot.com/-zjvjWNPQczI/TfRQTEYMk7I/"
            + "AAAAAAAAAK8/W9SrwEyYPJw/s1600/rainforest.jpg", -14.2400732, -53.1805018));
    this.add(new PlaceViewModel("Canals of Venice",
        "http://www.jds-jgp.net/016_Small_canal_-_Venice.jpg", 45.436357, 12.332249));
    this.add(new PlaceViewModel("Amalfi",
        "http://img.playasymar.com/wp-content/uploads/2010/12/amalfi.jpg", 40.6309889, 14.5916613));
    this.add(new PlaceViewModel("Colosseum of Rome",
        "http://www.fotos-bonitas.com/wp-content/uploads/2013/06/Bing_zh-CN.jpg", 41.89021,
        12.492231));
    this.add(new PlaceViewModel("The Blue Grotto",
        "http://static.environmentalgraffiti.com/sites/default/files/"
            + "images/800px-BlueGrottoCapriInsidejpg.img_assist_custom-600x450.jpg", 40.91389,
        14.208688));
    this.add(new PlaceViewModel("Karnak Temple",
        "http://www.filmapia.com/sites/default/files/filmapia/pub/place/karnak-temple.jpg",
        25.7178978, 32.658147));
    this.add(new PlaceViewModel("Nile River",
        "http://www.holidayplanners.com/nitro/files/nileriver%20egypt.jpg", 30.8358821,
        31.0784856));
    this.add(new PlaceViewModel("Valley of the Kings",
        "http://upload.wikimedia.org/wikipedia/commons/0/0c/Luxor"
            + ",_Tal_der_K%C3%B6nige_(1995,_860x605).jpg", 25.740165, 32.601411));
    this.add(new PlaceViewModel("Murcia", "http://www.auriautos.es/ciudades/murcia.jpg", 37.9886177,
        -1.13004));
  }

  /**
   * Return the number of places inside the collection.
   */
  @Override public int size() {
    return places.size();
  }

  /**
   * Return a PlaceViewModel obtained by position.
   */
  @Override public PlaceViewModel get(int i) {
    return places.get(i);
  }

  /**
   * Add a PlaceViewModel to the collection.
   */
  @Override public void add(PlaceViewModel placeViewModel) {
    places.add(placeViewModel);
  }

  /**
   * Remove a PlaceViewModel from the collection.
   */
  @Override public void remove(PlaceViewModel placeViewModel) {
    places.remove(placeViewModel);
  }

  /**
   * Add a collection of PlaceViewModel to the collection.
   */
  @Override public void addAll(Collection collection) {
    places.addAll(collection);
  }

  /**
   * Remove a collection of PlaceViewModel to the collection.
   */
  @Override public void removeAll(Collection collection) {
    places.addAll(collection);
  }
}
