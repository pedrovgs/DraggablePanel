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
        "http://2.bp.blogspot.com/-0ULv5dX_6Hs/TmeR3qEGVOI/AAAAAAAAChA/P7Yy6_WE9d0/s1600/sailing"
            + "+stone+4.img_assist_custom-600x450.jpg", 36.5047681, -117.0744727));
    this.add(new PlaceViewModel("The Wave, Arizona",
        "http://www.greenster.com/magazine/files/2012/04/The-Wave-Arizona.jpg", 36.995629,
        -112.00613));
    this.add(new PlaceViewModel("Antelope Canyon, Arizona",
        "http://www.hotelclub.com/blog/wp-content/uploads/2008/12/antelope_canyon2.jpg", 36.861897,
        -111.374438));
    this.add(new PlaceViewModel("Haleakala",
        "http://www.aloha-hawaii.com/wp-content/uploads/2010/03/haleakala-sunset.jpg", 20.7097222,
        -156.2533333));
    this.add(new PlaceViewModel("Multnomah Falls",
        "http://columbiariverimages.com/Images/multnomah_falls_oregon_2005.jpg", 45.5761597,
        -122.1157756));
    this.add(new PlaceViewModel("Bungle Bungles",
        "http://www.kimberleyaustralia.com/image-files/bungle-bungles-picture-piccaninny.jpg",
        -17.489633, 128.375491));
    this.add(new PlaceViewModel("Wave Rock",
        "http://www.wheatbelttourism.com/wp-content/uploads/2011/09/Wave-Rock-31.jpg", -32.441815,
        118.896991));
    this.add(new PlaceViewModel("Windjana Gorge National Park",
        "http://www.kimberleywa.com/yahoo_site_admin/assets/images/winjana_gorge_photo.18275348_"
            + "std.jpg", -32.441815, 118.896991));
    this.add(new PlaceViewModel("Ayers Rock",
        "http://www.travel4kids.com.au/wp-content/uploads/2013/02/ayers-rock.jpg", -25.3487497,
        131.0303833));
    this.add(new PlaceViewModel("Great Barrier Reef",
        "http://i.telegraph.co.uk/multimedia/archive/02434/greatBarrierReef_2434464b.jpg",
        -18.242835, 147.451012));
    this.add(new PlaceViewModel("Iguazu Falls",
        "http://world.new7wonders.com/content/uploads/2011/10/Iguazu_2011.jpg", -25.695259,
        -54.436666));
    this.add(new PlaceViewModel("Amazon Rain, Brazil",
        "http://3.bp.blogspot.com/_ks7s40OFQT8/TBsikSDA5XI/AAAAAAAABhQ/Wf_Td8k639E/s1600/12.jpg",
        -14.2400732, -53.1805018));
    this.add(new PlaceViewModel("Canals of Venice",
        "http://www.goparoo.com/europe/italy/veneto/venice/attractions/canals/images/canals-of-"
            + "venice-home.jpg", 45.436357, 12.332249));
    this.add(new PlaceViewModel("Amalfi",
        "http://i.telegraph.co.uk/multimedia/archive/02205/amalfi_2205267b.jpg", 40.6309889,
        14.5916613));
    this.add(new PlaceViewModel("Colosseum of Rome",
        "http://www.destination360.com/europe/italy/images/s/italy-rome-colosseum.jpg", 41.89021,
        12.492231));
    this.add(new PlaceViewModel("The Blue Grotto",
        "http://corural.com/wp-content/uploads/2014/11/blue-grotto-capri.jpg", 40.91389,
        14.208688));
    this.add(new PlaceViewModel("Karnak Temple",
        "http://i.livescience.com/images/i/000/033/999/i02/shutterstock_580417.jpg?1354320604",
        25.7178978, 32.658147));
    this.add(new PlaceViewModel("Nile River",
        "http://www.greenprophet.com/wp-content/uploads/nile-river-egypt-10085-Aswan.jpg",
        30.8358821, 31.0784856));
    this.add(new PlaceViewModel("Valley of the Kings",
        "http://www.destination360.com/africa/egypt/images/s/valley-of-the-kings.jpg", 25.740165,
        32.601411));
    this.add(new PlaceViewModel("Murcia",
        "http://www.inkacars.com/wp-content/uploads/2013/01/Murcia-Cathedral-AlquilerCoches"
            + "Aeropuertos.jpg", 37.9886177, -1.13004));
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