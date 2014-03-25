package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.DraggablePanel;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.fragment.BlackFragment;
import com.github.pedrovgs.sample.fragment.RedFragment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_sample);
        ButterKnife.inject(this);
        initializeListView();
        initializeDraggablePanel();
    }

    private void initializeDraggablePanel() {
        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(new RedFragment());
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
    }


}
