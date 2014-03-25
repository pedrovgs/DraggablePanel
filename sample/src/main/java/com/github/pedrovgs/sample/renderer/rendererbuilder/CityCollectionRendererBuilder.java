package com.github.pedrovgs.sample.renderer.rendererbuilder;

import com.github.pedrovgs.sample.renderer.CityRenderer;
import com.github.pedrovgs.sample.viewmodel.CityViewModel;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class CityCollectionRendererBuilder extends RendererBuilder<CityViewModel> {

    public CityCollectionRendererBuilder(Collection<Renderer<CityViewModel>> prototypes) {
        super(prototypes);
    }

    @Override
    protected Class getPrototypeClass(CityViewModel cityViewModel) {
        return CityRenderer.class;
    }
}
