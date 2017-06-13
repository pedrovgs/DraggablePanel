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
package com.github.pedrovgs.sample.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.DraggablePanel;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.fragment.PlaceFragment;
import com.github.pedrovgs.sample.viewmodel.PlaceViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedrogomez.renderers.RendererAdapter;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

/**
 * Sample activity created to show a list of famous places. If the user clicks on any list element
 * this sample shows a detailed draggable view with a picture and a map with the location.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class PlacesSampleActivity extends DIFragmentActivity {

  private static final String DRAGGABLE_PANEL_STATE = "draggable_panel_state";
  private static final String LAST_LOADED_PLACE_POSITION = "last_place_loaded_position";
  private static final int DELAY_MILLIS = 50;
  private static final float ZOOM = 10f;

  @InjectView(R.id.lv_places) ListView placesListView;
  @InjectView(R.id.draggable_panel) DraggablePanel draggablePanel;
  @InjectView(R.id.drawer_left) DrawerLayout drawerlayoutLeft;
  @InjectView(R.id.iv_drawer) ImageView drawerImageView;

  @Inject RendererAdapter<PlaceViewModel> placesAdapter;

  private PlaceFragment placeFragment;
  private SupportMapFragment mapFragment;

  private int lastLoadedPlacePosition;

  private ActionBarDrawerToggle drawerToggle;

  /**
   * Initialize the Activity with some injected data.
   */
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_place_sample);
    ButterKnife.inject(this);
    initializeFragments();
    initializeListView();
    initializeDraggablePanel();
    configNavigationDrawer();
  }

  /**
   * Sync the drawerToggle
   */
  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    drawerToggle.syncState();
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    drawerToggle.onConfigurationChanged(newConfig);
  }

  /**
   * Save the DraggablePanel state to restore it once the activity lifecycle be rebooted.
   *
   * @param outState bundle to put the DraggableState information.
   */
  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    saveDraggableState(outState);
    saveLastPlaceLoadedPosition(outState);
  }

  /**
   * Restore the DraggablePanel state.
   *
   * @param savedInstanceState bundle to get the Draggable state.
   */
  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    recoverDraggablePanelState(savedInstanceState);
    loadLastPlaceClicked(savedInstanceState);
  }

  /**
   * Get the DraggablePanelState from the saved bundle, modify the DraggablePanel visibility to
   * GONE
   * and apply the
   * DraggablePanelState to recover the last graphic state.
   */
  private void recoverDraggablePanelState(Bundle savedInstanceState) {
    final DraggableState draggableState =
        (DraggableState) savedInstanceState.getSerializable(DRAGGABLE_PANEL_STATE);
    if (draggableState == null) {
      draggablePanel.setVisibility(View.GONE);
      return;
    }
    updateDraggablePanelStateDelayed(draggableState);
  }

  /**
   * Return the view to the DraggablePanelState: minimized, maximized, closed to the right or
   * closed
   * to the left.
   *
   * @param draggableState to apply.
   */
  private void updateDraggablePanelStateDelayed(DraggableState draggableState) {
    Handler handler = new Handler();
    switch (draggableState) {
      case MAXIMIZED:
        handler.postDelayed(new Runnable() {
          @Override public void run() {
            draggablePanel.maximize();
          }
        }, DELAY_MILLIS);
        break;
      case MINIMIZED:
        handler.postDelayed(new Runnable() {
          @Override public void run() {
            draggablePanel.minimize();
          }
        }, DELAY_MILLIS);
        break;
      case CLOSED_AT_LEFT:
        handler.postDelayed(new Runnable() {
          @Override public void run() {
            draggablePanel.setVisibility(View.GONE);
            draggablePanel.closeToLeft();
          }
        }, DELAY_MILLIS);
        break;
      case CLOSED_AT_RIGHT:
        handler.postDelayed(new Runnable() {
          @Override public void run() {
            draggablePanel.setVisibility(View.GONE);
            draggablePanel.closeToRight();
          }
        }, DELAY_MILLIS);
        break;
      default:
        draggablePanel.setVisibility(View.GONE);
        break;
    }
  }

  /**
   * Keep a reference of the last place loaded.
   *
   * @param outState Bundle used to store the position.
   */
  private void saveLastPlaceLoadedPosition(Bundle outState) {
    outState.putInt(LAST_LOADED_PLACE_POSITION, lastLoadedPlacePosition);
  }

  /**
   * Keep a reference of the last DraggablePanelState.
   *
   * @param outState Bundle used to store the DraggablePanelState.
   */
  private void saveDraggableState(Bundle outState) {
    DraggableState draggableState = null;
    if (draggablePanel.isMaximized()) {
      draggableState = DraggableState.MAXIMIZED;
    } else if (draggablePanel.isMinimized()) {
      draggableState = DraggableState.MINIMIZED;
    } else if (draggablePanel.isClosedAtLeft()) {
      draggableState = DraggableState.CLOSED_AT_LEFT;
    } else if (draggablePanel.isClosedAtRight()) {
      draggableState = DraggableState.CLOSED_AT_RIGHT;
    }
    outState.putSerializable(DRAGGABLE_PANEL_STATE, draggableState);
  }

  /**
   * Apply the last place loaded to the different fragments showed inside the DraggablePanel..
   */
  private void loadLastPlaceClicked(Bundle savedInstanceState) {
    lastLoadedPlacePosition = savedInstanceState.getInt(LAST_LOADED_PLACE_POSITION, 0);
    showPlace(lastLoadedPlacePosition);
  }

  /**
   * Initialize PlaceFragment and SupportMapFragment.
   */
  private void initializeFragments() {
    placeFragment = new PlaceFragment();
    mapFragment = SupportMapFragment.newInstance(
        new GoogleMapOptions().mapType(GoogleMap.MAP_TYPE_SATELLITE));
    Picasso.with(this)
        .load("http://www.hdiphonewallpapers.us/phone-wallpapers/iphone-4-wallpapers/"
            + "hd-iphone-3gs-wallpapers-496ios.jpg")
        .into(drawerImageView);
  }

  /**
   * Initialize places ListView using placesAdapter and configure OnItemClickListener.
   */
  private void initializeListView() {
    placesListView.setAdapter(placesAdapter);
    placesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        lastLoadedPlacePosition = position;
        showPlace(position);
      }
    });
  }

  /**
   * Show a place in PlaceFragment and SupportMapFragment and apply the maximize effect over the
   * DraggablePanel.
   */
  private void showPlace(int position) {
    draggablePanel.setVisibility(View.VISIBLE);
    draggablePanel.maximize();
    PlaceViewModel placeViewModel = placesAdapter.getItem(position);
    placeFragment.showPlace(placeViewModel);

    mapFragment.getMap().clear();
    LatLng latitudeLongitude =
        new LatLng(placeViewModel.getLatitude(), placeViewModel.getLongitude());
    MarkerOptions marker = new MarkerOptions().position(latitudeLongitude);
    marker.title(placeViewModel.getName());
    marker.snippet(placeViewModel.getLatitude() + " , " + placeViewModel.getLongitude());
    mapFragment.getMap().addMarker(marker);
    mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latitudeLongitude, ZOOM));
  }

  /**
   * Initialize the DraggablePanel with top and bottom Fragments and apply all the configuration.
   */
  private void initializeDraggablePanel() {
    draggablePanel.setFragmentManager(getSupportFragmentManager());
    draggablePanel.setTopFragment(placeFragment);
    draggablePanel.setBottomFragment(mapFragment);
    TypedValue typedValue = new TypedValue();
    getResources().getValue(R.dimen.x_scale_factor, typedValue, true);
    float xScaleFactor = typedValue.getFloat();
    typedValue = new TypedValue();
    getResources().getValue(R.dimen.y_scale_factor, typedValue, true);
    float yScaleFactor = typedValue.getFloat();
    draggablePanel.setXScaleFactor(xScaleFactor);
    draggablePanel.setYScaleFactor(yScaleFactor);
    draggablePanel.setTopViewHeight(
        getResources().getDimensionPixelSize(R.dimen.top_fragment_height));
    draggablePanel.setTopFragmentMarginRight(
        getResources().getDimensionPixelSize(R.dimen.top_fragment_margin));
    draggablePanel.setTopFragmentMarginBottom(
        getResources().getDimensionPixelSize(R.dimen.top_fragment_margin));
    draggablePanel.initializeView();
    draggablePanel.setVisibility(View.GONE);
  }

  private void configNavigationDrawer() {
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    drawerlayoutLeft.setDrawerShadow(R.drawable.drawer_shadow, Gravity.LEFT);
    drawerToggle =
        new ActionBarDrawerToggle(this, drawerlayoutLeft, R.drawable.nav_drawer, R.string.app_name,
            R.string.app_name) {

          @Override public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
          }

          @Override public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
          }

          @Override public void onDrawerSlide(View drawerView, float slideOffset) {
            super.onDrawerSlide(drawerView, slideOffset);
            draggablePanel.slideHorizontally(slideOffset, ViewCompat.getX(drawerView),
                drawerView.getWidth());
          }
        };
    drawerlayoutLeft.setDrawerListener(drawerToggle);
  }
}
