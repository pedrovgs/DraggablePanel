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

        tvShow = new TvShowViewModel("Lost","http://thetvdb.com/banners/_cache/posters/73739-7.jpg",6);
        tvShow.addEpisode(new EpisodeViewModel("Pilot (1)","2004-09-22"));
        tvShow.addEpisode(new EpisodeViewModel("Pilot (2)","2004-09-29"));
        tvShow.addEpisode(new EpisodeViewModel("Tabula Rasa","2004-10-06"));
        tvShow.addEpisode(new EpisodeViewModel("Walkabout","2004-10-13"));
        tvShow.addEpisode(new EpisodeViewModel("White Rabbit","2004-10-20"));
        tvShow.addEpisode(new EpisodeViewModel("House of the Rising Sun","2004-10-27"));
        tvShow.addEpisode(new EpisodeViewModel("The Moth","2004-11-03"));
        tvShow.addEpisode(new EpisodeViewModel("Confidence Man","2004-11-10"));
        tvShow.addEpisode(new EpisodeViewModel("Solitary","2004-11-17"));
        tvShow.addEpisode(new EpisodeViewModel("Raised by Another","2004-12-01"));
        tvShow.addEpisode(new EpisodeViewModel("All the Best Cowboys Have Daddy Issues","2004-12-08"));
        tvShow.addEpisode(new EpisodeViewModel("Whatever the Case May Be the Case May Be","2005-01-05"));
        tvShow.addEpisode(new EpisodeViewModel("Hearts and Minds","2005-01-12"));
        tvShow.addEpisode(new EpisodeViewModel("Special","2005-01-19"));
        tvShow.addEpisode(new EpisodeViewModel("Homecoming","2005-02-09"));
        tvShow.addEpisode(new EpisodeViewModel("Outlaws","2005-02-16"));
        tvShow.addEpisode(new EpisodeViewModel("...In Translation","2005-02-23"));
        tvShow.addEpisode(new EpisodeViewModel("Numbers","2005-03-02"));
        tvShow.addEpisode(new EpisodeViewModel("Deus Ex Machina","2005-03-30"));
        tvShow.addEpisode(new EpisodeViewModel("Do No Harm","2005-04-06"));
        tvShow.addEpisode(new EpisodeViewModel("The Greater Good (a.k.a. Sides)","2005-05-04"));
        tvShow.addEpisode(new EpisodeViewModel("Born to Run","2005-05-11"));
        tvShow.addEpisode(new EpisodeViewModel("Exodus (1)","2005-05-18"));
        tvShow.addEpisode(new EpisodeViewModel("Exodus (2)","2005-05-25"));
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
