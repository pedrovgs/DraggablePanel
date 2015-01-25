package com.github.pedrovgs.sample.viewmodel;

/**
 * Created by pedro on 1/24/15.
 */
public class VideoViewModel {

    private final String title;
    private final int image;

    public VideoViewModel(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

}
