package com.github.pedrovgs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.github.pedrovgs.R;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class BlackFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_black, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Click on black fragment", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
