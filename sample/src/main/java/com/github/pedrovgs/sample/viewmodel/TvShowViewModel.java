package com.github.pedrovgs.sample.viewmodel;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowViewModel {

    private final String title;
    private final String poster;
    private final String fanArt;
    private final int numberOfSeasons;
    private final List<EpisodeViewModel> episodes;

    public TvShowViewModel(String title, String poster, String fanArt, int numberOfSeasons) {
        this.title = title;
        this.poster = poster;
        this.fanArt = fanArt;
        this.numberOfSeasons = numberOfSeasons;
        this.episodes = new LinkedList<EpisodeViewModel>();
    }

    public void addEpisode(EpisodeViewModel episodeViewModel) {
        episodes.add(episodeViewModel);
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getFanArt() {
        return fanArt;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }
}
