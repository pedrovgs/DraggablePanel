package com.github.pedrovgs.sample.viewmodel;

/**
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

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
