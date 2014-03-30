package com.github.pedrovgs.sample.viewmodel;

import com.pedrogomez.renderers.AdapteeCollection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class EpisodeCollection implements AdapteeCollection<EpisodeViewModel> {

    private final List<EpisodeViewModel> episodes;

    public EpisodeCollection() {
        this.episodes = new LinkedList<EpisodeViewModel>();
    }

    @Override
    public int size() {
        return episodes.size();
    }

    @Override
    public EpisodeViewModel get(int i) {
        return episodes.get(i);
    }

    @Override
    public void add(EpisodeViewModel episodeViewModel) {
        episodes.add(episodeViewModel);
    }

    @Override
    public void remove(EpisodeViewModel episodeViewModel) {
        episodes.remove(episodeViewModel);
    }

    @Override
    public void addAll(Collection<EpisodeViewModel> episodeViewModels) {
        episodes.addAll(episodeViewModels);
    }

    @Override
    public void removeAll(Collection<EpisodeViewModel> episodeViewModels) {
        episodes.removeAll(episodeViewModels);
    }
}
