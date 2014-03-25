package com.github.pedrovgs.sample.viewmodel;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class CityViewModel {

    private final String name;
    private final String photo;
    private final String description;

    public CityViewModel(String name, String photo, String description) {
        this.name = name;
        this.photo = photo;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }
}
