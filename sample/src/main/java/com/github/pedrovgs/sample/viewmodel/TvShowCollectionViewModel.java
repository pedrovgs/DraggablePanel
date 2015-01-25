/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.sample.viewmodel;

import com.pedrogomez.renderers.AdapteeCollection;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * TvShowCollectionViewModel implementation used to contains all the tv shows information. This
 * implementation is based on a LinkedList with hardcoded data.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TvShowCollectionViewModel implements AdapteeCollection<TvShowViewModel> {

  private final List<TvShowViewModel> tvShows;

  @Inject public TvShowCollectionViewModel() {
    this.tvShows = new LinkedList<TvShowViewModel>();
    TvShowViewModel tvShow = new TvShowViewModel("Breaking Bad",
        "http://thetvdb.com/banners/_cache/posters/81189-22.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/81189-21.jpg", 5);
    tvShow.addEpisode(new EpisodeViewModel("Pilot", "2008-01-20"));
    tvShow.addEpisode(new EpisodeViewModel("Cat's in the Bag...", "2008-01-27"));
    tvShow.addEpisode(new EpisodeViewModel("...And the Bag's in the River", "2008-02-10"));
    tvShow.addEpisode(new EpisodeViewModel("Cancer Man", "2008-02-17"));
    tvShow.addEpisode(new EpisodeViewModel("Gray Matter", "2008-02-24"));
    tvShow.addEpisode(new EpisodeViewModel("Crazy Handful of Nothin'", "2008-03-02"));
    tvShow.addEpisode(new EpisodeViewModel("A No-Rough-Stuff-Type Deal'", "2008-03-09"));
    tvShows.add(tvShow);

    tvShow = new TvShowViewModel("Marvel's Agents of S.H.I.E.L.D.",
        "http://thetvdb.com/banners/_cache/posters/263365-3.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/263365-4.jpg", 1);
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

    tvShow = new TvShowViewModel("Lost", "http://thetvdb.com/banners/_cache/posters/73739-7.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/73739-20.jpg", 6);
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
    tvShow.addEpisode(
        new EpisodeViewModel("Whatever the Case May Be the Case May Be", "2005-01-05"));
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

    tvShow = new TvShowViewModel("Arrow", "http://thetvdb.com/banners/_cache/posters/257655-5.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/257655-16.jpg", 2);
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

    tvShow =
        new TvShowViewModel("The Newsroom", "http://thetvdb.com/banners/_cache/posters/76399-1.jpg",
            "http://thetvdb.com/banners/_cache/fanart/original/76399-1.jpg", 3);
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

    tvShow = new TvShowViewModel("How I Met Your Mother",
        "http://thetvdb.com/banners/_cache/posters/75760-29.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/75760-51.jpg", 9);
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

    tvShow = new TvShowViewModel("Game of Thrones",
        "http://thetvdb.com/banners/_cache/posters/121361-4.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/121361-15.jpg", 4);
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

    tvShow = new TvShowViewModel("Dexter", "http://thetvdb.com/banners/_cache/posters/79349-24.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/79349-42.jpg", 8);
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

    tvShow = new TvShowViewModel("House of Cards",
        "http://thetvdb.com/banners/_cache/posters/79861-1.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/79861-3.jpg", 3);
    tvShow.addEpisode(new EpisodeViewModel("House of Cards Episode 1", "1990-11-18"));
    tvShow.addEpisode(new EpisodeViewModel("House of Cards Episode 2", "1990-11-25"));
    tvShow.addEpisode(new EpisodeViewModel("House of Cards Episode 3", "1990-12-02"));
    tvShow.addEpisode(new EpisodeViewModel("House of Cards Episode 4", "1990-12-09"));
    tvShows.add(tvShow);

    tvShow = new TvShowViewModel("The Big Bang Theory",
        "http://thetvdb.com/banners/_cache/posters/80379-18.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/80379-38.jpg", 7);
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

    tvShow = new TvShowViewModel("Sleepy Hollow",
        "http://thetvdb.com/banners/_cache/posters/269578-4.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/269578-4.jpg", 1);
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

    tvShow = new TvShowViewModel("The Vampire Diaries",
        "http://thetvdb.com/banners/_cache/posters/95491-28.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/95491-68.jpg", 5);
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

    tvShow = new TvShowViewModel("Friends", "http://thetvdb.com/banners/_cache/posters/79168-3.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/79168-6.jpg", 10);
    tvShow.addEpisode(new EpisodeViewModel("The One Where Monica Gets A Roommate", "1994-09-22"));
    tvShow.addEpisode(new EpisodeViewModel("The One With The Sonogram At The End", "1994-09-29"));
    tvShow.addEpisode(new EpisodeViewModel("The One With The Thumb", "1994-10-06"));
    tvShow.addEpisode(new EpisodeViewModel("The One With George Stephanopoulos", "1994-10-13"));
    tvShow.addEpisode(
        new EpisodeViewModel("The One With The East German Laundry Detergent", "1994-10-20"));
    tvShow.addEpisode(new EpisodeViewModel("The One With The Butt", "1994-10-27"));
    tvShow.addEpisode(new EpisodeViewModel("The One With The Blackout", "1994-11-03"));
    tvShow.addEpisode(new EpisodeViewModel("The One Where Nana Dies Twice", "1994-11-10"));
    tvShow.addEpisode(new EpisodeViewModel("The One Where Underdog Gets Away", "1994-11-17"));
    tvShow.addEpisode(new EpisodeViewModel("The One With The Monkey", "1994-12-15"));
    tvShows.add(tvShow);

    tvShow =
        new TvShowViewModel("New Girl", "http://thetvdb.com/banners/_cache/posters/248682-9.jpg",
            "http://thetvdb.com/banners/_cache/fanart/original/248682-20.jpg", 3);
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

    tvShow =
        new TvShowViewModel("Family Guy", "http://thetvdb.com/banners/_cache/posters/75978-13.jpg",
            "http://thetvdb.com/banners/_cache/fanart/original/75978-27.jpg", 12);
    tvShow.addEpisode(new EpisodeViewModel("Death has a Shadow", "1999-01-31"));
    tvShow.addEpisode(new EpisodeViewModel("I Never Met the Dead Man", "1999-04-11"));
    tvShow.addEpisode(new EpisodeViewModel("Chitty Chitty Death Bang", "1999-04-18"));
    tvShow.addEpisode(new EpisodeViewModel("Mind Over Murder", "1999-04-25"));
    tvShow.addEpisode(new EpisodeViewModel("A Hero Sits Next Door", "1999-05-02"));
    tvShow.addEpisode(new EpisodeViewModel("The Son Also Draws", "1999-05-09"));
    tvShow.addEpisode(new EpisodeViewModel("Brian: Portrait of a Dog", "1999-05-16"));
    tvShows.add(tvShow);

    tvShow =
        new TvShowViewModel("Gossip Girl", "http://thetvdb.com/banners/_cache/posters/80547-11.jpg",
            "http://thetvdb.com/banners/_cache/fanart/original/80547-24.jpg", 6);
    tvShow.addEpisode(new EpisodeViewModel("Pilot", "2007-09-19"));
    tvShow.addEpisode(new EpisodeViewModel("The Wild Brunch", "2007-09-26"));
    tvShow.addEpisode(new EpisodeViewModel("Poison Ivy", "2007-10-03"));
    tvShow.addEpisode(new EpisodeViewModel("Bad News Blair", "2007-10-10"));
    tvShow.addEpisode(new EpisodeViewModel("Dare Devil", "2007-10-17"));
    tvShow.addEpisode(new EpisodeViewModel("The Handmaiden's Talea", "2007-10-24"));
    tvShow.addEpisode(new EpisodeViewModel("Victor (Victrola)", "2007-11-07"));
    tvShow.addEpisode(new EpisodeViewModel("Seventeen Candles", "2007-11-14"));
    tvShow.addEpisode(new EpisodeViewModel("Blair Waldorf Must Pie!", "2007-11-28"));
    tvShow.addEpisode(new EpisodeViewModel("Hi, Society", "2007-12-05"));
    tvShows.add(tvShow);

    tvShow =
        new TvShowViewModel("American Dad", "http://thetvdb.com/banners/_cache/posters/73141-1.jpg",
            "http://thetvdb.com/banners/_cache/fanart/original/73141-12.jpg", 11);
    tvShow.addEpisode(new EpisodeViewModel("Pilot", "2005-02-06"));
    tvShow.addEpisode(new EpisodeViewModel("Threat Levels", "2005-05-01"));
    tvShow.addEpisode(new EpisodeViewModel("Stan Knows Best", "2005-05-08"));
    tvShow.addEpisode(new EpisodeViewModel("Francine's Flashback", "2005-05-15"));
    tvShow.addEpisode(new EpisodeViewModel("Roger Codger", "2005-06-05"));
    tvShow.addEpisode(new EpisodeViewModel("Homeland Insecurity", "2005-06-12"));
    tvShow.addEpisode(new EpisodeViewModel("Deacon Stan, Jesus Man", "2005-06-19"));
    tvShows.add(tvShow);

    tvShow = new TvShowViewModel("The Simpsons",
        "http://thetvdb.com/banners/_cache/posters/71663-20.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/71663-30.jpg", 26);
    tvShow.addEpisode(new EpisodeViewModel("Simpsons Roasting on an Open Fire", "1989-12-17"));
    tvShow.addEpisode(new EpisodeViewModel("Bart the Genius", "1990-01-14"));
    tvShow.addEpisode(new EpisodeViewModel("Homer's Odyssey", "1990-01-21"));
    tvShow.addEpisode(new EpisodeViewModel("There's No Disgrace Like Home", "1990-01-28"));
    tvShow.addEpisode(new EpisodeViewModel("Bart the General", "1990-02-04"));
    tvShow.addEpisode(new EpisodeViewModel("Moaning Lisa", "1990-02-11"));
    tvShow.addEpisode(new EpisodeViewModel("The Call of the Simpsons", "1990-02-18"));
    tvShow.addEpisode(new EpisodeViewModel("The Telltale Head", "1990-02-25"));
    tvShow.addEpisode(new EpisodeViewModel("Life on the Fast Lane", "1990-03-18"));
    tvShow.addEpisode(new EpisodeViewModel("Homer's Night Out", "1990-03-25"));
    tvShow.addEpisode(new EpisodeViewModel("The Crepes of Wrath", "1990-04-15"));
    tvShow.addEpisode(new EpisodeViewModel("Krusty Gets Busted", "1990-04-29"));
    tvShow.addEpisode(new EpisodeViewModel("Some Enchanted Evening", "1990-05-13"));
    tvShows.add(tvShow);

    tvShow = new TvShowViewModel("The Mentalist",
        "http://thetvdb.com/banners/_cache/posters/82459-6.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/82459-4.jpg", 6);
    tvShow.addEpisode(new EpisodeViewModel("Pilot", "2008-09-23"));
    tvShow.addEpisode(new EpisodeViewModel("Red Hair and Silver Tape", "2008-09-30"));
    tvShow.addEpisode(new EpisodeViewModel("Red Tide", "2008-10-14"));
    tvShow.addEpisode(new EpisodeViewModel("Ladies in Red", "2008-10-21"));
    tvShow.addEpisode(new EpisodeViewModel("Redwood", "2008-10-28"));
    tvShow.addEpisode(new EpisodeViewModel("Red Handed", "2008-11-11"));
    tvShow.addEpisode(new EpisodeViewModel("Seeing Red", "2008-11-18"));
    tvShow.addEpisode(new EpisodeViewModel("The Thin Red Line", "2008-11-25"));
    tvShow.addEpisode(new EpisodeViewModel("Flame Red", "2008-12-02"));
    tvShow.addEpisode(new EpisodeViewModel("Red Brick and Ivy", "2008-12-16"));
    tvShows.add(tvShow);

    tvShow = new TvShowViewModel("Sons of Anarchy",
        "http://thetvdb.com/banners/_cache/posters/82696-1.jpg",
        "http://thetvdb.com/banners/_cache/fanart/original/82696-18.jpg", 6);
    tvShow.addEpisode(new EpisodeViewModel("Pilot", "2008-09-03"));
    tvShow.addEpisode(new EpisodeViewModel("Seeds", "2008-09-10"));
    tvShow.addEpisode(new EpisodeViewModel("Fun Town", "2008-09-17"));
    tvShow.addEpisode(new EpisodeViewModel("Patch Over", "2008-09-24"));
    tvShow.addEpisode(new EpisodeViewModel("Giving Back", "2008-10-01"));
    tvShow.addEpisode(new EpisodeViewModel("AK 51", "2008-10-08"));
    tvShow.addEpisode(new EpisodeViewModel("Old Bones", "2008-10-15"));
    tvShow.addEpisode(new EpisodeViewModel("The Pull", "2008-10-22"));
    tvShow.addEpisode(new EpisodeViewModel("Hell Followed", "2008-10-29"));
    tvShow.addEpisode(new EpisodeViewModel("Better Half", "2008-11-05"));
    tvShow.addEpisode(new EpisodeViewModel("Capybara", "2008-11-12"));
    tvShow.addEpisode(new EpisodeViewModel("The Sleep of Babies", "2008-11-19"));
    tvShow.addEpisode(new EpisodeViewModel("The Revelator", "2008-11-26"));
    tvShows.add(tvShow);
  }

  /**
   * Return the number of tv shows inside the collection.
   */
  @Override public int size() {
    return tvShows.size();
  }

  /**
   * Return a TvShowViewModel obtained by position.
   */
  @Override public TvShowViewModel get(int i) {
    return tvShows.get(i);
  }

  /**
   * Add a TvShowViewModel to the collection.
   */
  @Override public void add(TvShowViewModel tvShowViewModel) {
    tvShows.add(tvShowViewModel);
  }

  /**
   * Remove a TvShowViewModel from the collection.
   */
  @Override public void remove(TvShowViewModel tvShowViewModel) {
    tvShows.remove(tvShowViewModel);
  }

  /**
   * Add a collection of TvShowViewModel to the collection.
   */
  @Override public void addAll(Collection<TvShowViewModel> tvShowViewModels) {
    tvShows.addAll(tvShowViewModels);
  }

  /**
   * Remove a collection of TvShowViewModel to the collection.
   */
  @Override public void removeAll(Collection<TvShowViewModel> tvShowViewModels) {
    tvShowViewModels.removeAll(tvShowViewModels);
  }
}
