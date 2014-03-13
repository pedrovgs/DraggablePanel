package com.github.pedrovgs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MainActivity extends Activity {

    private View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
