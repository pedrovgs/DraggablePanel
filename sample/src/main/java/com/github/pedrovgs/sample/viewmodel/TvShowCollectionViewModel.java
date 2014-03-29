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

        tvShow = new TvShowViewModel("Marvel's Agents of S.H.I.E.L.D.", "http://thetvdb.com/banners/_cache/posters/263365-3.jpg", 1);
        tvShow.addEpisode(new EpisodeViewModel("Pilot", "2013-09-24"));
        tvShow.addEpisode(new EpisodeViewModel("0-8-4", "2013-10-01"));
        tvShow.addEpisode(new EpisodeViewModel("The Asset", "2013-10-08"));
        tvShow.addEpisode(new EpisodeViewModel("Eye Spy", "2013-10-15"));
        tvShow.addEpisode(new EpisodeViewModel("Girl in the Flower Dress", "2013-10-22"));
        tvShow.addEpisode(new EpisodeViewModel("F.Z.Z.T.", "2013-11-05"));
        tvShow.addEpisode(new EpisodeViewModel("The Hub", "2013-11-12"));
        tvShow.addEpisode(new EpisodeViewModel("The Well", "2013-11-19"));
        tvShow.addEpisode(new EpisodeViewModel("Repairs", "2013-11-26"));
        tvShow.addEpisode(new EpisodeViewModel("The Bridge", "2013-12-10"));
        tvShow.addEpisode(new EpisodeViewModel("The Magical Place", "2014-01-07"));
        tvShow.addEpisode(new EpisodeViewModel("Seeds", "2014-01-14"));
        tvShow.addEpisode(new EpisodeViewModel("T.R.A.C.K.S.", "2014-02-04"));
        tvShow.addEpisode(new EpisodeViewModel("T.A.H.I.T.I.", "2014-03-04"));
        tvShow.addEpisode(new EpisodeViewModel("Yes Men", "2014-03-11"));
        tvShow.addEpisode(new EpisodeViewModel("End of the Beginning", "2014-04-01"));
        tvShow.addEpisode(new EpisodeViewModel("Turn, Turn, Turn", "2014-04-08"));
        tvShow.addEpisode(new EpisodeViewModel("Providence", "2014-04-15"));
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
