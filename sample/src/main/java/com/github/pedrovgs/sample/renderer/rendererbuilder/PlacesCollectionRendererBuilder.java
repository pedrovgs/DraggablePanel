/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.sample.renderer.rendererbuilder;

import com.github.pedrovgs.sample.renderer.PlaceRenderer;
import com.github.pedrovgs.sample.viewmodel.PlaceViewModel;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;
import java.util.Collection;

/**
 * RendererBuilder implementation created to map PlaceViewModel with PlaceRenderer implementations.
 * More info in this link: {@link https://github.com/pedrovgs/Renderers}
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class PlacesCollectionRendererBuilder extends RendererBuilder<PlaceViewModel> {

  public PlacesCollectionRendererBuilder(Collection<Renderer<PlaceViewModel>> prototypes) {
    super(prototypes);
  }

  @Override protected Class getPrototypeClass(PlaceViewModel placeViewModel) {
    return PlaceRenderer.class;
  }
}
