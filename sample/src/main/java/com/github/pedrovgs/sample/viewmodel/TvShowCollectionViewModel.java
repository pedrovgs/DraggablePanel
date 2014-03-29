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

        tvShow = new TvShowViewModel("Lost", "http://thetvdb.com/banners/_cache/posters/73739-7.jpg", 6);
        tvShow.addEpisode(new EpisodeViewModel("Pilot (1)", "2004-09-22"));
        tvShow.addEpisode(new EpisodeViewModel("Pilot (2)", "2004-09-29"));
        tvShow.addEpisode(new EpisodeViewModel("Tabula Rasa", "2004-10-06"));
        tvShow.addEpisode(new EpisodeViewModel("Walkabout", "2004-10-13"));
        tvShow.addEpisode(new EpisodeViewModel("White Rabbit", "2004-10-20"));
        tvShow.addEpisode(new EpisodeViewModel("House of the Rising Sun", "2004-10-27"));
        tvShow.addEpisode(new EpisodeViewModel("The Moth", "2004-11-03"));
        tvShow.addEpisode(new EpisodeViewModel("Confidence Man", "2004-11-10"));
        tvShow.addEpisode(new EpisodeViewModel("Solitary", "2004-11-17"));
        tvShow.addEpisode(new EpisodeViewModel("Raised by Another", "2004-12-01"));
        tvShow.addEpisode(new EpisodeViewModel("All the Best Cowboys Have Daddy Issues", "2004-12-08"));
        tvShow.addEpisode(new EpisodeViewModel("Whatever the Case May Be the Case May Be", "2005-01-05"));
        tvShow.addEpisode(new EpisodeViewModel("Hearts and Minds", "2005-01-12"));
        tvShow.addEpisode(new EpisodeViewModel("Special", "2005-01-19"));
        tvShow.addEpisode(new EpisodeViewModel("Homecoming", "2005-02-09"));
        tvShow.addEpisode(new EpisodeViewModel("Outlaws", "2005-02-16"));
        tvShow.addEpisode(new EpisodeViewModel("...In Translation", "2005-02-23"));
        tvShow.addEpisode(new EpisodeViewModel("Numbers", "2005-03-02"));
        tvShow.addEpisode(new EpisodeViewModel("Deus Ex Machina", "2005-03-30"));
        tvShow.addEpisode(new EpisodeViewModel("Do No Harm", "2005-04-06"));
        tvShow.addEpisode(new EpisodeViewModel("The Greater Good (a.k.a. Sides)", "2005-05-04"));
        tvShow.addEpisode(new EpisodeViewModel("Born to Run", "2005-05-11"));
        tvShow.addEpisode(new EpisodeViewModel("Exodus (1)", "2005-05-18"));
        tvShow.addEpisode(new EpisodeViewModel("Exodus (2)", "2005-05-25"));
        tvShows.add(tvShow);

        tvShow = new TvShowViewModel("Arrow", "http://thetvdb.com/banners/_cache/posters/257655-5.jpg", 2);
        tvShow.addEpisode(new EpisodeViewModel("Pilot", "2012-10-10"));
        tvShow.addEpisode(new EpisodeViewModel("Honor Thy Father", "2012-10-17"));
        tvShow.addEpisode(new EpisodeViewModel("Lone Gunmen", "2012-10-24"));
        tvShow.addEpisode(new EpisodeViewModel("An Innocent Man", "2012-10-31"));
        tvShow.addEpisode(new EpisodeViewModel("Damaged", "2012-11-07"));
        tvShow.addEpisode(new EpisodeViewModel("Legacies", "b2012-11-14"));
        tvShow.addEpisode(new EpisodeViewModel("Muse of Fire", "2012-11-28"));
        tvShow.addEpisode(new EpisodeViewModel("Vendetta", "2012-12-05"));
        tvShow.addEpisode(new EpisodeViewModel("Year's End", "2012-12-12"));
        tvShow.addEpisode(new EpisodeViewModel("Burned", "2013-01-16"));
        tvShow.addEpisode(new EpisodeViewModel("Trust But Verify", "2013-01-23"));
        tvShow.addEpisode(new EpisodeViewModel("Vertigo", "2013-02-06"));
        tvShow.addEpisode(new EpisodeViewModel("Betrayal", "2013-02-06"));
        tvShow.addEpisode(new EpisodeViewModel("The Odyssey", "2013-02-13"));
        tvShow.addEpisode(new EpisodeViewModel("Dodger", "2013-02-20"));
        tvShow.addEpisode(new EpisodeViewModel("Dead to Rights", "2013-02-27"));
        tvShow.addEpisode(new EpisodeViewModel("The Huntress Returns", "2013-03-20"));
        tvShow.addEpisode(new EpisodeViewModel("Salvation", "2013-03-27"));
        tvShow.addEpisode(new EpisodeViewModel("Unfinished Business", "2013-04-03"));
        tvShow.addEpisode(new EpisodeViewModel("Home Invasion", "2013-04-24"));
        tvShow.addEpisode(new EpisodeViewModel("The Undertaking", "2013-05-01"));
        tvShow.addEpisode(new EpisodeViewModel("Darkness on the Edge of Town", "2013-05-08"));
        tvShow.addEpisode(new EpisodeViewModel("Sacrifice", "2013-05-15"));
        tvShows.add(tvShow);

        tvShow = new TvShowViewModel("The Newsroom", "http://thetvdb.com/banners/_cache/posters/76399-1.jpg", 3);
        tvShow.addEpisode(new EpisodeViewModel("The Walking Shoe Incident", "1996-10-21"));
        tvShow.addEpisode(new EpisodeViewModel("Dinner at Eight", "1996-10-28"));
        tvShow.addEpisode(new EpisodeViewModel("Deeper, Deeper", "1996-11-04-11-04"));
        tvShow.addEpisode(new EpisodeViewModel("The Kevorkian Joke", "1996-11-11"));
        tvShow.addEpisode(new EpisodeViewModel("A Bad Day", "1996-11-18"));
        tvShow.addEpisode(new EpisodeViewModel("Petty Tyranny", "1996-11-25"));
        tvShow.addEpisode(new EpisodeViewModel("Dis and Dat", "1997-02-10"));
        tvShow.addEpisode(new EpisodeViewModel("Unity", "1997-02-17"));
        tvShow.addEpisode(new EpisodeViewModel("Parking", "1997-02-24"));
        tvShow.addEpisode(new EpisodeViewModel("Meltdown (1)", "1997-03-03"));
        tvShow.addEpisode(new EpisodeViewModel("Meltdown (2)", "1997-03-17"));
        tvShow.addEpisode(new EpisodeViewModel("Meltdown (3)", "1997-03-24"));
        tvShow.addEpisode(new EpisodeViewModel("The Campaign", "1997-03-31"));
        tvShows.add(tvShow);

        tvShow = new TvShowViewModel("How I Met Your Mother", "http://thetvdb.com/banners/_cache/posters/75760-29.jpg", 9);
        tvShow.addEpisode(new EpisodeViewModel("Pilot", "2005-09-19"));
        tvShow.addEpisode(new EpisodeViewModel("Purple Giraffe", "2005-09-26"));
        tvShow.addEpisode(new EpisodeViewModel("Sweet Taste of Liberty", "2005-10-03"));
        tvShow.addEpisode(new EpisodeViewModel("Return of the Shirt", "2005-10-10"));
        tvShow.addEpisode(new EpisodeViewModel("Okay Awesome", "2005-10-17"));
        tvShow.addEpisode(new EpisodeViewModel("Slutty Pumpkin", "2005-10-24"));
        tvShow.addEpisode(new EpisodeViewModel("Matchmaker", "2005-11-07"));
        tvShow.addEpisode(new EpisodeViewModel("The Duel", "2005-11-14"));
        tvShow.addEpisode(new EpisodeViewModel("Belly Full of Turkey", "2005-11-21"));
        tvShow.addEpisode(new EpisodeViewModel("The Pineapple Incident", "2005-11-28"));
        tvShow.addEpisode(new EpisodeViewModel("The Limo", "2005-12-19"));
        tvShow.addEpisode(new EpisodeViewModel("The Wedding", "2006-01-09"));
        tvShow.addEpisode(new EpisodeViewModel("Drumroll, Please", "2006-01-23"));
        tvShow.addEpisode(new EpisodeViewModel("Zip, Zip, Zip", "2006-02-27"));
        tvShow.addEpisode(new EpisodeViewModel("Game Night", "2006-02-06"));
        tvShow.addEpisode(new EpisodeViewModel("Cupcake", "2006-03-06"));
        tvShow.addEpisode(new EpisodeViewModel("Life Among the Gorillas", "2006-03-20"));
        tvShow.addEpisode(new EpisodeViewModel("Nothing Good Happens After 2 A.M.", "2006-04-10"));
        tvShow.addEpisode(new EpisodeViewModel("Mary the Paralegal", "2006-04-24"));
        tvShow.addEpisode(new EpisodeViewModel("Best Prom Ever", "2006-05-01"));
        tvShow.addEpisode(new EpisodeViewModel("Milk", "2006-05-08"));
        tvShow.addEpisode(new EpisodeViewModel("Come On", "2006-05-15"));
        tvShows.add(tvShow);

        tvShow = new TvShowViewModel("Game of Thrones", "http://thetvdb.com/banners/_cache/posters/121361-4.jpg", 4);
        tvShow.addEpisode(new EpisodeViewModel("Winter Is Coming", "2011-04-17"));
        tvShow.addEpisode(new EpisodeViewModel("The Kingsroad", "2011-04-24"));
        tvShow.addEpisode(new EpisodeViewModel("Lord Snow", "2011-05-01"));
        tvShow.addEpisode(new EpisodeViewModel("Cripples, Bastards, and Broken Things", "2011-05-08"));
        tvShow.addEpisode(new EpisodeViewModel("The Wolf and the Lion", "2011-05-15"));
        tvShow.addEpisode(new EpisodeViewModel("A Golden Crown", "2011-05-22"));
        tvShow.addEpisode(new EpisodeViewModel("You Win or You Die", "2011-05-29"));
        tvShow.addEpisode(new EpisodeViewModel("The Pointy End", "2011-06-05"));
        tvShow.addEpisode(new EpisodeViewModel("Baelor", "2011-06-12"));
        tvShow.addEpisode(new EpisodeViewModel("Fire and Blood", "2011-06-19"));
        tvShows.add(tvShow);


        tvShow = new TvShowViewModel("Dexter", "http://thetvdb.com/banners/_cache/posters/79349-24.jpg", 8);
        tvShow.addEpisode(new EpisodeViewModel("Dexter", "2006-10-01"));
        tvShow.addEpisode(new EpisodeViewModel("Crocodile", "2006-10-08"));
        tvShow.addEpisode(new EpisodeViewModel("Popping Cherry", "2006-10-15"));
        tvShow.addEpisode(new EpisodeViewModel("Let's Give the Boy a Hand", "2006-10-22"));
        tvShow.addEpisode(new EpisodeViewModel("Love American Style", "2006-10-29"));
        tvShow.addEpisode(new EpisodeViewModel("Return to Sender", "2006-11-05"));
        tvShow.addEpisode(new EpisodeViewModel("Circle of Friends", "2006-11-12"));
        tvShow.addEpisode(new EpisodeViewModel("Shrink Wrap", "2006-11-19"));
        tvShow.addEpisode(new EpisodeViewModel("Father Knows Best", "2006-11-26"));
        tvShow.addEpisode(new EpisodeViewModel("Seeing Red", "2006-12-03"));
        tvShow.addEpisode(new EpisodeViewModel("Truth Be Told", "2006-12-10"));
        tvShow.addEpisode(new EpisodeViewModel("Born Free", "2006-12-17"));
        tvShows.add(tvShow);

        tvShow = new TvShowViewModel("House of Cards", "http://thetvdb.com/banners/_cache/posters/79861-1.jpg", 3);
        tvShow.addEpisode(new EpisodeViewModel("House of Cards Episode 1", "1990-11-18"));
        tvShow.addEpisode(new EpisodeViewModel("House of Cards Episode 2", "1990-11-25"));
        tvShow.addEpisode(new EpisodeViewModel("House of Cards Episode 3", "1990-12-02"));
        tvShow.addEpisode(new EpisodeViewModel("House of Cards Episode 4", "1990-12-09"));
        tvShows.add(tvShow);

        tvShow = new TvShowViewModel("The Big Bang Theory", "http://thetvdb.com/banners/_cache/posters/80379-18.jpg", 7);
        tvShow.addEpisode(new EpisodeViewModel("Pilot", "2007-09-24"));
        tvShow.addEpisode(new EpisodeViewModel("The Big Bran Hypothesis", "2007-10-01"));
        tvShow.addEpisode(new EpisodeViewModel("The Fuzzy Boots Corollary", "2007-10-08"));
        tvShow.addEpisode(new EpisodeViewModel("The Luminous Fish Effect", "2007-10-15"));
        tvShow.addEpisode(new EpisodeViewModel("The Hamburger Postulate", "2007-10-22"));
        tvShow.addEpisode(new EpisodeViewModel("The Middle Earth Paradigm", "2007-10-29"));
        tvShow.addEpisode(new EpisodeViewModel("The Dumpling Paradox", "2007-11-05"));
        tvShow.addEpisode(new EpisodeViewModel("The Grasshopper Experiment", "2007-11-12"));
        tvShow.addEpisode(new EpisodeViewModel("The Cooper-Hofstadter Polarization", "2008-03-17"));
        tvShow.addEpisode(new EpisodeViewModel("The Loobenfeld Decay", "2008-03-24"));
        tvShow.addEpisode(new EpisodeViewModel("The Pancake Batter Anomaly", "2008-03-31"));
        tvShow.addEpisode(new EpisodeViewModel("The Jerusalem Duality", "2008-04-14"));
        tvShow.addEpisode(new EpisodeViewModel("The Bat Jar Conjecture", "2008-04-21"));
        tvShow.addEpisode(new EpisodeViewModel("The Nerdvana Annihilation", "2008-04-28"));
        tvShow.addEpisode(new EpisodeViewModel("The Pork Chop Indeterminacy", "2008-05-05"));
        tvShow.addEpisode(new EpisodeViewModel("The Peanut Reaction", "2008-05-12"));
        tvShow.addEpisode(new EpisodeViewModel("The Tangerine Factor", "2008-05-19"));
        tvShows.add(tvShow);


        tvShow = new TvShowViewModel("Sleepy Hollow", "http://thetvdb.com/banners/_cache/posters/269578-4.jpg", 1);
        tvShow.addEpisode(new EpisodeViewModel("Pilot", "2013-09-16"));
        tvShow.addEpisode(new EpisodeViewModel("Blood Moon", "2013-09-23"));
        tvShow.addEpisode(new EpisodeViewModel("For the Triumph of Evil", "2013-09-30"));
        tvShow.addEpisode(new EpisodeViewModel("The Lesser Key of Solomon", "2013-10-07"));
        tvShow.addEpisode(new EpisodeViewModel("John Doe", "2013-10-14"));
        tvShow.addEpisode(new EpisodeViewModel("The Sin Eater", "2013-11-04"));
        tvShow.addEpisode(new EpisodeViewModel("The Midnight Ride", "2013-11-11"));
        tvShow.addEpisode(new EpisodeViewModel("Necromancer", "2013-11-18"));
        tvShow.addEpisode(new EpisodeViewModel("Sanctuary", "2013-11-25"));
        tvShow.addEpisode(new EpisodeViewModel("The Golem", "2013-12-09"));
        tvShow.addEpisode(new EpisodeViewModel("The Vessel", "2014-01-13"));
        tvShow.addEpisode(new EpisodeViewModel("Indispensable Man", "2014-01-20"));
        tvShow.addEpisode(new EpisodeViewModel("Bad Blood", "2014-01-20"));
        tvShows.add(tvShow);

        tvShow = new TvShowViewModel("The Vampire Diaries", "http://thetvdb.com/banners/_cache/posters/95491-28.jpg", 5);
        tvShow.addEpisode(new EpisodeViewModel("Pilot", "2009-09-10"));
        tvShow.addEpisode(new EpisodeViewModel("The Night of the Comet", "2009-09-17"));
        tvShow.addEpisode(new EpisodeViewModel("Friday Night Bites", "2009-09-24"));
        tvShow.addEpisode(new EpisodeViewModel("Family Ties", "2009-10-01"));
        tvShow.addEpisode(new EpisodeViewModel("You're Undead to Me", "2009-10-08"));
        tvShow.addEpisode(new EpisodeViewModel("Lost Girls", "2009-10-15"));
        tvShow.addEpisode(new EpisodeViewModel("Haunted", "2009-10-29"));
        tvShow.addEpisode(new EpisodeViewModel("162 Candles", "2009-11-05"));
        tvShow.addEpisode(new EpisodeViewModel("History Repeating", "2009-11-12"));
        tvShow.addEpisode(new EpisodeViewModel("The Turning Point", "2009-11-19"));
        tvShows.add(tvShow);

        tvShow = new TvShowViewModel("Friends", "http://thetvdb.com/banners/_cache/posters/79168-3.jpg", 10);
        tvShow.addEpisode(new EpisodeViewModel("The One Where Monica Gets A Roommate", "1994-09-22"));
        tvShow.addEpisode(new EpisodeViewModel("The One With The Sonogram At The End", "1994-09-29"));
        tvShow.addEpisode(new EpisodeViewModel("The One With The Thumb", "1994-10-06"));
        tvShow.addEpisode(new EpisodeViewModel("The One With George Stephanopoulos", "1994-10-13"));
        tvShow.addEpisode(new EpisodeViewModel("The One With The East German Laundry Detergent", "1994-10-20"));
        tvShow.addEpisode(new EpisodeViewModel("The One With The Butt", "1994-10-27"));
        tvShow.addEpisode(new EpisodeViewModel("The One With The Blackout", "1994-11-03"));
        tvShow.addEpisode(new EpisodeViewModel("The One Where Nana Dies Twice", "1994-11-10"));
        tvShow.addEpisode(new EpisodeViewModel("The One Where Underdog Gets Away", "1994-11-17"));
        tvShow.addEpisode(new EpisodeViewModel("The One With The Monkey", "1994-12-15"));
        tvShows.add(tvShow);

        tvShow = new TvShowViewModel("New Girl", "http://thetvdb.com/banners/_cache/posters/248682-9.jpg", 3);
        tvShow.addEpisode(new EpisodeViewModel("Pilot", "2011-09-20"));
        tvShow.addEpisode(new EpisodeViewModel("Kryptonite", "2011-09-27"));
        tvShow.addEpisode(new EpisodeViewModel("Wedding", "2011-10-04"));
        tvShow.addEpisode(new EpisodeViewModel("Naked", "2011-11-01"));
        tvShow.addEpisode(new EpisodeViewModel("Cece Crashes", "2011-11-08"));
        tvShow.addEpisode(new EpisodeViewModel("Thanksgiving", "2011-11-15"));
        tvShow.addEpisode(new EpisodeViewModel("Bells", "2011-11-29"));
        tvShow.addEpisode(new EpisodeViewModel("Bad in Bed", "2011-12-06"));
        tvShow.addEpisode(new EpisodeViewModel("The 23rd", "2011-12-13"));
        tvShow.addEpisode(new EpisodeViewModel("The Story of the 50", "2012-01-17"));
        tvShows.add(tvShow);

        tvShow = new TvShowViewModel("Family Guy", "http://thetvdb.com/banners/_cache/posters/75978-13.jpg", 12);
        tvShow.addEpisode(new EpisodeViewModel("Death has a Shadow", "1999-01-31"));
        tvShow.addEpisode(new EpisodeViewModel("I Never Met the Dead Man", "1999-04-11"));
        tvShow.addEpisode(new EpisodeViewModel("Chitty Chitty Death Bang", "1999-04-18"));
        tvShow.addEpisode(new EpisodeViewModel("Mind Over Murder", "1999-04-25"));
        tvShow.addEpisode(new EpisodeViewModel("A Hero Sits Next Door", "1999-05-02"));
        tvShow.addEpisode(new EpisodeViewModel("The Son Also Draws", "1999-05-09"));
        tvShow.addEpisode(new EpisodeViewModel("Brian: Portrait of a Dog", "1999-05-16"));
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
