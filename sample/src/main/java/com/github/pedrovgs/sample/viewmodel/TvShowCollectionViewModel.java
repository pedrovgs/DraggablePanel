package com.github.pedrovgs.sample.viewmodel;

import com.pedrogomez.renderers.AdapteeCollection;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowCollectionViewModel implements AdapteeCollection<TvShowViewModel> {

    private final List<TvShowViewModel> tvShows;

    @Inject
    public TvShowCollectionViewModel() {
        this.tvShows = new LinkedList<TvShowViewModel>();
        TvShowViewModel tvShow = new TvShowViewModel("Breaking Bad", "http://thetvdb.com/banners/_cache/posters/81189-22.jpg", 5);
        tvShow.addEpisode(new EpisodeViewModel("Pilot", "2008-01-20"));
        tvShow.addEpisode(new EpisodeViewModel("Cat's in the Bag...", "2008-01-27"));
        tvShow.addEpisode(new EpisodeViewModel("...And the Bag's in the River", "2008-02-10"));
        tvShow.addEpisode(new EpisodeViewModel("Cancer Man", "2008-02-17"));
        tvShow.addEpisode(new EpisodeViewModel("Gray Matter", "2008-02-24"));
        tvShow.addEpisode(new EpisodeViewModel("Crazy Handful of Nothin'", "2008-03-02"));
        tvShow.addEpisode(new EpisodeViewModel("A No-Rough-Stuff-Type Deal'", "2008-03-09"));
        tvShows.add(tvShow);

    }

    @Override
    public int size() {
        return tvShows.size();
    }

    @Override
    public TvShowViewModel get(int i) {
        return tvShows.get(i);
    }

    @Override
    public void add(TvShowViewModel tvShowViewModel) {
        tvShows.add(tvShowViewModel);
    }

    @Override
    public void remove(TvShowViewModel tvShowViewModel) {
        tvShows.remove(tvShowViewModel);
    }

    @Override
    public void addAll(Collection<TvShowViewModel> tvShowViewModels) {
        tvShows.addAll(tvShowViewModels);
    }

    @Override
    public void removeAll(Collection<TvShowViewModel> tvShowViewModels) {
        tvShowViewModels.removeAll(tvShowViewModels);
    }
}
