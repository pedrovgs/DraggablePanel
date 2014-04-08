package com.github.pedrovgs.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.sample.R;
import com.squareup.picasso.Picasso;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MoviePosterFragment extends Fragment {

    @InjectView(R.id.iv_thumbnail)
    ImageView iv_thumbnail;

    private String videoPosterThumbnail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_poster, container, false);
        ButterKnife.inject(this, view);
        Picasso.with(getActivity()).load(videoPosterThumbnail).placeholder(R.drawable.tv_show_placeholder).into(iv_thumbnail);
        return view;
    }


    public void setPoster(String videoPosterThumbnail) {
        this.videoPosterThumbnail = videoPosterThumbnail;
    }
}