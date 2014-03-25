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
import com.github.pedrovgs.sample.viewmodel.CityViewModel;
import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class CityRenderer extends Renderer<CityViewModel> {

    private Context context;

    @InjectView(R.id.tv_name)
    TextView tv_name;
    @InjectView(R.id.iv_photo)
    ImageView iv_photo;

    public CityRenderer(Context context) {
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
        return layoutInflater.inflate(R.layout.city_row, viewGroup, false);
    }

    @Override
    protected void render() {
        CityViewModel city = getContent();
        tv_name.setText(city.getName());
        Picasso.with(context).load(city.getPhoto()).placeholder(R.drawable.maps_placeholder).into(iv_photo);
    }
}
