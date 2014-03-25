package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.DraggablePanel;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.fragment.BlackFragment;
import com.github.pedrovgs.sample.fragment.CityFragment;
import com.github.pedrovgs.sample.viewmodel.CityViewModel;
import com.pedrogomez.renderers.RendererAdapter;

import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class CitiesSampleActivity extends DIFragmentActivity {

    @InjectView(R.id.lv_cities)
    ListView lv_cities;
    @InjectView(R.id.draggable_panel)
    DraggablePanel draggablePanel;

    @Inject
    RendererAdapter<CityViewModel> citiesAdapter;

    private CityFragment cityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_sample);
        ButterKnife.inject(this);
        initializeFragments();
        initializeDraggablePanel();
        initializeListView();
    }

    private void initializeFragments() {
        cityFragment = new CityFragment();
    }

    private void initializeDraggablePanel() {
        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(cityFragment);
        draggablePanel.setBottomFragment(new BlackFragment());
        draggablePanel.setScaleFactor(getResources().getInteger(R.integer.scale_factor));
        draggablePanel.setTopViewHeight(getResources().getDimension(R.dimen.top_fragment_height));
        draggablePanel.setTopFragmentMarginRight(getResources().getDimension(R.dimen.top_fragment_margin));
        draggablePanel.setTopFragmentMarginBottom(getResources().getDimension(R.dimen.top_fragment_margin));
        draggablePanel.setBackgroundColor(getResources().getColor(R.color.black));
        draggablePanel.initializeView();
    }

    private void initializeListView() {
        lv_cities.setAdapter(citiesAdapter);
        lv_cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                CityViewModel cityViewModel = citiesAdapter.getItem(position);
                cityFragment.showCity(cityViewModel);
            }
        });
    }


}
