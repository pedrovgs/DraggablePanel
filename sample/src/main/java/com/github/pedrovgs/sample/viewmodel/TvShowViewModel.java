package com.github.pedrovgs.sample.viewmodel;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowViewModel {

    private final String title;
    private final String thumbnail;
    private final int numberOfSeasons;
    private final List<EpisodeViewModel> episodes;

    public TvShowViewModel(String title, String thumbnail, int numberOfSeasons) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.numberOfSeasons = numberOfSeasons;
        this.episodes = new LinkedList<EpisodeViewModel>();
    }

    public void addEpisode(EpisodeViewModel episodeViewModel) {
        episodes.add(episodeViewModel);
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }
}
