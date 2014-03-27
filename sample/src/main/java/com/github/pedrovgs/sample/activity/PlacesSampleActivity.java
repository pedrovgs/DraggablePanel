package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.util.TypedValue;
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
import com.google.android.gms.maps.GoogleMap;
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
        mapFragment = SupportMapFragment.newInstance(new GoogleMapOptions().mapType(GoogleMap.MAP_TYPE_SATELLITE));
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
                MarkerOptions marker = new MarkerOptions().position(latitudeLongitude);
                marker.title(placeViewModel.getName());
                marker.snippet(placeViewModel.getLatitude() + " , " + placeViewModel.getLongitude());
                mapFragment.getMap().addMarker(marker);
                mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latitudeLongitude, 10f));
            }
        });
    }

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
        draggablePanel.setyScaleFactor(yScaleFactor);
        draggablePanel.setTopViewHeight(getResources().getDimension(R.dimen.top_fragment_height));
        draggablePanel.setTopFragmentMarginRight(getResources().getDimension(R.dimen.top_fragment_margin));
        draggablePanel.setTopFragmentMarginBottom(getResources().getDimension(R.dimen.top_fragment_margin));
        draggablePanel.setBackgroundColor(getResources().getColor(R.color.black));
        draggablePanel.initializeView();
        draggablePanel.setVisibility(View.GONE);
    }


}
