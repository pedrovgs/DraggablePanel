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
package com.github.pedrovgs.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.viewmodel.PlaceViewModel;
import com.squareup.picasso.Picasso;

/**
 * Fragment implementation created to show a place inside an ImageView widget.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class PlaceFragment extends Fragment {

  @InjectView(R.id.tv_name) TextView nameTextView;
  @InjectView(R.id.iv_photo) ImageView photoImageView;

  private PlaceViewModel placeViewModel;

  /**
   * Override method used to initialize the fragment.
   */
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.place_row, container, false);
    ButterKnife.inject(this, view);
    return view;
  }

  /**
   * Use the PlaceViewModel information to render the place name and the place image inside the
   * fragment
   */
  public void showPlace(PlaceViewModel placeViewModel) {
    this.placeViewModel = placeViewModel;
    nameTextView.setText(placeViewModel.getName());
    Picasso.with(getActivity())
        .load(placeViewModel.getPhoto())
        .placeholder(R.drawable.maps_placeholder)
        .into(photoImageView);
  }

  /**
   * Method triggered when the iv_photo widget is clicked. This method shows a toast with the place
   * information.
   */
  @OnClick(R.id.iv_photo) void onPhotoClicked() {
    Toast.makeText(getActivity(), placeViewModel.getName(), Toast.LENGTH_LONG).show();
  }
}