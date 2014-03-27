package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.os.Handler;
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

    private static final String DRAGGABLE_PANEL_STATE = "draggable_panel_state";
    private static final String LAST_VIDEO_LOADED_POSITION = "last_video_loaded_position";

    @InjectView(R.id.lv_places)
    ListView lv_places;
    @InjectView(R.id.draggable_panel)
    DraggablePanel draggablePanel;

    @Inject
    RendererAdapter<PlaceViewModel> placesAdapter;

    private PlaceFragment placeFragment;
    private SupportMapFragment mapFragment;

    private int lastVideoLoadedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_sample);
        ButterKnife.inject(this);
        initializeFragments();
        initializeListView();
        initializeDraggablePanel();
    }

    private void recoverDraggablePanelState(Bundle savedInstanceState) {
        if (savedInstanceState.getSerializable(DRAGGABLE_PANEL_STATE) == null) {
            draggablePanel.setVisibility(View.GONE);
            return;
        }
        Handler handler = new Handler();
        final DraggablePanelState draggablePanelState = (DraggablePanelState) savedInstanceState.getSerializable(DRAGGABLE_PANEL_STATE);
        switch (draggablePanelState) {
            case MAXIMIZED:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        draggablePanel.maximize();
                    }
                }, 100);
                break;
            case MINIMIZED:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        draggablePanel.minimize();
                    }
                }, 100);
                break;
            case CLOSED_AT_LEFT:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        draggablePanel.closeToLeft();
                    }
                }, 100);
                break;
            case CLOSED_AT_RIGHT:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        draggablePanel.closeToRight();
                    }
                }, 100);
                break;
            default:
                draggablePanel.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveDraggableState(outState);
        saveLastVideoLoadedPosition(outState);
    }

    private void saveLastVideoLoadedPosition(Bundle outState) {
        outState.putInt(LAST_VIDEO_LOADED_POSITION, lastVideoLoadedPosition);
    }

    private void saveDraggableState(Bundle outState) {
        DraggablePanelState draggablePanelState = null;
        if (draggablePanel.isMaximized()) {
            draggablePanelState = DraggablePanelState.MAXIMIZED;
        } else if (draggablePanel.isMinimized()) {
            draggablePanelState = DraggablePanelState.MINIMIZED;
        } else if (draggablePanel.isClosedAtLeft()) {
            draggablePanelState = DraggablePanelState.CLOSED_AT_LEFT;
        } else if (draggablePanel.isClosedAtRight()) {
            draggablePanelState = DraggablePanelState.CLOSED_AT_RIGHT;
        }
        outState.putSerializable(DRAGGABLE_PANEL_STATE, draggablePanelState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        recoverDraggablePanelState(savedInstanceState);
        loadLastVideoLoaded(savedInstanceState);
    }

    private void loadLastVideoLoaded(Bundle savedInstanceState) {
        lastVideoLoadedPosition = savedInstanceState.getInt(LAST_VIDEO_LOADED_POSITION, 0);
        showPlace(lastVideoLoadedPosition);
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
                lastVideoLoadedPosition = position;
                showPlace(position);
            }
        });
    }

    private void showPlace(int position) {
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
