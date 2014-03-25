package com.github.pedrovgs.sample.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.github.pedrovgs.sample.DraggablePanelApplication;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DIFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DraggablePanelApplication)getApplication()).inject(this);
    }
}
