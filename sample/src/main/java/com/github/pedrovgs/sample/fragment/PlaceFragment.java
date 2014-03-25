package com.github.pedrovgs.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.viewmodel.PlaceViewModel;
import com.squareup.picasso.Picasso;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class PlaceFragment extends Fragment {

    @InjectView(R.id.tv_name)
    TextView tv_name;
    @InjectView(R.id.iv_photo)
    ImageView iv_photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.place_row, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    public void showPlace(PlaceViewModel placeViewModel) {
        tv_name.setText(placeViewModel.getName());
        Picasso.with(getActivity()).load(placeViewModel.getPhoto()).placeholder(R.drawable.maps_placeholder).into(iv_photo);
    }

}