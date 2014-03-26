package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.DraggablePanel;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.fragment.PlaceFragment;
import com.github.pedrovgs.sample.viewmodel.PlaceViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedrogomez.renderers.RendererAdapter;

import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class PlacesSampleActivity extends DIFragmentActivity {

    @InjectView(R.id.lv_places)
    ListView lv_places;
    @InjectView(R.id.draggable_panel)
    DraggablePanel draggablePanel;

    @Inject
    RendererAdapter<PlaceViewModel> placesAdapter;

    private PlaceFragment placeFragment;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_sample);
        ButterKnife.inject(this);
        initializeFragments();
        initializeListView();
        initializeDraggablePanel();
    }


    private void initializeFragments() {
        placeFragment = new PlaceFragment();
        GoogleMapOptions options = new GoogleMapOptions();
        mapFragment = SupportMapFragment.newInstance(options);
    }

    private void initializeListView() {
        lv_places.setAdapter(placesAdapter);
        lv_places.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                draggablePanel.setVisibility(View.VISIBLE);
                draggablePanel.maximize();
                PlaceViewModel placeViewModel = placesAdapter.getItem(position);
                placeFragment.showPlace(placeViewModel);

                mapFragment.getMap().clear();
                LatLng latitudeLongitude = new LatLng(placeViewModel.getLatitude(), placeViewModel.getLongitude());
                mapFragment.getMap().addMarker(new MarkerOptions().position(latitudeLongitude));
                mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLng(latitudeLongitude));

            }
        });
    }

    private void initializeDraggablePanel() {
        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(placeFragment);
        draggablePanel.setBottomFragment(mapFragment);
        draggablePanel.setScaleFactor(getResources().getInteger(R.integer.scale_factor));
        draggablePanel.setTopViewHeight(getResources().getDimension(R.dimen.top_fragment_height));
        draggablePanel.setTopFragmentMarginRight(getResources().getDimension(R.dimen.top_fragment_margin));
        draggablePanel.setTopFragmentMarginBottom(getResources().getDimension(R.dimen.top_fragment_margin));
        draggablePanel.setBackgroundColor(getResources().getColor(R.color.black));
        draggablePanel.initializeView();
        draggablePanel.setVisibility(View.GONE);
    }


}
