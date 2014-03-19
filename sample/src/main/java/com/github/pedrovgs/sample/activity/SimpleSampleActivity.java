package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.github.pedrovgs.DraggablePanel;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.fragment.BlackFragment;
import com.github.pedrovgs.sample.fragment.RedFragment;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class SimpleSampleActivity extends FragmentActivity {

    @InjectView(R.id.draggable_panel)
    DraggablePanel draggablePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_sample);
        ButterKnife.inject(this);
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

    @OnClick(R.id.ll_container)
    void onContentClicked() {
        Toast.makeText(this, "View container clicked", Toast.LENGTH_SHORT).show();
    }

}
