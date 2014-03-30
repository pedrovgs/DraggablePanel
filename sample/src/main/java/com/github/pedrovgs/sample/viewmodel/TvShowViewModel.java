package com.github.pedrovgs.sample.viewmodel;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowViewModel {

    private final String title;
    private final String poster;
    private final String fanArt;
    private final int numberOfSeasons;
    private final EpisodeCollection episodes;

    public TvShowViewModel(String title, String poster, String fanArt, int numberOfSeasons) {
        this.title = title;
        this.poster = poster;
        this.fanArt = fanArt;
        this.numberOfSeasons = numberOfSeasons;
        this.episodes = new EpisodeCollection();
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

    public EpisodeCollection getEpisodes() {
        return episodes;
    }
}
