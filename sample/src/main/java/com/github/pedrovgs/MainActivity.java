package com.github.pedrovgs;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.github.pedrovgs.fragment.BlackFragment;
import com.github.pedrovgs.fragment.RedFragment;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MainActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DraggablePanel draggablePanel = (DraggablePanel) findViewById(R.id.draggablePanel);

        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(new RedFragment());
        int topFragmentHeight = (int) getResources().getDimension(R.dimen.top_fragment_height);
        draggablePanel.setTopViewHeight(topFragmentHeight);
        draggablePanel.setBottomFragment(new BlackFragment());
        draggablePanel.initializeView();
    }
}
