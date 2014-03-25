package com.github.pedrovgs.sample.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.pedrovgs.sample.R;
import com.github.pedrovgs.sample.viewmodel.PlaceViewModel;
import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class PlaceRenderer extends Renderer<PlaceViewModel> {

    private Context context;

    @InjectView(R.id.tv_name)
    TextView tv_name;
    @InjectView(R.id.iv_photo)
    ImageView iv_photo;

    public PlaceRenderer(Context context) {
        this.context = context;
    }

    @Override
    protected void setUpView(View view) {
        ButterKnife.inject(this, view);
    }

    @Override
    protected void hookListeners(View view) {
        //Empty
    }

    @Override
    protected View inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.place_row, viewGroup, false);
    }

    @Override
    protected void render() {
        PlaceViewModel place = getContent();
        tv_name.setText(place.getName());
        Picasso.with(context).load(place.getPhoto()).placeholder(R.drawable.maps_placeholder).into(iv_photo);
    }
}
