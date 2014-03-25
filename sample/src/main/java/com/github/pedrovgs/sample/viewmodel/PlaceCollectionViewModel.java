package com.github.pedrovgs.sample.viewmodel;

import com.pedrogomez.renderers.AdapteeCollection;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class PlaceCollectionViewModel implements AdapteeCollection<PlaceViewModel> {

    private final List<PlaceViewModel> places;

    @Inject
    public PlaceCollectionViewModel() {
        this.places = new LinkedList<PlaceViewModel>();
        this.add(new PlaceViewModel("Sailing Stones, Death Valley", "http://static.environmentalgraffiti.com/sites/default/files/images/477px-20061205135618-DVNP-RACETRACKjpg", "Description"));
        this.add(new PlaceViewModel("The Wave, Arizona", "http://static.environmentalgraffiti.com/sites/default/files/images/wave3jpg.img_assist_custom-600x401.jpg", "Description"));
        this.add(new PlaceViewModel("Antelope Canyon, Arizona", "http://static.environmentalgraffiti.com/sites/default/files/images/450px-USAAntelope-Canyonjpg", "Description"));
        this.add(new PlaceViewModel("Haleakala", "http://www.ohanafun.net/UserFiles/Image/tours/haleakala_sunrise.jpg", "Description"));
        this.add(new PlaceViewModel("Multnomah Falls", "http://multnomahfallslodge.com/images/home_main2.jpg", "Description"));
        this.add(new PlaceViewModel("Bungle Bungles", "http://static.environmentalgraffiti.com/sites/default/files/images/bungle2jpg.img_assist_custom-600x454.jpg", "Description"));
        this.add(new PlaceViewModel("Wave Rock", "http://static.environmentalgraffiti.com/sites/default/files/images/800px-WaveRockjpg", "Description"));
        this.add(new PlaceViewModel("Windjana Gorge National Park", "http://travelblog.viator.com/wp-content/uploads/2009/02/wa-winjana-gorge.jpg", "Description"));
        this.add(new PlaceViewModel("Ayers Rock", "http://static1.absolutaustralia.com/wp-content/uploads/2009/08/ayers.jpg", "Description"));
        this.add(new PlaceViewModel("Great Barrier Reef", "http://www.charterworld.com/news/wp-content/uploads/2012/08/Great-Barrier-Reef-one-of-the-most-fabulous-yacht-charter-destinations-in-Australia.jpg", "Description"));
        this.add(new PlaceViewModel("Iguazu Falls", "http://travel.lowerhotels.com/wp-content/uploads/2013/06/Iguazu-Falls-Beauty.jpg", "Description"));
        this.add(new PlaceViewModel("Amazon Rain, Brazil", "http://1.bp.blogspot.com/-zjvjWNPQczI/TfRQTEYMk7I/AAAAAAAAAK8/W9SrwEyYPJw/s1600/rainforest.jpg", "Description"));
        this.add(new PlaceViewModel("Canals of Venice", "http://www.jds-jgp.net/016_Small_canal_-_Venice.jpg", "Description"));
        this.add(new PlaceViewModel("Amalfi", "http://img.playasymar.com/wp-content/uploads/2010/12/amalfi.jpg", "Description"));
        this.add(new PlaceViewModel("Colosseum of Rome", "http://www.fotos-bonitas.com/wp-content/uploads/2013/06/Bing_zh-CN.jpg", "Description"));
        this.add(new PlaceViewModel("The Blue Grotto", "http://static.environmentalgraffiti.com/sites/default/files/images/800px-BlueGrottoCapriInsidejpg.img_assist_custom-600x450.jpg", "Description"));
        this.add(new PlaceViewModel("Karnak Temple", "http://www.filmapia.com/sites/default/files/filmapia/pub/place/karnak-temple.jpg", "Description"));
        this.add(new PlaceViewModel("Nile River", "http://www.holidayplanners.com/nitro/files/nileriver%20egypt.jpg", "Description"));
        this.add(new PlaceViewModel("Valley of the Kings", "http://upload.wikimedia.org/wikipedia/commons/0/0c/Luxor,_Tal_der_K%C3%B6nige_(1995,_860x605).jpg", "Description"));
    }

    @Override
    public int size() {
        return places.size();
    }

    @Override
    public PlaceViewModel get(int i) {
        return places.get(i);
    }

    @Override
    public void add(PlaceViewModel placeViewModel) {
        places.add(placeViewModel);
    }

    @Override
    public void remove(PlaceViewModel placeViewModel) {
        places.remove(placeViewModel);
    }


    @Override
    public void addAll(Collection collection) {
        places.addAll(collection);
    }

    @Override
    public void removeAll(Collection collection) {
        places.addAll(collection);
    }
}
